package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.IDriverDAO;
import com.yberdaliyev.models.entities.CarEntity;
import com.yberdaliyev.models.entities.DriverEntity;
import com.yberdaliyev.models.entities.OrderEntity;
import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.models.pojos.Order;
import com.yberdaliyev.models.repositories.CarRepository;
import com.yberdaliyev.models.repositories.DriverRepository;
import com.yberdaliyev.models.repositories.OrderRepository;
import com.yberdaliyev.models.transformers.EntityToPojoTransformer;
import com.yberdaliyev.models.transformers.PojoToEntityTransformer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private static Logger logger = Logger.getLogger(DriverService.class);
    private DriverRepository repository;
    private OrderRepository orderRepository;
    private CarRepository carRepository;
    private EntityToPojoTransformer entityToPojo;
    private PojoToEntityTransformer pojoToEntity;

    @Autowired
    public void setEntityToPojo(EntityToPojoTransformer entityToPojo) {this.entityToPojo = entityToPojo;}
    @Autowired
    public void setPojoToEntity(PojoToEntityTransformer pojoToEntity) {this.pojoToEntity = pojoToEntity;}
    @Autowired
    public void setRepository(DriverRepository repository) {this.repository = repository;}
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {this.orderRepository = orderRepository;}
    @Autowired
    public void setCarRepository(CarRepository carRepository) {this.carRepository = carRepository;}

    public Driver getDriver(Long id) {

       Driver driver = entityToPojo.toDriver(repository.findOne(id));

        return driver;
    }

    @Override
    public Driver generateDriver(Long id,
                                 String firstname,
                                 String lastname,
                                 String patronymic,
                                 Integer experience_years,
                                 Long car,
                                 Date birthdate,
                                 String login,
                                 String email,
                                 Long order) {
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
    public void updateDriver(Long id,
                             String firstname,
                             String lastname,
                             String patronymic,
                             Integer experience_years,
                             Long car,
                             Date birthdate,
                             Long order) {

        DriverEntity driverEntity = repository.findOne(id);
        CarEntity carEntity = (car==null)?null:carRepository.findOne(car);

        if (firstname!=null) {driverEntity.setFirstname(firstname);}
        if (lastname!=null) {driverEntity.setLastname(lastname);}
        if (patronymic!=null) {driverEntity.setPatronymic(patronymic);}
        if (experience_years!=null) {driverEntity.setExperience_years(experience_years);}
        if (carEntity!=null) {driverEntity.setCar(carEntity);}
        if (birthdate!=null) {driverEntity.setBirthdate(birthdate);}

        if (order!=null) {
            logger.warn("ORDER before is:"+driverEntity.getOrder());
            OrderEntity orderEntity = orderRepository.findOne(order);
            driverEntity.setOrder(orderEntity);
            orderEntity.setDriver(driverEntity);
        }

        logger.warn("ORDER ID is:"+driverEntity.getOrder().getId());
        repository.save(driverEntity);
    }

    @Override
    public Long insert(Driver driver) {
        return repository.save(pojoToEntity.toDriverEntity(driver)).getId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateOrder(Long driver_id, Long order_id) {
        DriverEntity driverEntity = repository.findOne(driver_id);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order_id);
        driverEntity.setOrder(orderEntity);
        repository.save(driverEntity);
    }

    @Override
    public List<Driver> getAll() {
        logger.warn("getting all drivers");
        List<DriverEntity> driverEntities = repository.findAll();
        List<Driver> drivers = new ArrayList<>();
        for (DriverEntity driverEntity : driverEntities) {
            drivers.add(entityToPojo.toDriver(driverEntity));
        }
        return drivers;
    }

}
