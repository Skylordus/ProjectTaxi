package com.yberdaliyev.models.forms;

import java.sql.Date;

/**
 * Created by Yerlan on 20.03.2017.
 */
public class CarTableForm {

    private long id;
    private String manufacturer;
    private String model;
    private String regnum;
    private String color;

    public CarTableForm() {}

    public CarTableForm(long id, String manufacturer, String model, String regnum, String color) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.regnum = regnum;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
