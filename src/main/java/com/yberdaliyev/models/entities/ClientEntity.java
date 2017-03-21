package com.yberdaliyev.models.entities;

import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.User;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "clients", schema = "main")
public class ClientEntity {
  @Id
  @GeneratedValue
  private long id;
  private String firstname;
  private String lastname;
  private String patronymic;
  private java.sql.Date date_registered;
  private int orders_amount;
  private java.sql.Date birthdate;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
  @JoinColumn(name = "login",referencedColumnName = "login")
  private LoginEntity login;

  private String email;

  @Column(name = "\"order\"")
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "\"order\"",referencedColumnName = "id")
  private OrderEntity order;

  public ClientEntity() {}

  public ClientEntity(String firstname, String lastname, String patronymic, Date date_registered, int orders_amount, Date birthdate, LoginEntity login, String email, OrderEntity order) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.patronymic = patronymic;
    this.date_registered = date_registered;
    this.orders_amount = orders_amount;
    this.birthdate = birthdate;
    this.login = login;
    this.email = email;
    this.order = order;
  }

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

  public Date getDate_registered() {
    return date_registered;
  }

  public void setDate_registered(Date date_registered) {
    this.date_registered = date_registered;
  }

  public int getOrders_amount() {
    return orders_amount;
  }

  public void setOrders_amount(int orders_amount) {
    this.orders_amount = orders_amount;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public LoginEntity getLogin() {
    return login;
  }

  public void setLogin(LoginEntity login) {
    this.login = login;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public OrderEntity getOrder() {
    return order;
  }

  public void setOrder(OrderEntity order) {
    this.order = order;
  }
}
