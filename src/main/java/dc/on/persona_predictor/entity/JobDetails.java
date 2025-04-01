package dc.on.persona_predictor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name="jobs_inventory", schema = "persona_predictor")
@Getter
@Setter
public class JobDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    public Integer job_id;


    @Column(name = "job_description", columnDefinition = "TEXT")
    public String job_description;

    @Column(name = "deadline")
    public Date deadline;

    @Column(name = "organization")
    public String organization;

    @Column(name = "type")
    public String type;

    @Column(name = "status")
    public String status;

    @Column(name="job_title")
    public String job_title;





//    CREATE TABLE persona_predictor.job_details (
//    jobId SERIAL PRIMARY KEY,
//    job_title VARCHAR(50),
//    job_description TEXT NOT NULL,
//    organization VARCHAR(50),
//    deadline DATE,
//    status VARCHAR(10),
//
//    type VARCHAR(20)
//
//);
}
