package dc.on.persona_predictor.controller;

import dc.on.persona_predictor.entity.JobDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dc.on.persona_predictor.entity.UserInfo;
import dc.on.persona_predictor.service.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/resume")
public class User_Persona_Controller {

   @Autowired
   UserDetailsService userDetails;

    @PostMapping("/signUp")
    public String storeInfo(@RequestBody UserInfo userInfo){

        System.out.println("User data is " +userInfo);
        String isSaved = userDetails.signUp(userInfo);
        return isSaved;

    }


    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserInfo userInfo){
       List<JobDetails> jd = userDetails.getDetails(userInfo);
        return null;
    }

}
