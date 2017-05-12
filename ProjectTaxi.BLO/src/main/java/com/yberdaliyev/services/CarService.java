package com.yberdaliyev.services;

import com.yberdaliyev.entities.CarEntity;
import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Driver;
import com.yberdaliyev.repositories.CarRepository;
import com.yberdaliyev.transformers.EntityToPojoTransformer;
import com.yberdaliyev.transformers.PojoToEntityTransformer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class CarService implements ICarService {
    private static Logger logger = Logger.getLogger(CarService.class);
    private CarRepository repository;
    private EntityToPojoTransformer entityToPojo;
    private PojoToEntityTransformer pojoToEntity;

    @Autowired
    public void setEntityToPojo(EntityToPojoTransformer entityToPojo) {this.entityToPojo = entityToPojo;}
    @Autowired
    public void setPojoToEntity(PojoToEntityTransformer pojoToEntity) {this.pojoToEntity = pojoToEntity;}

    @Autowired
    //@Qualifier("myCarRepo")
    public void setRepository(CarRepository repository) {this.repository = repository;}




    @Override
    public Car getCar(Long id) {
        if (id==null) return null;
        Car car = entityToPojo.toCar(repository.findOne(id));
        return car;
    }

    @Override
    public Car generateCar(Long id,
                           String manufacturer,
                           String model,
                           String regnum,
                           String color) {
        Car car = new Car(id,
                manufacturer,
                model,
                regnum,
                color,
                null);
        return car;
    }

    @Override
    public void updateCar(Long id,
                             String manufacturer,
                             String model,
                             String regnum,
                             String color,
                             Driver driver) {

        Car car = new Car(id,
                manufacturer,
                model,
                regnum,
                color,
                driver);

        repository.save(pojoToEntity.toCarEntity(car));
    }

    @Override
    public Long insert(Car car) {
        return repository.save(pojoToEntity.toCarEntity(car)).getId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Car> getAll() {
        logger.warn("getting all cars");
        List<CarEntity> carEntities = repository.findAll();
        List<Car> cars = new ArrayList<>();
        for (CarEntity carEntity : carEntities) {
            cars.add(entityToPojo.toCar(carEntity));
        }
        return cars;
    }

}
