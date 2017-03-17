package com.yberdaliyev.models.pojos;

import java.sql.Time;

public class Order {
  private Long id;
  private String from;
  private String to;
  private Long price_per_km;
  private Time pickup_time;
  private Long client;
  private Long driver;
  private Long status;


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

  public Long getPrice_per_km() {
    return price_per_km;
  }

  public void setPrice_per_km(Long price_per_km) {
    this.price_per_km = price_per_km;
  }

  public Long getClient() {
    return client;
  }

  public void setClient(Long client) {
    this.client = client;
  }

  public Long getDriver() {
    return driver;
  }

  public void setDriver(Long driver) {
    this.driver = driver;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public Time getPickup_time() {
    return pickup_time;
  }

  public void setPickup_time(Time pickup_time) {
    this.pickup_time = pickup_time;
  }

}
