package dc.on.persona_predictor.entity;

import dc.on.persona_predictor.constants.Constant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name="jobs_inventory", schema = Constant.SCHEMA_PERSONA_PREDICTOR)
public class JobDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Integer job_id;


    @Column(name = "job_description", columnDefinition = "TEXT")
    private String job_description;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "organization")
    private String organization;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name="job_title")
    private String job_title;

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

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
