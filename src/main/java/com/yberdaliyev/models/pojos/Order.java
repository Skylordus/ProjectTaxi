package com.yberdaliyev.models.pojos;


import java.util.Date;

public class Order {
  private Long id;
  private String from;
  private String to;
  private Integer price_per_km;
  private Date pickup_time;
  private Client client;
  private Driver driver;
  private Integer status;



  public Order() {}

  public Order(Long id, String from, String to, Integer price_per_km, Date pickup_time, Client client, Driver driver, Integer status) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.price_per_km = price_per_km;
    this.pickup_time = pickup_time;
    this.client = client;
    this.driver = driver;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public Integer getPrice_per_km() {
    return price_per_km;
  }

  public void setPrice_per_km(Integer price_per_km) {
    this.price_per_km = price_per_km;
  }

  public Date getPickup_time() {
    return pickup_time;
  }

  public void setPickup_time(Date pickup_time) {
    this.pickup_time = pickup_time;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Driver getDriver() {
    return driver;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
