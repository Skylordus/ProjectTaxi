package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.Order;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IDriverService {
    Driver getDriver(Long id);
    Driver generateDriver(Long id,
                          String firstname,
                          String lastname,
                          String patronymic,
                          Integer experience_years,
                          Long car,
                          Date birthdate,
                          String login,
                          String email,
                          Long order);

    void updateDriver(Long id,
                         String firstname,
                         String lastname,
                         String patronymic,
                         Integer experience_years,
                         Long car,
                         Date birthdate,
                         Long order);

    Long insert(Driver driver);
    void delete(Long id);
    void updateOrder(Long client_id, Long order_id);
    List<Driver> getAll();
}
