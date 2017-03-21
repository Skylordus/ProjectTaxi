package com.yberdaliyev.models.pojos;


import java.sql.Time;

public class Order {
  private long id;
  private String from;
  private String to;
  private int price_per_km;
  private Time pickup_time;
  private Client client;
  private Driver driver;
  private int status;



  public Order() {}

  public Order(long id, String from, String to, int price_per_km, Time pickup_time, Client client, Driver driver, int status) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.price_per_km = price_per_km;
    this.pickup_time = pickup_time;
    this.client = client;
    this.driver = driver;
    this.status = status;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public int getPrice_per_km() {
    return price_per_km;
  }

  public void setPrice_per_km(int price_per_km) {
    this.price_per_km = price_per_km;
  }

  public Time getPickup_time() {
    return pickup_time;
  }

  public void setPickup_time(Time pickup_time) {
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

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
