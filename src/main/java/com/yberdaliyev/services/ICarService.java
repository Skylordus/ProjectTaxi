package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface ICarService {
    Car getCar(Long id);
    Car generateCar(long id,
                    String manufacturer,
                    String model,
                    String regnum,
                    String color);

    void updateCar(long id,
                      String manufacturer,
                      String model,
                      String regnum,
                      String color);

    Long insert(Car car, boolean getID);
    void delete(Long id);
    List<Car> getAll();
}
