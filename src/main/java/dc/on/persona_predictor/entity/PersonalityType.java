package dc.on.persona_predictor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@Table(name="personality", schema = "persona_predictor")
public class PersonalityType {

    @Column(name="email_id")
    public String email_id;

    @Column(name="personality")
    public String personality;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer id;

    public  PersonalityType(String personality, String email_id){
        this.personality = personality;
        this.email_id = email_id;
    }
}
