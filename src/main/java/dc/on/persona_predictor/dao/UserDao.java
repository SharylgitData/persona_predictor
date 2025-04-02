
package dc.on.persona_predictor.dao;

import dc.on.persona_predictor.entity.*;
import dc.on.persona_predictor.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserInfo isValideUser(String emailId, String password) {
        try {
            UserInfo userDetails = userData.validateUser(emailId, password);
            if (userDetails != null && userDetails.id > 0)
                return userDetails;
            return null;
        } catch (Exception e) {
            logger.error("Error validating user: {}" , e.getMessage());
            return null;
        }
    }

    public List<JobDetails> getOrgListedJobs(String companyname) {
        try {
            return jobsRepository.getOrgData(companyname);
        } catch (Exception e) {
            logger.error("Error fetching org jobs: {}" , e.getMessage());
            return List.of();
        }
    }

    public List<JobDetails> getListedJobs() {
        try {
            return jobsRepository.getListedJobs();
        } catch (Exception e) {
            logger.error("Error fetching listed jobs: {}" , e.getMessage());
            return List.of();
        }
    }

    public List<JobDetails> getAllJobs() {
        try {
            return jobsRepository.getRequestedPostings();
        } catch (Exception e) {
            logger.error("Error fetching all jobs: {}" , e.getMessage());
            return List.of();
        }
    }

    public String saveJobRequest(JobDetails jobPostRequest) {
        try {
            JobDetails jobreq = jobsRepository.save(jobPostRequest);
            if (jobreq.job_id > 0) {
                return "Your job request has been successfully sent to the Admin";
            }
            return "Something went wrong while saving the request";
        } catch (Exception e) {
            logger.error("Error saving job request: {} " , e.getMessage());
            return "Exception occurred while saving job request";
        }
    }

    @Transactional
    public String updateAction(Integer id, String action) {
        try {
            Integer result = jobsRepository.updateJobPostingReq(id, action);
            return result > 0 ? "Status Updated successfully" : "Status Update failed";
        } catch (Exception e) {
            logger.error("Error updating job status: {} " , e.getMessage());
            return "Exception occurred while updating job status";
        }
    }

    @Transactional
    public String insertAndUpdatePersonality(String dominantPersonality, String emailId, String isGiven) {
        try {
            Integer user = userData.updateTest(emailId, isGiven);

            PersonalityType personalityType = new PersonalityType(dominantPersonality, emailId);

            //get the personality for the email id if exists
            String personality = personalityTestRepository.getPersonality(emailId);

            if(Optional.ofNullable(personality).isPresent())
                personalityTestRepository.updateUserPersonality(dominantPersonality, emailId);
            else
                personalityTestRepository.save(personalityType);

            if (personalityType != null && personalityType.id > 0) {
                return "Personality of email-Id- " + emailId + " has been saved successfully";
            }
            return "Personality save failed for " + emailId;
        } catch (Exception e) {
            logger.error("Error saving personality: {}",  e.getMessage());
            return "Exception occurred while saving user's personality";
        }
    }

    public String getPersonality(String emailId) {
        try {
            return personalityTestRepository.getPersonality(emailId);
        } catch (Exception e) {
            logger.error("Error retrieving personality: {}" , e.getMessage());
            return null;
        }
    }

    public void saveResumeInfo(ResumeInfo resumeInfo) {
        try {
            resumeInfoRepository.save(resumeInfo);
        } catch (Exception e) {
            logger.error("Error saving resume info: {}" , e.getMessage());
        }
    }

    public Boolean checkApplication(String jobId, String emailId) {
        try {
            Long count = resumeInfoRepository.ifAlreadyFiled(jobId, emailId);
            if(count>0)
                return true;

        } catch (Exception e) {
            logger.error("Error finding if the user has applied for the job: {} ", e.getMessage());
        }

        return false;
    }

    public List<ResumeInfo> getAppliedCandidates(String jobId) {
        try {
            List<ResumeInfo> resumeInfo = resumeInfoRepository.getAppliedUsers(jobId);

            if (!resumeInfo.isEmpty()) {
                return resumeInfo;
            }
        }catch (Exception e) {
            logger.error("Error while finding the details of the applied candidates for the job id {} . The error is {}" ,jobId, e.getMessage());
        }
        return null;
    }
}
