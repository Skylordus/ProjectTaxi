package com.yberdaliyev.models.pojos;

public abstract class User {

  public abstract Long getId();

  public abstract void setId(Long id);

  public abstract String getFirstname();

  public abstract void setFirstname(String firstname);

  public abstract String getLastname();

  public abstract void setLastname(String lastname);

  public abstract String getPatronymic();

  public abstract void setPatronymic(String patronymic);

  public abstract java.sql.Date getBirthdate();

  public abstract void setBirthdate(java.sql.Date birthdate);

  public abstract String getPwd();

  public abstract void setPwd(String pwd);

  public abstract String getLogin();

  public abstract void setLogin(String login);

  public abstract String getEmail();

  public abstract void setEmail(String email);

}
