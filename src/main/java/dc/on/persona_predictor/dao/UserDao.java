//
//package dc.on.persona_predictor.dao;
//
//import dc.on.persona_predictor.constants.MessageLog;
//import dc.on.persona_predictor.entity.*;
//import dc.on.persona_predictor.repository.*;
//import jakarta.transaction.Transactional;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.Optional;
//
//
//@Service
//public class UserDao {
//    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
//
//    @Autowired
//    private UserDataRepository userData;
//
//    @Autowired
//    private JobsRepository jobsRepository;
//
//    @Autowired
//    private PersonalityTestRepository personalityTestRepository;
//
//    @Autowired
//    private Resume_Info_Repository resumeInfoRepository;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//
//
//    public UserInfo isValideUser(String emailId, String password) {
//        try {
//            UserInfo userDetails = userData.validateUser(emailId, password);
//            if (userDetails != null && userDetails.id > 0)
//                return userDetails;
//
//        } catch (Exception e) {
//            logger.error(MessageLog.ERROR_VALIDATING_USER, e.getMessage());
//        }
//        return null;
//    }
//
//    public List<JobDetails> getOrgListedJobs(String companyname) {
//        try {
//            return jobsRepository.getOrgData(companyname);
//        } catch (Exception e) {
//            logger.error(MessageLog.ERROR_FETCHING_ORG_JOBS , e.getMessage());
//            return List.of();
//        }
//    }
//
//    public List<JobDetails> getListedJobs() {
//        try {
//            return jobsRepository.getListedJobs();
//        } catch (Exception e) {
//            logger.error(MessageLog.ERROR_FETCHING_LISTED_JOBS , e.getMessage());
//            return List.of();
//        }
//    }
//
//    public List<JobDetails> getAllJobs() {
//        try {
//            return jobsRepository.getRequestedPostings();
//        } catch (Exception e) {
//            logger.error(MessageLog.ERROR_FETCHING_ALL_JOBS , e.getMessage());
//            return List.of();
//        }
//    }
//
//    public String saveJobRequest(JobDetails jobPostRequest) {
//        try {
//            JobDetails jobreq = jobsRepository.save(jobPostRequest);
//            if (jobreq.job_id > 0) {
//                return MessageLog.JOB_REQUEST_SUCCESS;
//            }
//            return MessageLog.ERROR_SOEMETHING_WENT_WRONG;
//        } catch (Exception e) {
//            logger.error(MessageLog.ERROR_SAVING_JOB_REQUEST , e.getMessage());
//            return MessageLog.ERROR_SOEMETHING_WENT_WRONG;
//        }
//    }
//
//    @Transactional
//    public String updateAction(Integer id, String action) {
//        try {
//            Integer result = jobsRepository.updateJobPostingReq(id, action);
//            return result > 0 ? MessageLog.MSG_STATUS_UPDATED_SUCCESSFULLY: MessageLog.MSG_STATUS_UPDATE_FAILED;
//        } catch (Exception e) {
//            logger.error(MessageLog.ERROR_UPDATING_JOB_STATUS , e.getMessage());
//            return MessageLog.MSG_EXCEPTION_UPDATING_JOB_STATUS;
//        }
//    }
//
//    @Transactional
//    public String insertAndUpdatePersonality(String dominantPersonality, String emailId, String isGiven) {
//        try {
//            Integer user = userData.updateTest(emailId, isGiven);
//
//            PersonalityType personalityType = new PersonalityType(dominantPersonality, emailId);
//
//            //get the personality for the email id if exists
//            String personality = personalityTestRepository.getPersonality(emailId);
//
//            if(Optional.ofNullable(personality).isPresent())
//                personalityTestRepository.updateUserPersonality(dominantPersonality, emailId);
//            else
//                personalityTestRepository.save(personalityType);
//
//            if (personalityType != null && personalityType.id > 0) {
//                return String.format(MessageLog.MSG_PERSONALITY_SAVED_SUCCESSFULLY, emailId);
//            }
//            return String.format(MessageLog.MSG_PERSONALITY_SAVE_FAILED, emailId);
//        } catch (Exception e) {
//            logger.error(MessageLog.ERROR_SAVING_PERSONALITY,  e.getMessage());
//            return MessageLog.MSG_EXCEPTION_SAVING_USER_PERSONALITY;
//        }
//    }
//
//    public String getPersonality(String emailId) {
//        try {
//            return personalityTestRepository.getPersonality(emailId);
//        } catch (Exception e) {
//            logger.error(MessageLog.ERROR_RETRIEVING_PERSONALITY , e.getMessage());
//            return null;
//        }
//    }
//
//    public void saveResumeInfo(ResumeInfo resumeInfo) {
//        try {
//            resumeInfoRepository.save(resumeInfo);
//        } catch (Exception e) {
//            logger.error(MessageLog.ERROR_SAVING_RESUME_INFO , e.getMessage());
//        }
//    }
//
//    public Boolean checkApplication(String jobId, String emailId) {
//        try {
//            Long count = resumeInfoRepository.ifAlreadyFiled(jobId, emailId);
//            if(count>0)
//                return true;
//
//        } catch (Exception e) {
//            logger.error(MessageLog.ERROR_CHECKING_APPLICATION, e.getMessage());
//        }
//
//        return false;
//    }
//
//    public List<ResumeInfo> getAppliedCandidates(String jobId) {
//        try {
//            List<ResumeInfo> resumeInfo = resumeInfoRepository.getAppliedUsers(jobId);
//
//            if (!resumeInfo.isEmpty()) {
//                return resumeInfo;
//            }
//        }catch (Exception e) {
//            logger.error(MessageLog.ERROR_FINDING_APPLIED_CANDIDATES,jobId, e.getMessage());
//        }
//        return null;
//    }
//
//    public List<CandidateApplication> getApplicationsFiled(String emailId){
//        String sql = new StringBuilder("SELECT js.job_id, jin.organization, jin.job_title, js.rank, js.improvement_area ")
//                .append("FROM persona_predictor.jobseeker_resume_info js ")
//                .append("JOIN persona_predictor.jobs_inventory jin ON js.job_id = jin.job_id ")
//                .append("WHERE js.email_id = ? ")
//                .append("ORDER BY js.rank")
//                .toString();
//
//        return jdbcTemplate.query(sql, new Object[]{emailId}, new CandidateApplicationMapper());
//    }
//
//
//}
package dc.on.persona_predictor.dao;

