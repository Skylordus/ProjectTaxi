package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerlan on 02.03.2017.
 */
public interface IOrderService {
    Order generateOrder(long id, String from, String to, int price, long client, long driver, int status, Time time);

    void updateOrder(long id, String from, String to, int price, long client, long driver, int status, Time time);
    Long insert(Order order, boolean getID);
    void delete(Long id);
    Order getOrder(Long id);
    List<Order> getAll();
    List<Order> getFreeOrders();
    void setStatus(long orderID, int status);

}
