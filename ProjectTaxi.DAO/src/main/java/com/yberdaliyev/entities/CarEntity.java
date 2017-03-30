package com.yberdaliyev.entities;

import javax.persistence.*;
@Entity
@Table(name = "cars", schema = "main")
public class CarEntity {
  @Id
  @GeneratedValue
  private Long id;
  private String manufacturer;
  private String model;
  private String regnum;
  private String color;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "driver",referencedColumnName = "id")
  private DriverEntity driver;

  @Version
  private Long version;

  public CarEntity() {}

  public CarEntity(String manufacturer, String model, String regnum, String color, DriverEntity driver) {
    this.manufacturer = manufacturer;
    this.model = model;
    this.regnum = regnum;
    this.color = color;
    this.driver = driver;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getRegnum() {
    return regnum;
  }

  public void setRegnum(String regnum) {
    this.regnum = regnum;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public DriverEntity getDriver() {
    return driver;
  }

  public void setDriver(DriverEntity driver) {
    this.driver = driver;
  }

  @Override
  public String toString() {
    return manufacturer+" "+model+" color: "+color+" Reg.number: " + regnum;
  }
}
