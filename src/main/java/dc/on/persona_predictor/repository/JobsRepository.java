package dc.on.persona_predictor.repository;

import dc.on.persona_predictor.entity.JobDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository  extends JpaRepository<JobDetails, Integer> {
    @Query("SELECT j FROM JobDetails j WHERE j.organization = :companyname and j.status='Approved'")
    List<JobDetails> getOrgData(@Param("companyname") String companyname);

    @Query("SELECT e FROM JobDetails e WHERE e.deadline >= CURRENT_DATE and e.status='Approved' ")
    List<JobDetails> getListedJobs();

    @Query("SELECT e FROM JobDetails e WHERE e.status = 'Pending'")
    List<JobDetails> getRequestedPostings();

    @Modifying
    @Query("Update JobDetails jd set jd.status =:action where jd.job_id =:id")
    Integer updateJobPostingReq(@Param("id") Integer id, @Param("action") String action);
}
