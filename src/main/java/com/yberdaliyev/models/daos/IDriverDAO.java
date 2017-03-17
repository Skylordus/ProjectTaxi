package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.pojos.Driver;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IDriverDAO {

    Long insert(Driver driver, boolean getID);
    ArrayList<Driver> getAll();
    boolean updateById(Long id, Properties columns);
    boolean deleteById(Long id);
    Driver getById(long id);
    boolean updateOrder(Long driver_id, Long order_id);
}
