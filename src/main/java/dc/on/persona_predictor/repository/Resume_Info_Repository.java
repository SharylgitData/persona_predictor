package dc.on.persona_predictor.repository;

import dc.on.persona_predictor.entity.ResumeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Resume_Info_Repository extends JpaRepository<ResumeInfo, Integer> {

    @Query("select count(1) from ResumeInfo r where r.job_id=:jobId and r.jobseeker_email_id=:emailId")
    Long ifAlreadyFiled(@Param("jobId") String jobId, @Param("emailId") String emailId);

    @Query("select r from ResumeInfo r where r.job_id=:jobId order by r.rank desc")
    List<ResumeInfo> getAppliedUsers(@Param("jobId") String jobId);
}
