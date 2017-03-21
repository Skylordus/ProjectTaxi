package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.connectors.Connector;
import com.yberdaliyev.models.pojos.Order;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IOrderDAO {

    void deleteById(Long id);

    void updateById(Long id, Order order);

    Long insert(Order order, boolean getID);

    Order getById(long id);

    List<Order> getAll();

    List<Order> getFreeOrders();
}
