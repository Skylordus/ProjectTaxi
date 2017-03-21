package com.yberdaliyev.models.entities;

import com.yberdaliyev.models.pojos.User;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "drivers", schema = "main")
public class DriverEntity {
  @Id
  @GeneratedValue
  private long id;

  private int experience_years;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "car",referencedColumnName = "id")
  private CarEntity car;

  private String firstname;
  private String lastname;
  private String patronymic;
  private java.sql.Date birthdate;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
  @JoinColumn(name = "login",referencedColumnName = "login")
  private LoginEntity login;

  private String email;

  @Column(name = "\"order\"")
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "\"order\"",referencedColumnName = "id")
  private OrderEntity order;

  public DriverEntity() {}

  public DriverEntity(int experience_years, CarEntity car, String firstname, String lastname, String patronymic, Date birthdate, LoginEntity login, String email, OrderEntity order) {
    this.experience_years = experience_years;
    this.car = car;
    this.firstname = firstname;
    this.lastname = lastname;
    this.patronymic = patronymic;
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

  public int getExperience_years() {
    return experience_years;
  }

  public void setExperience_years(int experience_years) {
    this.experience_years = experience_years;
  }

  public CarEntity getCar() {
    return car;
  }

  public void setCar(CarEntity car) {
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

  @Override
  public String toString() {
    return firstname+" "+patronymic+" "+lastname;
  }
}
