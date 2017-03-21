package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.*;
import com.yberdaliyev.models.pojos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class OrderService implements IOrderService {
    private IOrderDAO orderDAO;
    private IDriverService driverService;
    private IClientService clientService;

    @Autowired
    public void setOrderDAO(IOrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    @Autowired
    public void setDriverService(IDriverService driverService) {
        this.driverService = driverService;
    }
    @Autowired
    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }

    public static final int STATUS_NO_DRIVER = 0;
    public static final int STATUS_NOT_STARTED = 1;
    public static final int STATUS_FULFILLING = 2;
    public static final int STATUS_FINISHED = 3;
    public static final int STATUS_TIME_IS_UP = 4;
    public static final String[] STATUS_MESSAGES = { "Wait until a driver picks this order",
                                                    "Wait until assigned driver arrives",
                                                    "Order on execution",
                                                    "Order is executed",
                                                    "Time is up, but the driver didn't arrive"};

    public Order getOrder(Long id) {
       Order order = orderDAO.getById(id);
        return order;
    }

    public void setStatus(long orderID, int status) {
        updateOrder(orderID,
                "",
                "",
                0,
                0,
                0,
                status,
                null);
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.getAll();
    }
    @Override
    public List<Order> getFreeOrders()  {
        return orderDAO.getFreeOrders();
    }
    @Override
    public Order generateOrder(long id,
                               String from,
                               String to,
                               int price,
                               long client,
                               long driver,
                               int status,
                               Time time) {
        Order order = new Order();
        order.setId(id);
        order.setFrom(from);
        order.setTo(to);
        order.setClient(client);
        order.setDriver(driver);
        order.setPrice_per_km(price);
        order.setStatus(status);
        order.setPickup_time(time);
        return order;
    }

    @Override
    public void updateOrder(long id,
                            String from,
                            String to,
                            int price,
                            long client,
                            long driver,
                            int status,
                            Time time) {
        Order order = new Order(id,
                from,
                to,
                price,
                time,
                client,
                driver,
                status);

        orderDAO.updateById(id,order);
    }

    @Override
    public Long insert(Order order, boolean getID) {
         return orderDAO.insert(order, getID);
    }

    @Override
    public void delete(Long id) {
        Order order = getOrder(id);
//        driverService.updateOrder(order.getDriver(),0l);
//        clientService.updateOrder(order.getClient(),0l);
        orderDAO.deleteById(id);
    }

}
