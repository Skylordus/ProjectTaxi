package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IDriverService {
    Driver getDriver(Long id);
    Driver generateDriver(String id,
                          String firstname,
                          String lastname,
                          String patronymic,
                          String experience_years,
                          String car,
                          String birthdate,
                          String login,
                          String email,
                          String order);

    boolean updateDriver(String id,
                         String firstname,
                         String lastname,
                         String patronymic,
                         String experience_years,
                         String car,
                         String birthdate,
                         String login,
                         String email,
                         String order);

    Long insert(Driver driver, boolean getID);
    boolean delete(Long id);
    boolean updateOrder(Long client_id, Long order_id);
    ArrayList<Driver> getAll();
}
