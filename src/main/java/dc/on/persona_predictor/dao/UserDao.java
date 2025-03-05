package dc.on.persona_predictor.dao;

import dc.on.persona_predictor.entity.JobDetails;
import dc.on.persona_predictor.entity.UserInfo;
import dc.on.persona_predictor.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dc.on.persona_predictor.repository.JobsRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserDao {
    @Autowired
    UserDataRepository userData;

    @Autowired
    JobsRepository jobsRepository;


    public UserInfo isValideUser(String emailId, String password) {
        UserInfo userDetails = userData.validateUser(emailId, password);
        if (Optional.ofNullable(userDetails).get().id > 0)
            return userDetails;
        return userDetails;
    }

    public List<JobDetails> getOrgListedJobs(String companyname) {
        List<JobDetails> jobDetails = jobsRepository.getOrgData(companyname);
        return  jobDetails;
    }

    public List<JobDetails> getListedJobs() {
        List<JobDetails> jobDetails = jobsRepository.getListedJobs();
        return  jobDetails;
    }

    public List<JobDetails> getAllJobs() {
        List<JobDetails> jobDetails = jobsRepository.findAll();
        return  jobDetails;
    }

}
