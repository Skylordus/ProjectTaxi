package com.yberdaliyev.models.forms;

import java.sql.Date;

/**
 * Created by Yerlan on 20.03.2017.
 */
public class DriverTableForm {
    private long id;
    private int experience_years;
    private long car;
    private String firstname;
    private String lastname;
    private String patronymic;
    private Date birthdate;
    private String login;
    private String email;
    private long order;
    private String pwd;

    public DriverTableForm() {}

    public DriverTableForm(long id, int experience_years, long car, String firstname, String lastname, String patronymic, Date birthdate, String login, String email, long order, String pwd) {
        this.id = id;
        this.experience_years = experience_years;
        this.car = car;
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.login = login;
        this.email = email;
        this.order = order;
        this.pwd = pwd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getExperience_years() {
        return experience_years;
    }

    public void setExperience_years(int experience_years) {
        this.experience_years = experience_years;
    }

    public long getCar() {
        return car;
    }

    public void setCar(long car) {
        this.car = car;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
