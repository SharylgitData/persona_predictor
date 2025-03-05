package dc.on.persona_predictor.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;


@Entity
@Table(name="user_info", schema = "persona_predictor")
@Data
@Getter

public class UserInfo {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name="first_name")
    public String first_name;

    @Column(name="last_name")
    public String last_name;

    @Column(name="email_id")
    public String email_id;

    @Column(name="password")
    public String password;

    @Column(name="mobile")
    public String mobile;

    @Column(name="companyname")
    public String companyname;

    @Column(name="isorganization")
    public String isorganization;

    @Column(name="type")
    public String type;

}
