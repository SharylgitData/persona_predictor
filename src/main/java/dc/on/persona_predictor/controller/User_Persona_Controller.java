package dc.on.persona_predictor.controller;

import dc.on.persona_predictor.constants.Constant;
import dc.on.persona_predictor.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dc.on.persona_predictor.service.UserDetailsService;
import java.util.HashMap;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;
import  dc.on.persona_predictor.constants.MessageLog;

@CrossOrigin(origins = Constant.ASTRICK, allowedHeaders = Constant.ASTRICK)
@RestController
@RequestMapping(Constant.ROOT_URL)
public class User_Persona_Controller {

    Logger logger = LoggerFactory.getLogger(User_Persona_Controller.class);

    @Autowired
    UserDetailsService userDetails;

    @PostMapping(Constant.SIGN_UP)
    public Map<String, String> storeUserInfo(@RequestBody UserInfo userInfo){

        logger.info(MessageLog.USER_DATA_LOG, userInfo);
        Map<String, String> result = new  HashMap<>();
        result.put(MessageLog.RESULT_KEY, userDetails.signUp(userInfo));
        return result;
    }

    @PostMapping(path = Constant.LOGIN, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<JobDetailsTypes>> userLogin(@RequestBody UserInfo userInfo){
        logger.info(MessageLog.WELCOME_LOGIN_LOG, userInfo);
        ResponseEntity<ApiResponse<JobDetailsTypes>> svcResp = userDetails.getDetails(userInfo);
        return ResponseEntity
                .status(svcResp.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(svcResp.getBody());
    }

    @PostMapping(Constant.JOB_POST_REQUEST)
    public ResponseEntity<?> employerJdReq(@RequestBody JobDetails jobPostRequest){
        logger.info(MessageLog.REQUEST_TO_ADMIN_LOG, jobPostRequest);

        ResponseEntity<ApiResponse<String>> draft = userDetails.saveJdRequest(jobPostRequest);
        return  draft;
    }


    @PostMapping(Constant.DECIDE_POSTING)
    public ResponseEntity<ApiResponse<String>> decidePosting(@PathVariable(Constant.PARAM_ID) Integer id,
                                                             @PathVariable(Constant.PARAM_STATUS) String status){
        ResponseEntity<ApiResponse<String>> draft = userDetails.requestedJobPostAction(id, status);
        return  draft;
    }

    @PostMapping(Constant.STORE_PERSONALITY)
    public ResponseEntity<ApiResponse<String>> savePersonality(@PathVariable(Constant.DOMINANT_PERSONALITY) String dominantPersonality,
                                                               @PathVariable(Constant.EMAIL_ID) String emailId){
        ResponseEntity<ApiResponse<String>> result =  userDetails.saveUserPersonality(dominantPersonality, emailId);
        return result;
    }


    @PostMapping(value = Constant.JOB_APPLICATION, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPdf(
            @RequestParam(Constant.RESUME) MultipartFile file,
            @RequestParam(Constant.JOB_ID) String jobId,
            @RequestParam(Constant.EMAIL_ID_UNDERSCORE) String emailId,
            @RequestParam(Constant.JOB_DESCRIPTION) String job_description,
            @RequestParam(Constant.JOB_TITLE) String job_title
    ){
        ResponseEntity<String> result =  userDetails.forwardResumeToPython(file, jobId, emailId, job_description,job_title);
        return result;
    }

    @GetMapping(Constant.CHECK_IF_ALREADY_APPLIED)
    public ResponseEntity<String> checkUserApplication(@PathVariable(Constant.JOBID) String jobId, @PathVariable(Constant.EMAIL_ID) String emailId){
        ResponseEntity<String> ifApplied =  userDetails.checkIfAlreadyApplied(jobId, emailId);
        return ifApplied;
    }

    @GetMapping(Constant.GET_APPLIED_USERS)
    public ResponseEntity<List<ResumeInfo>> getAppliedUsers(@PathVariable(Constant.JOBID) String jobId){
        ResponseEntity<List<ResumeInfo>> getUsers =  userDetails.getAppliedUsersInfo(jobId);
        return getUsers;
    }

    @GetMapping(Constant.GET_CANDIDATE_APPLICATION)
    public ResponseEntity<List<CandidateApplication>> getCandidateApplication(@PathVariable(Constant.EMAIL_ID) String emailId){
        ResponseEntity<List<CandidateApplication>> jobsFiled =  userDetails.getCandidateApplication(emailId);
        return jobsFiled;
    }
}
