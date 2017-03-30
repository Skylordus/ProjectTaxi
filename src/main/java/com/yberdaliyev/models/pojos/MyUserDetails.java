package com.yberdaliyev.models.pojos;

import com.yberdaliyev.models.enums.USER_ROLES;

import java.io.Serializable;

public class MyUserDetails implements Serializable {
  private String login;
  private String pwd;
  private USER_ROLES role;
  private boolean enabled;
  private String email;

  public MyUserDetails() {}

  public MyUserDetails(String login, String pwd, USER_ROLES role, String email, boolean enabled) {
    this.login = login;
    this.pwd = pwd;
    this.role = role;
    this.email = email;
    this.enabled = enabled;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public USER_ROLES getRole() {
    return role;
  }

  public void setRole(USER_ROLES role) {
    this.role = role;
  }

  public boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
