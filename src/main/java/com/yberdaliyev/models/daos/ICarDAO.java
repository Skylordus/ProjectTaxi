package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Driver;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface ICarDAO {
    Long insert(Car Car, boolean getID);
    ArrayList<Car> getAll();
    boolean updateById(Long id, Properties columns);
    boolean deleteById(Long id);
    Car getById(long id);
}
