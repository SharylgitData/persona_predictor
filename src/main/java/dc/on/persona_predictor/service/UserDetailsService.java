package dc.on.persona_predictor.service;

import dc.on.persona_predictor.constants.Constant;
import dc.on.persona_predictor.entity.*;
import dc.on.persona_predictor.repository.UserDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import java.util.*;
import dc.on.persona_predictor.dao.UserDao;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UserDetailsService {

    @Autowired
    UserDataRepository userData;

    @Autowired
    UserDao userDao;

    @Autowired
    private AsyncService asyncService;

    Logger logger= LoggerFactory.getLogger(UserDetailsService.class);


    public String signUp(UserInfo userInfo){
        try {
            if(Optional.ofNullable(userInfo.isorganization).get().equalsIgnoreCase("Yes"))
                userInfo.type = "organization";
            else
                userInfo.type = "jobSeeker";
            userInfo.personality_test = "N";
            logger.info("userInfo {}", userInfo);
            UserInfo dataSaved = userData.save(userInfo);
            return Optional.of(dataSaved.id)
                    .filter(r->r>0)
                    .map(o->"User Successfully Registered")
                    .get();
        }catch(Exception sqlException){
            logger.info("Exception occured while signing up the user {}" +
                    ". The exception is {}", userInfo.first_name, sqlException.getMessage());
            return "The User is already signed In";
        }
    }



    public ResponseEntity<ApiResponse<JobDetailsTypes>> getDetails(UserInfo userInfo) {
        ResponseEntity<ApiResponse<JobDetailsTypes>> jobDetails= validateAndgetDetails(userInfo);
        return jobDetails;
    }

    private ResponseEntity<ApiResponse<JobDetailsTypes>> validateAndgetDetails(UserInfo userInfo) {
        UserInfo userEntireInfo = verifyUser(userInfo);
        ApiResponse<JobDetailsTypes> response ;
        String message = "";
        JobDetailsTypes jobDetailsTypes; ;
        List<JobDetails> jobDetails = new ArrayList<>();
       if(!Optional.ofNullable(userEntireInfo).isEmpty()) {
           jobDetails = getUserDetails(userEntireInfo);
       }
       else
           message = "User Name or Password is incorrect";
       String userName = "";
        if(userEntireInfo.type.equals(Constant.ORG))
            userName = userEntireInfo.companyname;
        else
            userName =  userEntireInfo.first_name;
       if(!message.equalsIgnoreCase("")) {

           jobDetailsTypes =  new JobDetailsTypes(jobDetails, userEntireInfo.email_id, userName , userEntireInfo.type, userEntireInfo.personality_test);
           response = new ApiResponse<>(message, "error", jobDetailsTypes);
           return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
       }

        jobDetailsTypes =  new JobDetailsTypes(jobDetails, userEntireInfo.email_id, userName, userEntireInfo.type, userEntireInfo.personality_test);
        response = new ApiResponse<>("successfully logged in", "success", jobDetailsTypes);
        return  ResponseEntity.ok(response);


    }

    private UserInfo verifyUser(UserInfo userInfo) {
        String email_id = userInfo.email_id;
        String password = userInfo.password;
        UserInfo userEntireInfo ;
        userEntireInfo = userDao.isValideUser(email_id, password);

        if(Optional.ofNullable(userEntireInfo).isPresent() && Optional.ofNullable(userEntireInfo).get().id > 0)
            return userEntireInfo;
        return null;
    }

    private List<JobDetails> getUserDetails(UserInfo userInfo) {
        List<JobDetails> jobDetails ;
        if(userInfo.type.equalsIgnoreCase(Constant.ORG))
            jobDetails = userDao.getOrgListedJobs(userInfo.companyname);
        else if (userInfo.type.equalsIgnoreCase(Constant.JOB_SEEKER))
            jobDetails = userDao.getListedJobs();
        else
            jobDetails = userDao.getAllJobs();

        return  jobDetails;
        }

    public ResponseEntity<ApiResponse<String>> saveJdRequest(JobDetails jobDetails) {
        String response =  userDao.saveJobRequest(jobDetails);
        ApiResponse<String> apiResponse = new ApiResponse<>(response, response,  response);
        return ResponseEntity.ok(apiResponse);

    }

    public ResponseEntity<ApiResponse<String>> requestedJobPostAction(Integer id, String action){
        String response =  userDao.updateAction(id, action);
        if(response.equals("Status Updated successfully"))
            return ResponseEntity.ok(new ApiResponse<>(response, response,response));
        ApiResponse<String> res  = new ApiResponse<>(response, "error", response);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse<String>> saveUserPersonality(String dominantPersonality, String emailId) {
        String result = userDao.insertAndUpdatePersonality(dominantPersonality, emailId, Constant.YES);
        ApiResponse<String> res  = new ApiResponse<>(result, "success", result);
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<String>  forwardResumeToPython(MultipartFile file, String jobId, String emailId, String job_description,String job_title) {

            asyncService.getResumeDetails(file,jobId, emailId, job_description, job_title);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Your Resume has been Submitted. Thank you for applying.");



    }

    public ResponseEntity<String> checkIfAlreadyApplied(String jobId, String emailId) {
        Boolean ifexist= userDao.checkApplication(jobId, emailId);
        if(ifexist)
            return ResponseEntity.ok("User has already applied for this position");
        return ResponseEntity.ok("User is filing the application first time");
    }

    public ResponseEntity<List<ResumeInfo>> getAppliedUsersInfo(String jobId) {
        List<ResumeInfo> resumeInfo;
        resumeInfo = userDao.getAppliedCandidates(jobId);
        if(!resumeInfo.isEmpty())
            return ResponseEntity.ok(resumeInfo);
        return new ResponseEntity<>(resumeInfo, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<CandidateApplication>> getCandidateApplication(String emailId) {
        List<CandidateApplication> candidateApplications;
        candidateApplications = userDao.getApplicationsFiled(emailId);
        if(!candidateApplications.isEmpty())
            return ResponseEntity.ok(candidateApplications);
        return new ResponseEntity<>(candidateApplications, HttpStatus.BAD_REQUEST);
    }
}

