package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.IDriverDAO;
import com.yberdaliyev.models.pojos.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class DriverService implements IDriverService {
    private IDriverDAO driverDAO;

    @Autowired
    public DriverService(IDriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    public Driver getDriver(Long id) {

       Driver driver = driverDAO.getById(id);

        return driver;
    }

    @Override
    public Driver generateDriver(long id,
                                 String firstname,
                                 String lastname,
                                 String patronymic,
                                 int experience_years,
                                 long car,
                                 Date birthdate,
                                 String login,
                                 String email,
                                 long order) {
        Driver driver = new Driver();
        driver.setId(id);
        driver.setFirstname(firstname);
        driver.setLogin(login);
        driver.setLastname(lastname);
        driver.setPatronymic(patronymic);
        driver.setExperience_years(experience_years);
        driver.setCar(car);
        driver.setBirthdate(birthdate);
        driver.setOrder(order);
        driver.setEmail(email);
        return driver;
    }

    @Override
    public void updateDriver(long id,
                                String firstname,
                                String lastname,
                                String patronymic,
                                int experience_years,
                                long car,
                                Date birthdate,
                                String login,
                                String email,
                                long order) {

        Driver driver = new Driver(id,
                experience_years,
                car,
                firstname,
                lastname,
                patronymic,
                birthdate,
                login,
                email,
                order,
                null);
        driverDAO.updateById(id,driver);
    }

    @Override
    public Long insert(Driver driver, boolean getID) {
        return driverDAO.insert(driver, getID);
    }

    @Override
    public void delete(Long id) {
        driverDAO.deleteById(id);
    }
    @Override
    public void updateOrder(Long driver_id, Long order_id) {
      driverDAO.updateOrder(driver_id,order_id);
    }

    @Override
    public List<Driver> getAll() {
        return driverDAO.getAll();
    }

}
