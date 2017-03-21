package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.CarDAO;
import com.yberdaliyev.models.daos.ICarDAO;
import com.yberdaliyev.models.pojos.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class CarService implements ICarService {

    private ICarDAO carDAO;

    @Autowired
    public CarService(ICarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public Car getCar(Long id) {
       Car car = carDAO.getById(id);
        return car;
    }

    @Override
    public Car generateCar(long id,
                           String manufacturer,
                           String model,
                           String regnum,
                           String color) {
        Car car = new Car();
        car.setId(id);
        car.setManufacturer(manufacturer);
        car.setModel(model);
        car.setRegnum(regnum);
        car.setColor(color);
        return car;
    }

    @Override
    public void updateCar(long id,
                             String manufacturer,
                             String model,
                             String regnum,
                             String color) {

        Car car = new Car(id,
                manufacturer,
                model,
                regnum,
                color);
        carDAO.updateById(id,car);
    }

    @Override
    public Long insert(Car car, boolean getID) {
        return carDAO.insert(car, getID);
    }

    @Override
    public void delete(Long id) {
        carDAO.deleteById(id);
    }

    @Override
    public List<Car> getAll() {
        return carDAO.getAll();
    }
}
