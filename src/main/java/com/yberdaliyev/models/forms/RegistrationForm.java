package com.yberdaliyev.models.forms;

import com.yberdaliyev.common.validators.SpecialPasswordConstraint;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;


/**
 * Created by Yerlan on 18.03.2017.
 */

public class RegistrationForm {
    @NotNull
    private String user_role;
    @NotNull
    @Size(min=2, max=10)
    private String user_name;
    @NotNull
    @Size(min=2, max=10)
    private String user_surname;
    @Size(min=2, max=10)
    private String user_patronymic;
    @Past
    private Date user_birthdate;
    @NotNull
    @Size(min=3, max=15)
    private String user_login;
    @NotNull
    @Size(min=5, max=20)
    private String user_password;
    @NotNull
    @Size(min=3, max=30)
    @Email
    private String user_email;
    @NotNull
    @Size(min=3, max=20)
    @SpecialPasswordConstraint(message = "special password does not match")
    private String special_password;


    public RegistrationForm() {}
    public RegistrationForm(String user_role, String user_name, String user_surname, String user_patronymic, Date user_birthdate, String user_login, String user_password, String user_email,String special_password) {
        this.user_role = user_role;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_patronymic = user_patronymic;
        this.user_birthdate = user_birthdate;
        this.user_login = user_login;
        this.user_password = user_password;
        this.user_email = user_email;
        this.special_password = special_password;
    }


    public String getSpecial_password() {
        return special_password;
    }

    public void setSpecial_password(String special_password) {
        this.special_password = special_password;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public void setUser_surname(String user_surname) {
        this.user_surname = user_surname;
    }

    public String getUser_patronymic() {
        return user_patronymic;
    }

    public void setUser_patronymic(String user_patronymic) {
        this.user_patronymic = user_patronymic;
    }

    public Date getUser_birthdate() {
        return user_birthdate;
    }

    public void setUser_birthdate(Date user_birthdate) {
        this.user_birthdate = user_birthdate;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
