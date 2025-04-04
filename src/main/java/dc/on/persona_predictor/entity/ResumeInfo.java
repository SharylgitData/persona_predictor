package dc.on.persona_predictor.entity;


import jakarta.persistence.*;
import java.util.Map;


@Entity
@Table(name = "jobseeker_resume_info", schema = "persona_predictor")
public class ResumeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;


    @Column(name = "reason", length = 1500)
    private String rankReason;

    @Column(name = "rank")
    private int rank;


    @Column(name = "entities", length = 10000)
    private String user_entities;

    @Column(name = "job_id")
    private Integer job_id;

    @Column(name = "email_id", length = 100)
    private String jobseeker_email_id;

    // Constructors
    public ResumeInfo() {}

    public void setUser_entities(String user_entities) {
        this.user_entities = user_entities;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRankReason() {
        return rankReason;
    }
    public void setRankReason(String rankReason) {
        this.rankReason = rankReason;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

    public Integer getJob_id() {
        return job_id;
    }
    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public String getJobseeker_email_id() {
        return jobseeker_email_id;
    }
    public void setJobseeker_email_id(String jobseeker_email_id) {
        this.jobseeker_email_id = jobseeker_email_id;
    }


    @Override
    public String toString() {
        return "ResumeInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rankReason='" + rankReason + '\'' +
                ", rank=" + rank +
                ", job_id=" + job_id +
                ", jobseeker_email_id='" + jobseeker_email_id + '\'' +
                '}';
    }
}


//CREATE TABLE persona_predictor.resume_info (
//        id SERIAL PRIMARY KEY,
//        job_id INTEGER,
//        name VARCHAR(50),
//rank INTEGER,
//reason VARCHAR(500),
//entities VARCHAR(5000),
//email_id varchar(100)
//);
