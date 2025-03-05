package dc.on.persona_predictor.repository;

import dc.on.persona_predictor.entity.JobDetails;
import dc.on.persona_predictor.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobsRepository  extends JpaRepository<JobDetails, Integer> {
    @Query("SELECT j FROM JobDetails j WHERE j.organization = :companyname")
    List<JobDetails> getOrgData(@Param("companyname") String companyname);

    @Query("SELECT e FROM JobDetails e WHERE e.deadline >= CURRENT_DATE")
    List<JobDetails> getListedJobs();
}
