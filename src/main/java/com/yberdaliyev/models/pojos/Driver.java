package com.yberdaliyev.models.pojos;

import java.sql.Date;

public class Driver extends User{
  private long id;
  private int experience_years;
  private Car car;
  private String firstname;
  private String lastname;
  private String patronymic;
  private java.sql.Date birthdate;
  private String login;
  private String email;
  private Order order;
  private String pwd;

  public Driver() {}

  public Driver(long id, int experience_years, Car car, String firstname, String lastname, String patronymic, Date birthdate, String login, String email, Order order, String pwd) {
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

  @Override
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

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
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

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
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
