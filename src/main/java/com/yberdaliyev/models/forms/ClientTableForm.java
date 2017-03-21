package com.yberdaliyev.models.forms;

import java.sql.Date;

/**
 * Created by Yerlan on 20.03.2017.
 */
public class ClientTableForm {
    private long id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private Date date_registered;
    private int orders_amount;
    private Date birthdate;
    private String login;
    private String email;
    private long order;
    private String pwd;

    public ClientTableForm() {}

    public ClientTableForm(long id, String firstname, String lastname, String patronymic, Date date_registered, int orders_amount, Date birthdate, String login, String email, long order, String pwd) {
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

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