import dc.on.persona_predictor.constants.MessageLog;
import dc.on.persona_predictor.entity.*;
import dc.on.persona_predictor.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    @Autowired
    private UserDataRepository userData;

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private PersonalityTestRepository personalityTestRepository;

    @Autowired
    private Resume_Info_Repository resumeInfoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserInfo isValideUser(String emailId, String password) {
        return isValideUserInternal(emailId, password);
    }

    private UserInfo isValideUserInternal(String emailId, String password) {
        try {
            UserInfo userDetails = userData.validateUser(emailId, password);
            if (userDetails != null && userDetails.getId() > 0) {
                return userDetails;
            }
        } catch (Exception e) {
            logger.error(MessageLog.ERROR_VALIDATING_USER, e.getMessage());
        }
        return null;
    }

    public List<JobDetails> getOrgListedJobs(String companyName) {
        return getOrgListedJobsInternal(companyName);
    }

    private List<JobDetails> getOrgListedJobsInternal(String companyName) {
        try {
            return jobsRepository.getOrgData(companyName);
        } catch (Exception e) {
            logger.error(MessageLog.ERROR_FETCHING_ORG_JOBS, e.getMessage());
            return List.of();
        }
    }

    public List<JobDetails> getListedJobs() {
        return getListedJobsInternal();
    }

    private List<JobDetails> getListedJobsInternal() {
        try {
            return jobsRepository.getListedJobs();
        } catch (Exception e) {
            logger.error(MessageLog.ERROR_FETCHING_LISTED_JOBS, e.getMessage());
            return List.of();
        }
    }

    public List<JobDetails> getAllJobs() {
        return getAllJobsInternal();
    }

    private List<JobDetails> getAllJobsInternal() {
        try {
            return jobsRepository.getRequestedPostings();
        } catch (Exception e) {
            logger.error(MessageLog.ERROR_FETCHING_ALL_JOBS, e.getMessage());
            return List.of();
        }
    }

    public String saveJobRequest(JobDetails jobPostRequest) {
        return saveJobRequestInternal(jobPostRequest);
    }

    private String saveJobRequestInternal(JobDetails jobPostRequest) {
        try {
            JobDetails jobreq = jobsRepository.save(jobPostRequest);
            if (jobreq.getJob_id() > 0) {
                return MessageLog.JOB_REQUEST_SUCCESS;
            }
            return MessageLog.ERROR_SOEMETHING_WENT_WRONG;
        } catch (Exception e) {
            logger.error(MessageLog.ERROR_SAVING_JOB_REQUEST, e.getMessage());
            return MessageLog.ERROR_SOEMETHING_WENT_WRONG;
        }
    }

    @Transactional
    public String updateAction(Integer id, String action) {
        return updateActionInternal(id, action);
    }

    private String updateActionInternal(Integer id, String action) {
        try {
            Integer result = jobsRepository.updateJobPostingReq(id, action);
            return result > 0 ? MessageLog.MSG_STATUS_UPDATED_SUCCESSFULLY : MessageLog.MSG_STATUS_UPDATE_FAILED;
        } catch (Exception e) {
            logger.error(MessageLog.ERROR_UPDATING_JOB_STATUS, e.getMessage());
            return MessageLog.MSG_EXCEPTION_UPDATING_JOB_STATUS;
        }
    }

    @Transactional
    public String insertAndUpdatePersonality(String dominantPersonality, String emailId, String isGiven) {
        return insertAndUpdatePersonalityInternal(dominantPersonality, emailId, isGiven);
    }

    private String insertAndUpdatePersonalityInternal(String dominantPersonality, String emailId, String isGiven) {
        try {
            userData.updateTest(emailId, isGiven);
            PersonalityType personalityType = new PersonalityType(dominantPersonality, emailId);
            String existing = personalityTestRepository.getPersonality(emailId);

            if (Optional.ofNullable(existing).isPresent()) {
                personalityTestRepository.updateUserPersonality(dominantPersonality, emailId);
            } else {
                personalityTestRepository.save(personalityType);
            }

            if (personalityType != null && personalityType.id > 0) {
                return String.format(MessageLog.MSG_PERSONALITY_SAVED_SUCCESSFULLY, emailId);
            }
            return String.format(MessageLog.MSG_PERSONALITY_SAVE_FAILED, emailId);
        } catch (Exception e) {
            logger.error(MessageLog.ERROR_SAVING_PERSONALITY, e.getMessage());
            return MessageLog.MSG_EXCEPTION_SAVING_USER_PERSONALITY;
        }
    }

    public String getPersonality(String emailId) {
        return getPersonalityInternal(emailId);
    }

    private String getPersonalityInternal(String emailId) {
        try {
            return personalityTestRepository.getPersonality(emailId);
        } catch (Exception e) {
            logger.error(MessageLog.ERROR_RETRIEVING_PERSONALITY, e.getMessage());
            return null;
        }
    }

    public void saveResumeInfo(ResumeInfo resumeInfo) {
        saveResumeInfoInternal(resumeInfo);
    }

    private void saveResumeInfoInternal(ResumeInfo resumeInfo) {
        try {
            resumeInfoRepository.save(resumeInfo);
        } catch (Exception e) {
            logger.error(MessageLog.ERROR_SAVING_RESUME_INFO, e.getMessage());
        }
    }

    public Boolean checkApplication(String jobId, String emailId) {
        return checkApplicationInternal(jobId, emailId);
    }

    private Boolean checkApplicationInternal(String jobId, String emailId) {
        try {
            Long count = resumeInfoRepository.ifAlreadyFiled(jobId, emailId);
            return count > 0;
        } catch (Exception e) {
            logger.error(MessageLog.ERROR_CHECKING_APPLICATION, e.getMessage());
            return false;
        }
    }

    public List<ResumeInfo> getAppliedCandidates(String jobId) {
        return getAppliedCandidatesInternal(jobId);
    }

    private List<ResumeInfo> getAppliedCandidatesInternal(String jobId) {
        try {
            List<ResumeInfo> list = resumeInfoRepository.getAppliedUsers(jobId);
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception e) {
            logger.error(MessageLog.ERROR_FINDING_APPLIED_CANDIDATES, jobId, e.getMessage());
        }
        return null;
    }

    public List<CandidateApplication> getApplicationsFiled(String emailId) {
        return getApplicationsFiledInternal(emailId);
    }

    private List<CandidateApplication> getApplicationsFiledInternal(String emailId) {
        String sql = new StringBuilder()
                .append("SELECT js.job_id, jin.organization, jin.job_title, js.rank, js.improvement_area ")
                .append("FROM persona_predictor.jobseeker_resume_info js ")
                .append("JOIN persona_predictor.jobs_inventory jin ON js.job_id = jin.job_id ")
                .append("WHERE js.email_id = ? ")
                .append("ORDER BY js.rank")
                .toString();

        return jdbcTemplate.query(sql, new Object[]{emailId}, new CandidateApplicationMapper());
    }
}
