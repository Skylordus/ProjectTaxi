package com.yberdaliyev.models.pojos;

public class Car {
  private Long id;
  private String manufacturer;
  private String model;
  private String regnum;
  private String color;
  private Driver driver;

  public Car(Long id, String manufacturer, String model, String regnum, String color, Driver driver) {
    this.id = id;
    this.manufacturer = manufacturer;
    this.model = model;
    this.regnum = regnum;
    this.color = color;

  }

  public Car() {}

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

  public Driver getDriver() {
    return driver;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
  }

  @Override
  public String toString() {
    return manufacturer+" "+model+" color: "+color+" Reg.number: " + regnum;
  }
}
