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
    boolean deleteById(Long id);

    boolean updateById(Long id, Properties columns);

    Long insert(Order order, boolean getID);

    Order getById(long id);

    ArrayList<Order> getAll();

    ArrayList<Order> getFreeOrders();
}
