package com.yberdaliyev.services;

import com.yberdaliyev.entities.OrderEntity;
import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.Order;
import com.yberdaliyev.repositories.OrderRepository;
import com.yberdaliyev.transformers.EntityToPojoTransformer;
import com.yberdaliyev.transformers.PojoToEntityTransformer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
        if (id==null) return null;
        Order order = entityToPojo.toOrder(repository.findOne(id));

        logger.warn("My order is:"+order);
        return order;
    }

    public void setStatus(long orderID, int status) {
        updateOrder(orderID,
                null,
                null,
                null,
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
        logger.warn("free orders="+orderEntities);
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
                               Date time) {
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
                            Date time) {
        OrderEntity orderEntity = repository.findOne(id);
        if (from!=null) {orderEntity.setFrom(from);}
        if (to!=null) {orderEntity.setTo(to);}
        if ((price!=null)&&(price>0)) {orderEntity.setPrice_per_km(price);}
        if (client!=null) {orderEntity.setClient(pojoToEntity.toClientEntity(client));}
        if (driver!=null) {orderEntity.setDriver(pojoToEntity.toDriverEntity(driver));}
        if ((status!=null)&&(status>0)) {orderEntity.setStatus(status);}
        if (time!=null) {orderEntity.setPickup_time(time);}

       repository.save(orderEntity);
    }

    @Override
    public Long insert(Order order) {
        OrderEntity orderEntity = pojoToEntity.toOrderEntity(order);
        logger.warn("insert method, GOT THE ENTITY");
        return repository.save(orderEntity).getId();
    }

    @Override
    public void delete(Long id) {
        logger.warn("deleting order ID="+id);
        repository.deleteById(id);
    }


}
