package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface ICarService {
    Car getCar(Long id);
    Car generateCar(Long id,
                    String manufacturer,
                    String model,
                    String regnum,
                    String color);

    void updateCar(Long id,
                   String manufacturer,
                   String model,
                   String regnum,
                   String color,
                   Driver driver);

    Long insert(Car car);
    void delete(Long id);
    List<Car> getAll();
}
