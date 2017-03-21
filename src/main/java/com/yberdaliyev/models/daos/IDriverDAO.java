package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.pojos.Driver;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IDriverDAO {

    Long insert(Driver driver, boolean getID);
    List<Driver> getAll();
    void updateById(Long id, Driver driver);
    void deleteById(Long id);
    Driver getById(long id);
    void updateOrder(Long driver_id, Long order_id);
}
