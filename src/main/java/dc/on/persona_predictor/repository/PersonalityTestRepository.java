package dc.on.persona_predictor.repository;


import dc.on.persona_predictor.entity.PersonalityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalityTestRepository extends JpaRepository<PersonalityType, Integer> {

    @Query("SELECT p.personality from PersonalityType p where p.email_id=:emailId")
    String getPersonality(@Param("emailId") String emailId);
}
