package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.IDriverDAO;
import com.yberdaliyev.models.pojos.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
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
    public Driver generateDriver(String id,
                                 String firstname,
                                 String lastname,
                                 String patronymic,
                                 String experience_years,
                                 String car,
                                 String birthdate,
                                 String login,
                                 String email,
                                 String order) {
        Driver driver = new Driver();
        if (!id.isEmpty()) driver.setId(Long.parseLong(id));
        driver.setFirstname(firstname);
        driver.setLogin(login);
        driver.setLastname(lastname);
        driver.setPatronymic(patronymic);
        if (!experience_years.isEmpty()) driver.setExperience_years(Long.parseLong(experience_years));
        if (!car.isEmpty()) driver.setCar(Long.parseLong(car));
        if (!birthdate.isEmpty()) driver.setBirthdate(Date.valueOf(birthdate));
        if (!order.isEmpty()) driver.setOrder(Long.parseLong(order));
        driver.setEmail(email);
        return driver;
    }

    @Override
    public boolean updateDriver(String id,
                                String firstname,
                                String lastname,
                                String patronymic,
                                String experience_years,
                                String car,
                                String birthdate,
                                String login,
                                String email,
                                String order) {
        long driver_id = Long.parseLong(id);
        Properties columns = new Properties();
        if (!firstname.isEmpty()) columns.put("firstname",firstname);
        if (!lastname.isEmpty()) columns.put("lastname",lastname);
        if (!patronymic.isEmpty()) columns.put("patronymic",patronymic);
        if (!experience_years.isEmpty()) columns.put("experience_years",experience_years);
        if (!car.isEmpty()) columns.put("car",car);
        if (!birthdate.isEmpty()) columns.put("birthdate",birthdate);
        if (!login.isEmpty()) columns.put("login",login);
        if (!email.isEmpty()) columns.put("email",email);
        if (!order.isEmpty()) columns.put("\"order\"",order);
        return driverDAO.updateById(driver_id,columns);
    }

    @Override
    public Long insert(Driver driver, boolean getID) {
        return driverDAO.insert(driver, getID);
    }

    @Override
    public boolean delete(Long id) {
        if (driverDAO.deleteById(id)) {
            return true;
        }
        return false;
    }
    @Override
    public boolean updateOrder(Long driver_id, Long order_id) {
        if (driverDAO.updateOrder(driver_id,order_id)) return true;
        return false;
    }
    @Override
    public ArrayList<Driver> getAll() {
        return driverDAO.getAll();
    }

}
