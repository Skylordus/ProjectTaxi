package com.yberdaliyev.models.pojos;

public class Driver extends User{
  private Long id;
  private Long experience_years;
  private Long car;
  private String firstname;
  private String lastname;
  private String patronymic;
  private java.sql.Date birthdate;
  private String pwd;
  private String login;
  private String email;
  private Long order;

  public Long getOrder() {
    return order;
  }

  public void setOrder(Long order) {
    this.order = order;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getExperience_years() {
    return experience_years;
  }

  public void setExperience_years(Long experience_years) {
    this.experience_years = experience_years;
  }

  public Long getCar() {
    return car;
  }

  public void setCar(Long car) {
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

  @Override
  public String toString() {
    return firstname+" "+patronymic+" "+lastname;
  }
}
