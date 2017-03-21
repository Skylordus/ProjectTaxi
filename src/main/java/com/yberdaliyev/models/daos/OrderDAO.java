package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.connectors.Connector;
import com.yberdaliyev.models.entities.OrderEntity;
import com.yberdaliyev.models.pojos.Order;
import com.yberdaliyev.models.transformers.EntityToPojoTransformer;
import com.yberdaliyev.models.transformers.PojoToEntityTransformer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository
@Transactional
public class OrderDAO implements IOrderDAO {
    private static Logger logger = Logger.getLogger(OrderDAO.class);
    private EntityToPojoTransformer entityToPojo;
    private PojoToEntityTransformer pojoToEntity;

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    public void setEntityToPojo(EntityToPojoTransformer entityToPojo) {this.entityToPojo = entityToPojo;}
    @Autowired
    public void setPojoToEntity(PojoToEntityTransformer pojoToEntity) {this.pojoToEntity = pojoToEntity;}

    @Override
    public Long insert(Order order, boolean getID) {
        logger.warn("trying to insert a new order");
        OrderEntity orderEntity = pojoToEntity.toOrderEntity(order);
        manager.persist(orderEntity);
        logger.warn("inserting a new order with ID="+orderEntity.getId());
        if (getID) {return orderEntity.getId();}
        else return 0L;
    }

    @Override
    public Order getById(long id) {
        logger.warn("getting order by ID="+id);
        OrderEntity orderEntity = manager.find(OrderEntity.class, id);
        if (orderEntity == null) return null;
        return entityToPojo.toOrder(orderEntity);
    }

    @Override
    public void deleteById(Long id) {
        logger.warn("deleting order with id="+id);
        OrderEntity orderEntity = manager.find(OrderEntity.class, id);
        manager.remove(orderEntity);
    }

    @Override
    public void updateById(Long id, Order order) {
        logger.warn("trying to update a order with id="+id);
        OrderEntity orderEntity = manager.find(OrderEntity.class, id);
        if (order.getClient() > 0) orderEntity.setClient(order.getClient());
        if (order.getStatus() > -1) orderEntity.setStatus(order.getStatus());
        if (order.getPickup_time() != null) orderEntity.setPickup_time(order.getPickup_time());
        if (order.getPrice_per_km() > 0) orderEntity.setPrice_per_km(order.getPrice_per_km());
        if (order.getFrom() != null) orderEntity.setFrom(order.getFrom());
        if (order.getTo() != null) orderEntity.setTo(order.getTo());
        if (order.getDriver() > 0) orderEntity.setDriver(order.getDriver());
    }

    @Override
    public List<Order> getAll() {
        logger.warn("getting all orders");
        List<OrderEntity> orderEntities = manager.createQuery("Select a From OrderEntity a", OrderEntity.class).getResultList();

        List<Order> orders = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            orders.add(entityToPojo.toOrder(orderEntity));
        }
        return orders;
    }

    @Override
    public List<Order> getFreeOrders() {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<OrderEntity> criteriaQuery = criteriaBuilder.createQuery(OrderEntity.class);
        Root<OrderEntity> root = criteriaQuery.from(OrderEntity.class);

        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("driver"), null)
                )
        );

        List orders = manager.createQuery(criteriaQuery).getResultList();

        return orders;

    }

}
