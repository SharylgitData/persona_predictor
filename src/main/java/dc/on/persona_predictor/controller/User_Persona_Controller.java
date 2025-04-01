package dc.on.persona_predictor.controller;

import dc.on.persona_predictor.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dc.on.persona_predictor.service.UserDetailsService;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/resume")
public class User_Persona_Controller {

   @Autowired
   UserDetailsService userDetails;

    @PostMapping("/signUp")
    public Map<String, String> storeInfo(@RequestBody UserInfo userInfo){

        System.out.println("User data is " +userInfo);
        Map<String, String> result = new  HashMap<>();
        result.put("result",  userDetails.signUp(userInfo));

        return result;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JobDetailsTypes>> userLogin(@RequestBody UserInfo userInfo){
        System.out.println("welcome to  login api point"+ userInfo);
        ResponseEntity<ApiResponse<JobDetailsTypes>> jd = userDetails.getDetails(userInfo);
        return jd;
    }

    @PostMapping("/sendJobRequest")
    public ResponseEntity<?> employerJdReq(@RequestBody JobDetails jobPostRequest){
        System.out.println("request to send to admin is "+ jobPostRequest);

        ResponseEntity<ApiResponse<String>> draft = userDetails.saveJdRequest(jobPostRequest);
        return  draft;
    }


    @PostMapping("/decidePosting/{id}/{status}")
    public ResponseEntity<ApiResponse<String>> decidePosting(@PathVariable("id") Integer id,
                                                             @PathVariable("status") String status){
        ResponseEntity<ApiResponse<String>> draft = userDetails.requestedJobPostAction(id, status);
        return  draft;
    }

    @PostMapping("/savePersonality/{dominantPersonality}/{emailId}")
    public ResponseEntity<ApiResponse<String>> savePersonality(@PathVariable("dominantPersonality") String dominantPersonality,
                                  @PathVariable("emailId") String emailId){
        ResponseEntity<ApiResponse<String>> result =  userDetails.saveUserPersonality(dominantPersonality, emailId);
        return result;
    }


    @PostMapping(value = "/apply", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPdf(
            @RequestParam("resume") MultipartFile file,
            @RequestParam("job_id") String jobId,
            @RequestParam("email_id") String emailId,
            @RequestParam("job_description") String job_description,
            @RequestParam("job_title") String job_title
    ){
        ResponseEntity<String> result =  userDetails.forwardResumeToPython(file, jobId, emailId, job_description,job_title);
        return result;
    }

    @GetMapping(value = "/ifapplied/{jobid}/{emailId}")
    public ResponseEntity<String> checkUserApplication(@PathVariable("jobid") String jobId, @PathVariable("emailId") String emailId){
        ResponseEntity<String> ifApplied =  userDetails.checkIfAlreadyApplied(jobId, emailId);
            return ifApplied;
    }

    @GetMapping(value = "/getAppliedUsers/{jobid}")
    public ResponseEntity<List<ResumeInfo>> getAppliedUsers(@PathVariable("jobid") String jobId){
        ResponseEntity<List<ResumeInfo>> getUsers =  userDetails.getAppliedUsersInfo(jobId);
        return getUsers;
    }
}
