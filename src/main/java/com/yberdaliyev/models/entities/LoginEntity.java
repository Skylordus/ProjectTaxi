package com.yberdaliyev.models.entities;

import com.yberdaliyev.models.enums.USER_ROLES;
import com.yberdaliyev.models.pojos.Driver;

import javax.persistence.*;

/**
 * Created by Yerlan on 20.03.2017.
 */
@Entity
@Table(name = "logins", schema = "main")
public class LoginEntity {
    @Id
    private String login;
    private String pwd;
    @Enumerated(EnumType.STRING)
    private USER_ROLES role;
    private boolean enabled;
    private String email;

    @OneToOne(mappedBy = "login", targetEntity = AdminEntity.class, cascade = CascadeType.ALL)
    private AdminEntity admin;
    @OneToOne(mappedBy = "login", targetEntity = ClientEntity.class, cascade = CascadeType.ALL)
    private ClientEntity client;
    @OneToOne(mappedBy = "login", targetEntity = DriverEntity.class, cascade = CascadeType.ALL)
    private DriverEntity driver;

    @Version
    private Long version;

    public LoginEntity(){}

    public LoginEntity(String login, String pwd, USER_ROLES role, String email, boolean enabled) {
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public AdminEntity getAdmin() {
        return admin;
    }

    public void setAdmin(AdminEntity admin) {
        this.admin = admin;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public DriverEntity getDriver() {
        return driver;
    }

    public void setDriver(DriverEntity driver) {
        this.driver = driver;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
