package com.yberdaliyev.models.pojos;


import java.util.Date;

public class Client extends User{
  private Long id;
  private String firstname;
  private String lastname;
  private String patronymic;
  private Date date_registered;
  private Integer orders_amount;
  private Date birthdate;
  private String login;
  private String email;
  private Order order;
  private String pwd;


  public Client() {}

  public Client(Long id, String firstname, String lastname, String patronymic, Date date_registered, Integer orders_amount, Date birthdate, String login, String email, Order order, String pwd) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.patronymic = patronymic;
    this.date_registered = date_registered;
    this.orders_amount = orders_amount;
    this.birthdate = birthdate;
    this.login = login;
    this.email = email;
    this.order = order;
    this.pwd = pwd;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
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

  public Date getDate_registered() {
    return date_registered;
  }

  public void setDate_registered(Date date_registered) {
    this.date_registered = date_registered;
  }

  public Integer getOrders_amount() {
    return orders_amount;
  }

  public void setOrders_amount(Integer orders_amount) {
    this.orders_amount = orders_amount;
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

}

