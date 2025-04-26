//package dc.on.persona_predictor.service;
//
//import dc.on.persona_predictor.constants.Constant;
//import dc.on.persona_predictor.entity.*;
//import dc.on.persona_predictor.repository.UserDataRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import java.util.*;
//import dc.on.persona_predictor.dao.UserDao;
//import org.springframework.web.multipart.MultipartFile;
//
//
//@Service
//public class UserDetailsService {
//
//    @Autowired
//    UserDataRepository userData;
//
//    @Autowired
//    UserDao userDao;
//
//    @Autowired
//    private AsyncService asyncService;
//
//    Logger logger= LoggerFactory.getLogger(UserDetailsService.class);
//
//
//    public String signUp(UserInfo userInfo){
//        try {
//            if(Optional.ofNullable(userInfo.isorganization).get().equalsIgnoreCase("Yes"))
//                userInfo.type = "organization";
//            else
//                userInfo.type = "jobSeeker";
//            userInfo.personality_test = "N";
//            logger.info("userInfo {}", userInfo);
//            UserInfo dataSaved = userData.save(userInfo);
//            return Optional.of(dataSaved.id)
//                    .filter(r->r>0)
//                    .map(o->"User Successfully Registered")
//                    .get();
//        }catch(Exception sqlException){
//            logger.info("Exception occured while signing up the user {}" +
//                    ". The exception is {}", userInfo.first_name, sqlException.getMessage());
//            return "The User is already signed In";
//        }
//    }
//
//
//
//    public ResponseEntity<ApiResponse<JobDetailsTypes>> getDetails(UserInfo userInfo) {
//        ResponseEntity<ApiResponse<JobDetailsTypes>> jobDetails= validateAndgetDetails(userInfo);
//        return jobDetails;
//    }
//
//    private ResponseEntity<ApiResponse<JobDetailsTypes>> validateAndgetDetails(UserInfo userInfo) {
//        UserInfo userEntireInfo = verifyUser(userInfo);
//        ApiResponse<JobDetailsTypes> response ;
//        String message = "";
//        JobDetailsTypes jobDetailsTypes; ;
//        List<JobDetails> jobDetails = new ArrayList<>();
//       if(!Optional.ofNullable(userEntireInfo).isEmpty()) {
//           jobDetails = getUserDetails(userEntireInfo);
//       }
//       else
//           message = "User Name or Password is incorrect";
//       String userName = "";
//        if(userEntireInfo.type.equals(Constant.ORG))
//            userName = userEntireInfo.companyname;
//        else
//            userName =  userEntireInfo.first_name;
//       if(!message.equalsIgnoreCase("")) {
//
//           jobDetailsTypes =  new JobDetailsTypes(jobDetails, userEntireInfo.email_id, userName , userEntireInfo.type, userEntireInfo.personality_test);
//           response = new ApiResponse<>(message, "error", jobDetailsTypes);
//           return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//       }
//
//        jobDetailsTypes =  new JobDetailsTypes(jobDetails, userEntireInfo.email_id, userName, userEntireInfo.type, userEntireInfo.personality_test);
//        response = new ApiResponse<>("successfully logged in", "success", jobDetailsTypes);
//        return  ResponseEntity.ok(response);
//
//
//    }
//
//    private UserInfo verifyUser(UserInfo userInfo) {
//        String email_id = userInfo.email_id;
//        String password = userInfo.password;
//        UserInfo userEntireInfo ;
//        try{userEntireInfo = userDao.isValideUser(email_id, password);}catch (Exception e){userEntireInfo = null;}
//
//        if(Optional.ofNullable(userEntireInfo).isPresent() && Optional.ofNullable(userEntireInfo).get().id > 0)
//            return userEntireInfo;
//        return null;
//    }
//
//    private List<JobDetails> getUserDetails(UserInfo userInfo) {
//        List<JobDetails> jobDetails ;
//        if(userInfo.type.equalsIgnoreCase(Constant.ORG))
//            jobDetails = userDao.getOrgListedJobs(userInfo.companyname);
//        else if (userInfo.type.equalsIgnoreCase(Constant.JOB_SEEKER))
//            jobDetails = userDao.getListedJobs();
//        else
//            jobDetails = userDao.getAllJobs();
//
//        return  jobDetails;
//        }
//
//    public ResponseEntity<ApiResponse<String>> saveJdRequest(JobDetails jobDetails) {
//        String response =  userDao.saveJobRequest(jobDetails);
//        ApiResponse<String> apiResponse = new ApiResponse<>(response, response,  response);
//        return ResponseEntity.ok(apiResponse);
//
//    }
//
//    public ResponseEntity<ApiResponse<String>> requestedJobPostAction(Integer id, String action){
//        String response =  userDao.updateAction(id, action);
//        if(response.equals("Status Updated successfully"))
//            return ResponseEntity.ok(new ApiResponse<>(response, response,response));
//        ApiResponse<String> res  = new ApiResponse<>(response, "error", response);
//        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
//    }
//
//    public ResponseEntity<ApiResponse<String>> saveUserPersonality(String dominantPersonality, String emailId) {
//        String result = userDao.insertAndUpdatePersonality(dominantPersonality, emailId, Constant.YES);
//        ApiResponse<String> res  = new ApiResponse<>(result, "success", result);
//        return ResponseEntity.ok(res);
//    }
//
//    public ResponseEntity<String>  forwardResumeToPython(MultipartFile file, String jobId, String emailId, String job_description,String job_title) {
//            assert (file!=null && (jobId!=null && Integer.parseInt(jobId)>0));
//            asyncService.getResumeDetails(file,jobId, emailId, job_description, job_title);
//            return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .body("Your Resume has been Submitted. Thank you for applying.");
//
//
//
//    }
//
//    public ResponseEntity<String> checkIfAlreadyApplied(String jobId, String emailId) {
//        Boolean ifexist= userDao.checkApplication(jobId, emailId);
//        if(ifexist)
//            return ResponseEntity.ok("User has already applied for this position");
//        return ResponseEntity.ok("User is filing the application first time");
//    }
//
//    public ResponseEntity<List<ResumeInfo>> getAppliedUsersInfo(String jobId) {
//        List<ResumeInfo> resumeInfo;
//        resumeInfo = userDao.getAppliedCandidates(jobId);
//        if(!resumeInfo.isEmpty())
//            return ResponseEntity.ok(resumeInfo);
//        return new ResponseEntity<>(resumeInfo, HttpStatus.BAD_REQUEST);
//    }
//
//    public ResponseEntity<List<CandidateApplication>> getCandidateApplication(String emailId) {
//        List<CandidateApplication> candidateApplications;
//        candidateApplications = userDao.getApplicationsFiled(emailId);
//        if(!candidateApplications.isEmpty())
//            return ResponseEntity.ok(candidateApplications);
//        return new ResponseEntity<>(candidateApplications, HttpStatus.BAD_REQUEST);
//    }
//
//}
//
package dc.on.persona_predictor.service;

