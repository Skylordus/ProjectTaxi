package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.*;
import com.yberdaliyev.models.entities.OrderEntity;
import com.yberdaliyev.models.pojos.*;
import com.yberdaliyev.models.repositories.OrderRepository;
import com.yberdaliyev.models.transformers.EntityToPojoTransformer;
import com.yberdaliyev.models.transformers.PojoToEntityTransformer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private OrderRepository repository;
    private IDriverService driverService;
    private IClientService clientService;
    private EntityToPojoTransformer entityToPojo;
    private PojoToEntityTransformer pojoToEntity;
    private static Logger logger = Logger.getLogger(OrderService.class);

    @Autowired
    public void setEntityToPojo(EntityToPojoTransformer entityToPojo) {this.entityToPojo = entityToPojo;}
    @Autowired
    public void setPojoToEntity(PojoToEntityTransformer pojoToEntity) {this.pojoToEntity = pojoToEntity;}
    @Autowired
    public void setRepository(OrderRepository repository) {this.repository = repository;}
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
       Order order = entityToPojo.toOrder(repository.findOne(id));
        return order;
    }

    public void setStatus(long orderID, int status) {
        updateOrder(orderID,
                "",
                "",
                0,
                null,
                null,
                status,
                null);
    }

    @Override
    public List<Order> getAll() {
        logger.warn("getting all orders");
        List<OrderEntity> orderEntities = repository.findAll();
        List<Order> orders = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            orders.add(entityToPojo.toOrder(orderEntity));
        }
        return orders;
    }

    @Override
    public List<Order> getFreeOrders()  {
        logger.warn("getting free orders");
        List<OrderEntity> orderEntities = repository.findByDriverEqualsNull();
        List<Order> orders = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            orders.add(entityToPojo.toOrder(orderEntity));
        }
        return orders;
    }

    @Override
    public Order generateOrder(Long id,
                               String from,
                               String to,
                               Integer price,
                               Client client,
                               Driver driver,
                               Integer status,
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
    public void updateOrder(Long id,
                            String from,
                            String to,
                            Integer price,
                            Client client,
                            Driver driver,
                            Integer status,
                            Time time) {
        Order order = new Order(id,
                from,
                to,
                price,
                time,
                client,
                driver,
                status);

       repository.save(pojoToEntity.toOrderEntity(order));
    }

    @Override
    public Long insert(Order order) {
        return repository.save(pojoToEntity.toOrderEntity(order)).getId();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
