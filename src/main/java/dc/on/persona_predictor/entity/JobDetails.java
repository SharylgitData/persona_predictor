package dc.on.persona_predictor.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="user_info", schema = "persona_predictor")
@Getter
@Setter
public class JobDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer jobId;

    @Lob
    @Column(name = "job_description", columnDefinition = "TEXT")
    private String jobDescription;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "organization")
    private String organization;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;



//    CREATE TABLE persona_predictor.job_details (
//    jobId SERIAL PRIMARY KEY,
//    job_description TEXT NOT NULL,
//    deadline DATE,
//    organization VARCHAR(50),
//    type VARCHAR(20),
//    status VARCHAR(10)
//);
}