import dc.on.persona_predictor.constants.Constant;
import dc.on.persona_predictor.constants.MessageLog;
import dc.on.persona_predictor.entity.*;
import dc.on.persona_predictor.repository.UserDataRepository;
import dc.on.persona_predictor.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserDataRepository userData;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AsyncService asyncService;


    public String signUp(UserInfo userInfo) {
        return handleSignUp(userInfo);
    }
    private String handleSignUp(UserInfo userInfo) {
        try {
            boolean isOrg = Optional.ofNullable(userInfo.getIsorganization())
                    .map(s -> s.equalsIgnoreCase(Constant.YES))
                    .orElse(false);
            userInfo.setType(isOrg ? Constant.ORG: Constant.JOB_SEEKER);
            userInfo.setPersonality_test(Constant.NO);
            logger.info(MessageLog.USER_DATA_LOG, userInfo);
            UserInfo dataSaved = userData.save(userInfo);
            return Optional.of(dataSaved.getId())
                    .filter(id -> id > 0)
                    .map(id -> MessageLog.SUCCESS_REGISTERED)
                    .orElse(MessageLog.REGISTRATION_FAILED);
        } catch (Exception e) {
            logger.info(MessageLog.SIGNUP_FAILED, userInfo.getFirst_name(), e.getMessage());
            return MessageLog.ALREADY_REGISTERED;
        }
    }

    public ResponseEntity<ApiResponse<JobDetailsTypes>> getDetails(UserInfo userInfo) {
        return handleGetDetails(userInfo);
    }
    private ResponseEntity<ApiResponse<JobDetailsTypes>> handleGetDetails(UserInfo userInfo) {
        UserInfo userEntireInfo = verifyUser(userInfo);
        String message = userEntireInfo == null ? MessageLog.USER_NAME_PASSWORD_INCORRECT : Constant.EMPTY_STRING;
        List<JobDetails> jobDetails = userEntireInfo != null  ? getUserDetails(userEntireInfo)  : Collections.emptyList();
        String userName = Constant.EMPTY_STRING;
        if (userEntireInfo != null) {
            userName = userEntireInfo.getType().equals(Constant.ORG)  ? userEntireInfo.getCompanyname() : userEntireInfo.getFirst_name();
        }
        JobDetailsTypes jobDetailsTypes = new JobDetailsTypes(
                jobDetails,
                userEntireInfo != null ? userEntireInfo.getEmail_id() :  Constant.EMPTY_STRING,
                userName,
                userEntireInfo != null ? userEntireInfo.getType() : Constant.EMPTY_STRING,
                userEntireInfo != null ? userEntireInfo.getPersonality_test() :  Constant.EMPTY_STRING
        );
        ApiResponse<JobDetailsTypes> apiResponse = new ApiResponse<>(
                message.isEmpty() ? MessageLog.SUCCESSFULLY_LOGGED_IN : message,
                message.isEmpty() ? Constant.SUCCESS : Constant.ERROR,
                jobDetailsTypes
        );
        return message.isEmpty() ? ResponseEntity.ok(apiResponse)  : new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse<String>> saveJdRequest(JobDetails jobDetails) {
        return handleSaveJdRequest(jobDetails);
    }

    private ResponseEntity<ApiResponse<String>> handleSaveJdRequest(JobDetails jobDetails) {
        String response = userDao.saveJobRequest(jobDetails);
        ApiResponse<String> apiResponse = new ApiResponse<>(response, response, response);
        return ResponseEntity.ok(apiResponse);
    }
    public ResponseEntity<ApiResponse<String>> requestedJobPostAction(Integer id, String action) {
        return handleRequestedJobPostAction(id, action);
    }
    private ResponseEntity<ApiResponse<String>> handleRequestedJobPostAction(Integer id, String action) {
        String response = userDao.updateAction(id, action);
        if (MessageLog.STATUS_UPDATED_SUCCESSFULLY.equals(response)) {
            return ResponseEntity.ok(new ApiResponse<>(response, response, response));
        } else {
            return new ResponseEntity<>(new ApiResponse<>(response, "error", response), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ApiResponse<String>> saveUserPersonality(String dominantPersonality, String emailId) {
        return handleSaveUserPersonality(dominantPersonality, emailId);
    }
    private ResponseEntity<ApiResponse<String>> handleSaveUserPersonality(String dominantPersonality, String emailId) {
        String result = userDao.insertAndUpdatePersonality(dominantPersonality, emailId, Constant.YES);
        return ResponseEntity.ok(new ApiResponse<>(result, Constant.SUCCESS, result));
    }

    public ResponseEntity<String> forwardResumeToPython(MultipartFile file, String jobId, String emailId, String job_description, String job_title) {
        return handleForwardResumeToPython(file, jobId, emailId, job_description, job_title);
    }

    private ResponseEntity<String> handleForwardResumeToPython(MultipartFile file, String jobId, String emailId, String job_description, String job_title) {
        assert file != null && jobId != null && Integer.parseInt(jobId) > 0;
        asyncService.getResumeDetails(file, jobId, emailId, job_description, job_title);
        return ResponseEntity.status(HttpStatus.OK)
                .body(MessageLog.MSG_RESUME_SUBMITTED);
    }

    public ResponseEntity<String> checkIfAlreadyApplied(String jobId, String emailId) {
        return handleCheckIfAlreadyApplied(jobId, emailId);
    }
    private ResponseEntity<String> handleCheckIfAlreadyApplied(String jobId, String emailId) {
        boolean exists = userDao.checkApplication(jobId, emailId);
        String message = exists
                ?  MessageLog.USER_HAS_ALREADY_APPLIED
                : MessageLog.USER_HAS_APPLIED_FIRST_TIME;
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<List<ResumeInfo>> getAppliedUsersInfo(String jobId) {
        return handleGetAppliedUsersInfo(jobId);
    }

    private ResponseEntity<List<ResumeInfo>> handleGetAppliedUsersInfo(String jobId) {
        List<ResumeInfo> resumeInfo = userDao.getAppliedCandidates(jobId);
        if (!resumeInfo.isEmpty()) {
            return ResponseEntity.ok(resumeInfo);
        }
        return new ResponseEntity<>(resumeInfo, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<CandidateApplication>> getCandidateApplication(String emailId) {
        return handleGetCandidateApplication(emailId);
    }

    private ResponseEntity<List<CandidateApplication>> handleGetCandidateApplication(String emailId) {
        List<CandidateApplication> candidateApplications = userDao.getApplicationsFiled(emailId);
        if (!candidateApplications.isEmpty()) {
            return ResponseEntity.ok(candidateApplications);
        }
        return new ResponseEntity<>(candidateApplications, HttpStatus.BAD_REQUEST);
    }

    private UserInfo verifyUser(UserInfo userInfo) {
        try {
            UserInfo user = userDao.isValideUser(userInfo.getEmail_id(), userInfo.getPassword());
            if (user != null && user.getId() > 0) {
                return user;
            }
        } catch (Exception e) {
            logger.error(MessageLog.ERROR_VERIFYING_USER, e);
        }
        return null;
    }

    private List<JobDetails> getUserDetails(UserInfo userInfo) {
        if (userInfo.getType().equalsIgnoreCase(Constant.ORG)) {
            return userDao.getOrgListedJobs(userInfo.getCompanyname());
        } else if (userInfo.getType().equalsIgnoreCase(Constant.JOB_SEEKER)) {
            return userDao.getListedJobs();
        }
        return userDao.getAllJobs();
    }
}
