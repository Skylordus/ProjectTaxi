package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface ICarService {
    Car getCar(Long id);
    Car generateCar(String id,
                    String manufacturer,
                    String model,
                    String regnum,
                    String color);

    boolean updateCar(String id,
                      String manufacturer,
                      String model,
                      String regnum,
                      String color);

    Long insert(Car car, boolean getID);
    boolean delete(Long id);
    ArrayList<Car> getAll();
}
