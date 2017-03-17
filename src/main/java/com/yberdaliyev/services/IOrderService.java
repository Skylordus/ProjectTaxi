package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Yerlan on 02.03.2017.
 */
public interface IOrderService {
    Order generateOrder(String id, String from, String to, String price, String client, String driver, String status, String time);
    Order generateOrder(Client client,
                         String from,
                         String to,
                         String pickup_time,
                         String plan);

    boolean updateOrder(String id, String from, String to, String price, String client, String driver, String status, String time);
    Long insert(Order order, boolean getID);
    boolean delete(Long id);
    Order getOrder(Long id);
    ArrayList<Order> getAll();
    ArrayList<Order> getFreeOrders();
    boolean setStatus(Long orderID, int status);

}
