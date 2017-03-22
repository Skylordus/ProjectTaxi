package com.yberdaliyev.models.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders", schema = "main")
public class OrderEntity {
  @Id
  @GeneratedValue
  private Long id;

  private String from;
  private String to;
  private Integer price_per_km;
  @Temporal(TemporalType.TIME)
  private Date pickup_time;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
  @JoinColumn(name = "client", referencedColumnName = "id")
  private ClientEntity client;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "driver", referencedColumnName = "id")
  private DriverEntity driver;

  private Integer status;

  @Version
  private Long version;

  public OrderEntity() {
  }

  public OrderEntity(String from, String to, int price_per_km, Date pickup_time, ClientEntity client, DriverEntity driver, int status) {
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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}

