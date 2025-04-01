package dc.on.persona_predictor.repository;

import dc.on.persona_predictor.entity.JobDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import dc.on.persona_predictor.entity.UserInfo;

import java.util.List;

@Repository
public interface UserDataRepository extends JpaRepository<UserInfo, Integer> {
//   @Query("SELECT * FROM JobDetails WHERE type=?1")
//    List<JobDetails> findDetails(UserInfo userInfo);

   @Query("Select u from UserInfo u where u.email_id = :email_id and u.password = :password")
   UserInfo validateUser(@Param("email_id") String email_id, @Param("password") String password);

    @Modifying
    @Query("update UserInfo u set u.personality_test=:given where u.email_id = :emailId")
    Integer updateTest(@Param("emailId") String emailId, @Param("given") String given);
}
