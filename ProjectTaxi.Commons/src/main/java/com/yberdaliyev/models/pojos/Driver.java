package com.yberdaliyev.models.pojos;


import java.util.Date;

public class Driver extends User{
  private Long id;
  private Integer experience_years;
  private Long carid;
  private String firstname;
  private String lastname;
  private String patronymic;
  private Date birthdate;
  private String login;
  private String email;
  private Long orderid;
  private String pwd;

  public Driver() {}

  public Driver(Long id, Integer experience_years, Long carid, String firstname, String lastname, String patronymic, Date birthdate, String login, String email, Long orderid, String pwd) {
    this.id = id;
    this.experience_years = experience_years;
    this.carid = carid;
    this.firstname = firstname;
    this.lastname = lastname;
    this.patronymic = patronymic;
    this.birthdate = birthdate;
    this.login = login;
    this.email = email;
    this.orderid = orderid;
    this.pwd = pwd;
  }

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getExperience_years() {
    return experience_years;
  }

  public void setExperience_years(Integer experience_years) {
    this.experience_years = experience_years;
  }

  public Long getCar() {
    return carid;
  }

  public void setCar(Long carid) {
    this.carid = carid;
  }

  @Override
  public String getFirstname() {
    return firstname;
  }

  @Override
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  @Override
  public String getLastname() {
    return lastname;
  }

  @Override
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  @Override
  public String getPatronymic() {
    return patronymic;
  }

  @Override
  public void setPatronymic(String patronymic) {
    this.patronymic = patronymic;
  }

  @Override
  public Date getBirthdate() {
    return birthdate;
  }

  @Override
  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  @Override
  public String getLogin() {
    return login;
  }

  @Override
  public void setLogin(String login) {
    this.login = login;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  public Long getOrder() {
    return orderid;
  }

  public void setOrder(Long orderid) {
    this.orderid = orderid;
  }

  @Override
  public String getPwd() {
    return pwd;
  }

  @Override
  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  @Override
  public String toString() {
    return firstname+" "+patronymic+" "+lastname;
  }
}
