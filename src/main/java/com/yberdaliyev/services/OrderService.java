package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.*;
import com.yberdaliyev.models.pojos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
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

    public boolean setStatus(Long orderID, int status) {
        return updateOrder(orderID.toString(),
                "",
                "",
                "",
                "",
                "",
                String.valueOf(status),
                "");
    }

    @Override
    public ArrayList<Order> getAll() {
        return orderDAO.getAll();
    }
    @Override
    public ArrayList<Order> getFreeOrders()  {
        return orderDAO.getFreeOrders();
    }
    @Override
    public Order generateOrder(String id, String from, String to, String price, String client, String driver, String status, String time) {
        Order order = new Order();
        order.setId(Long.parseLong(id));
        order.setFrom(from);
        order.setTo(to);
        order.setClient(Long.parseLong(client));
        order.setDriver(Long.parseLong(driver));
        order.setPrice_per_km(Long.parseLong(price));
        order.setStatus(Long.parseLong(status));
        order.setPickup_time(Time.valueOf(time+":00"));
        return order;
    }

    @Override
    public boolean updateOrder(String id, String from, String to, String price, String client, String driver, String status, String time) {
        long order_id = Long.parseLong(id);
        Properties columns = new Properties();
        if (!from.isEmpty()) columns.put("\"from\"",from);
        if (!to.isEmpty()) columns.put("\"to\"",to);
        if (!price.isEmpty()) columns.put("price_per_km",price);
        if (!client.isEmpty()) columns.put("client",client);
        if (!driver.isEmpty()) columns.put("driver",driver);
        if (!status.isEmpty()) columns.put("status",status);
        if (!time.isEmpty()) columns.put("pickup_time",time);
        return orderDAO.updateById(order_id,columns);
    }

    @Override
    public Long insert(Order order, boolean getID) {
         return orderDAO.insert(order, getID);
    }

    @Override
    public boolean delete(Long id) {
        Order order = getOrder(id);
        driverService.updateOrder(order.getDriver(),0l);
        clientService.updateOrder(order.getClient(),0l);
        if (orderDAO.deleteById(id)) {
            return true;
        }
        return false;
    }

    @Override
    public Order generateOrder(Client client,
                                String from,
                                String to,
                                String pickup_time,
                                String plan) {

        Order order = new Order();
        order.setFrom(from);
        order.setTo(to);
        order.setPickup_time(Time.valueOf(pickup_time+":00"));
        order.setStatus((long)0);
        order.setClient(client.getId());
        String str = plan;
        if ( str.equals("economy") ) {
            order.setPrice_per_km((long) 16);
        } else if ( str.equals("comfort") ) {
            order.setPrice_per_km((long) 25);
        } else order.setPrice_per_km((long) 40);
        return order;
    }

}
