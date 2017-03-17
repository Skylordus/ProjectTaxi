package com.yberdaliyev.models.pojos;

public class Client extends User{
  private Long id;
  private String firstname;
  private String lastname;
  private String patronymic;
  private java.sql.Date date_registered;
  private Long orders_amount;
  private String pwd;
  private java.sql.Date birthdate;
  private String login;
  private String email;
  private Long order;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public java.sql.Date getDate_registered() {
    return date_registered;
  }

  public void setDate_registered(java.sql.Date date_registered) {
    this.date_registered = date_registered;
  }

  public Long getOrders_amount() {
    return orders_amount;
  }

  public void setOrders_amount(Long orders_amount) {
    this.orders_amount = orders_amount;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public java.sql.Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(java.sql.Date birthdate) {
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

  public Long getOrder() {
    return order;
  }

  public void setOrder(Long order) {
    this.order = order;
  }
}
