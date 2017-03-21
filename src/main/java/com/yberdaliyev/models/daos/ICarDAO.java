package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Driver;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface ICarDAO {
    Long insert(Car Car, boolean getID);
    List<Car> getAll();
    void updateById(Long id, Car car);
    void deleteById(Long id);
    Car getById(long id);
}
