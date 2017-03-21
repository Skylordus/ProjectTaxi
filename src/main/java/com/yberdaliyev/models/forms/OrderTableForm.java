package com.yberdaliyev.models.forms;

import java.sql.Time;

/**
 * Created by Yerlan on 20.03.2017.
 */
public class OrderTableForm {

    private long id;
    private String from;
    private String to;
    private long client_id;
    private long driver_id;
    private int price;
    private int status;
    private Time pickup_time;

    public OrderTableForm() {}

    public OrderTableForm(long id, String from, String to, long client_id, long driver_id, int price, int status, Time pickup_time) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.client_id = client_id;
        this.driver_id = driver_id;
        this.price = price;
        this.status = status;
        this.pickup_time = pickup_time;
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

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public long getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(long driver_id) {
        this.driver_id = driver_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Time getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(Time pickup_time) {
        this.pickup_time = pickup_time;
    }
}
