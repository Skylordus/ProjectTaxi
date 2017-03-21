package com.yberdaliyev.models.pojos;

import java.sql.Date;

public class Admin extends User {
  private long id;
  private String firstname;
  private String lastname;
  private String patronymic;
  private java.sql.Date birthdate;
  private String login;
  private String email;
  private String pwd;

  public Admin(long id, String firstname, String lastname, String patronymic, Date birthdate, String login, String email, String pwd) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.patronymic = patronymic;
    this.birthdate = birthdate;
    this.login = login;
    this.email = email;
    this.pwd = pwd;
  }

  public Admin() {}

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public java.sql.Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(java.sql.Date birthdate) {
    this.birthdate = birthdate;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
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
}
