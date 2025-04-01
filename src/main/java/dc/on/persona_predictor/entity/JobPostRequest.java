//package dc.on.persona_predictor.entity;
//
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import java.util.Date;
//
//@Entity
//@Table(name = "requested_Jobpost", schema = "persona_predictor")
//@Getter
//@Setter
//public class JobPostRequest {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public Long id;
//
//    @Column(name = "organization_name", length = 50)
//    public String organization_name;
//
//    @Column(name = "email_id", length = 50)
//    public String email_id;
//
//    @Column(name = "job_description", nullable = false, columnDefinition = "TEXT")
//    public String job_description;
//
//    @Column(name = "deadline")
////    public LocalDate deadline;
//      public Date deadline;
//
//    @Column(name = "status", length = 10)
//    public String status;
//
//    @Column(name = "job_title", nullable = false)
//    public String job_title;
//
//
////    CREATE TABLE persona_predictor.requested_Jobpost (
////            id Serial Primary key,
////    job_title VARCHAR(50),
////    job_description TEXT NOT NULL,
////    deadline DATE,
////    organization_name VARCHAR(50),
////    email_id VARCHAR(50),
////    status VARCHAR(10)
////);
//}
