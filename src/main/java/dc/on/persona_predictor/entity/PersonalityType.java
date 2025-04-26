package dc.on.persona_predictor.entity;

import dc.on.persona_predictor.constants.Constant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.bcel.Const;

@Entity
@Table(name="personality", schema = Constant.SCHEMA_PERSONA_PREDICTOR)
public class PersonalityType {

    @Column(name="email_id")
    private String email_id;

    @Column(name="personality")
    private String personality;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer id;

    public  PersonalityType(String personality, String email_id){
        this.personality = personality;
        this.email_id = email_id;
    }
}
