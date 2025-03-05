package dc.on.persona_predictor.service;

import dc.on.persona_predictor.constants.Constant;
import dc.on.persona_predictor.entity.JobDetails;
import dc.on.persona_predictor.entity.UserInfo;
import dc.on.persona_predictor.repository.UserDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import dc.on.persona_predictor.dao.UserDao;

@Service
public class UserDetailsService {

    @Autowired
    UserDataRepository userData;

    @Autowired
    UserDao userDao;


    Logger logger= LoggerFactory.getLogger(UserDetailsService.class);


    public String signUp(UserInfo userInfo){
        try {
            if(Optional.ofNullable(userInfo.isorganization).get().equalsIgnoreCase("Yes"))
                userInfo.type = "organization";
            else
                userInfo.type = "jobSeeker";
            logger.info("userInfo {}", userInfo);
            UserInfo dataSaved = userData.save(userInfo);
            return Optional.ofNullable(dataSaved.id).filter(r->r>0).map(o->"Data Saved").orElse("Data already Present");
        }catch(Exception sqlException){
            logger.info("Exception occured while signing up the user {}. The exception is {}", userInfo.first_name, sqlException);
            return "Error while signing in the user";
        }
    }



    public List<JobDetails> getDetails(UserInfo userInfo) {
        List<JobDetails> jobDetails= validateAndgetDetails(userInfo);

        return jobDetails;
    }

    private List<JobDetails> validateAndgetDetails(UserInfo userInfo) {
        UserInfo userEntireInfo = verifyUser(userInfo);
        List<JobDetails> jobDetails = new ArrayList<>();
       if(!Optional.ofNullable(userEntireInfo).isEmpty()) {
           jobDetails = getUserDetails(userInfo);
       }

        return jobDetails;

    }

    private UserInfo verifyUser(UserInfo userInfo) {
        String email_id = userInfo.email_id;
        String password = userInfo.password;
        UserInfo userEntireInfo = new UserInfo();
        userEntireInfo = userDao.isValideUser(email_id, password);

        if(Optional.ofNullable(userEntireInfo).get().id > 0)
            return userEntireInfo;
        return null;
    }

    private List<JobDetails> getUserDetails(UserInfo userInfo) {
        List<JobDetails> jobDetails = new ArrayList<>();
        if(userInfo.type.equalsIgnoreCase(Constant.ORG))
            jobDetails = userDao.getOrgListedJobs(userInfo.companyname);
        else if (userInfo.type.equalsIgnoreCase(Constant.JOB_SEEKER))
            jobDetails = userDao.getListedJobs();
        else
            jobDetails = userDao.getAllJobs();
        return  jobDetails;

        }
    }

