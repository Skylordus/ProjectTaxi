package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Driver;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IDriverService {
    Driver getDriver(Long id);
    Driver generateDriver(long id,
                          String firstname,
                          String lastname,
                          String patronymic,
                          int experience_years,
                          long car,
                          Date birthdate,
                          String login,
                          String email,
                          long order);

    void updateDriver(long id,
                         String firstname,
                         String lastname,
                         String patronymic,
                         int experience_years,
                         long car,
                         Date birthdate,
                         String login,
                         String email,
                         long order);

    Long insert(Driver driver, boolean getID);
    void delete(Long id);
    void updateOrder(Long client_id, Long order_id);
    List<Driver> getAll();
}
