package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.Order;
import java.util.Date;
import java.util.List;

/**
 * Created by Yerlan on 02.03.2017.
 */
public interface IOrderService {
    Order generateOrder(Long id, String from, String to, Integer price, Client client, Driver driver, Integer status, Date time);

    void updateOrder(Long id, String from, String to, Integer price, Client client, Driver driver, Integer status, Date time);
    Long insert(Order order);
    void delete(Long id);
    Order getOrder(Long id);
    List<Order> getAll();
    List<Order> getFreeOrders();
    void setStatus(long orderID, int status);

}
