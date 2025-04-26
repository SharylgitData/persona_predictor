package dc.on.persona_predictor.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobDetailsTypes {
    private List<JobDetails> jobDetails;
    private String emailId;
    private String userName;
    private String type;
    private String personality_test;

    public JobDetailsTypes(List<JobDetails> jobDetails, String emailId, String userName,  String type, String personality_test) {
        this.jobDetails = jobDetails;
        this.emailId = emailId;
        this.userName = userName;
        this.type = type;
        this.personality_test = personality_test;
    }
}
