package dc.on.persona_predictor.entity;

import dc.on.persona_predictor.constants.Constant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.lang.module.Configuration;


@Entity
@Table(name=Constant.TABLE_USER_INFO, schema = Constant.SCHEMA_PERSONA_PREDICTOR)
@Data
@Getter

public class UserInfo {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="email_id")
    private String email_id;

    @Column(name="password")
    private String password;

    @Column(name="mobile")
    private String mobile;

    @Column(name="companyname")
    private String companyname;

    @Column(name="isorganization")
    private String isorganization;

    @Column(name="type")
    private String type;

    @Column(name="personality_test")
    private String personality_test;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getIsorganization() {
        return isorganization;
    }

    public void setIsorganization(String isorganization) {
        this.isorganization = isorganization;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPersonality_test() {
        return personality_test;
    }

    public void setPersonality_test(String personality_test) {
        this.personality_test = personality_test;
    }

    //    insert into  persona_predictor.user_info(first_name, last_name, email_id, password, mobile,
//    companyname, isorganization, type) values
//            ('Admin', 'Admin', 'admin@dcmail.ca', 'admin@1234', '2262199131', 'No', 'admin')
//

}
