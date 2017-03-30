package com.yberdaliyev.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "admins", schema = "main")
public class AdminEntity {
  @Id
  @GeneratedValue
  private Long id;
  private String firstname;
  private String lastname;
  private String patronymic;
  @Temporal(TemporalType.DATE)
  private Date birthdate;
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
  @JoinColumn(name = "login",referencedColumnName = "login")
  private LoginEntity login;


  @Version
  private Long version;

  public AdminEntity() {}

  public AdminEntity(String firstname,
                     String lastname,
                     String patronymic,
                     Date birthdate,
                     LoginEntity login
                     ) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.patronymic = patronymic;
    this.birthdate = birthdate;
    this.login = login;

  }

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



}
