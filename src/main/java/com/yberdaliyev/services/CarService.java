package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.CarDAO;
import com.yberdaliyev.models.daos.ICarDAO;
import com.yberdaliyev.models.pojos.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Car generateCar(String id,
                           String manufacturer,
                           String model,
                           String regnum,
                           String color) {
        Car car = new Car();
        car.setId(Long.parseLong(id));
        car.setManufacturer(manufacturer);
        car.setModel(model);
        car.setRegnum(regnum);
        car.setColor(color);
        return car;
    }

    @Override
    public boolean updateCar(String id,
                             String manufacturer,
                             String model,
                             String regnum,
                             String color) {
        long car_id = Long.parseLong(id);
        Properties columns = new Properties();
        if (!manufacturer.isEmpty()) columns.put("manufacturer",manufacturer);
        if (!model.isEmpty()) columns.put("model",model);
        if (!regnum.isEmpty()) columns.put("regnum",regnum);
        if (!color.isEmpty()) columns.put("color",color);
        return carDAO.updateById(car_id,columns);
    }

    @Override
    public Long insert(Car car, boolean getID) {
        return carDAO.insert(car, getID);
    }

    @Override
    public boolean delete(Long id) {
        if (carDAO.deleteById(id)) {
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Car> getAll() {
        return carDAO.getAll();
    }
}
