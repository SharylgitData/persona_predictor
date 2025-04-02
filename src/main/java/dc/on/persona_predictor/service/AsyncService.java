package dc.on.persona_predictor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dc.on.persona_predictor.dao.UserDao;
import dc.on.persona_predictor.entity.ResumeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@EnableAsync
public class AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncService.class);
    @Autowired
    UserDao userDao;
    @Async
    public CompletableFuture getResumeDetails(MultipartFile file, String jobId, String emailId, String job_description, String job_title) {

            try {
                ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                };
                String personality = userDao.getPersonality(emailId);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
                body.add("file", resource);
                body.add("personality", personality);
                body.add("job_title", job_title);
                body.add("job_description", job_description);
                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
                String pythonServiceUrl = "https://python-cv-analyzer.onrender.com/uploadPdf";

                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Map<String, Object>> response = restTemplate.exchange(pythonServiceUrl, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Map<String, Object>>() {
                });
                logger.info("The response is "+response);
                logger.info("the end of response");
                storeResponse(response, jobId, emailId);
                Map<String, Object> res = response.getBody();


                return CompletableFuture.completedFuture("Completed processing");
            } catch (Exception e) {
                e.printStackTrace();
                return CompletableFuture.completedFuture("Error while calling or storing the resume entities"+e.getMessage());
            }

        }

        private void storeResponse(ResponseEntity<Map<String, Object>> response, String jobId, String emailId) {
            ResumeInfo resumeInfo = new ResumeInfo();
            Map<String, Object> pythonResponse = response.getBody();
            resumeInfo.setName(Optional.ofNullable(pythonResponse.get("name")).isPresent()? pythonResponse.get("name").toString() : null);
            resumeInfo.setRankReason(Optional.ofNullable(pythonResponse.get("reason")).isPresent() ? pythonResponse.get("reason").toString() :  null);
            resumeInfo.setRank(Optional.ofNullable(pythonResponse.get("rank")).isPresent() ? Integer.parseInt(pythonResponse.get("rank").toString()) : null);
            resumeInfo.setJob_id(Integer.parseInt(jobId));
            resumeInfo.setJobseeker_email_id(emailId);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString="";
            try {
                jsonString = mapper.writeValueAsString(pythonResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resumeInfo.setUser_entities(jsonString);

            userDao.saveResumeInfo(resumeInfo);

        }


}
