package com.yberdaliyev.entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "drivers", schema = "main")
public class DriverEntity {
  @Id
  @GeneratedValue
  private Long id;

  private Integer experience_years;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "car",referencedColumnName = "id")
  private CarEntity car;

  private String firstname;
  private String lastname;
  private String patronymic;
  @Temporal(TemporalType.DATE)
  private Date birthdate;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "login",referencedColumnName = "login")
  private LoginEntity login;



  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "\"order\"")
  private OrderEntity order;

  @Version
  private Long version;

  public DriverEntity() {}

  public DriverEntity(Integer experience_years, CarEntity car, String firstname, String lastname, String patronymic, Date birthdate, LoginEntity login,  OrderEntity order) {
    this.experience_years = experience_years;
    this.car = car;
    this.firstname = firstname;
    this.lastname = lastname;
    this.patronymic = patronymic;
    this.birthdate = birthdate;
    this.login = login;
    this.order = order;
  }

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
