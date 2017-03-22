//package com.yberdaliyev.models.daos.unused.hibernate;
//
//import com.yberdaliyev.models.daos.ICarDAO;
//import com.yberdaliyev.models.entities.CarEntity;
//import com.yberdaliyev.models.pojos.Car;
//import com.yberdaliyev.models.transformers.EntityToPojoTransformer;
//import com.yberdaliyev.models.transformers.PojoToEntityTransformer;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.ArrayList;
//import java.util.List;
//
//@Transactional
//@Repository
//public class CarDAO_h implements ICarDAO {
//    private EntityToPojoTransformer entityToPojo;
//    private PojoToEntityTransformer pojoToEntity;
//
//    @PersistenceContext
//    private EntityManager manager;
//
//    @Autowired
//    public void setEntityToPojo(EntityToPojoTransformer entityToPojo) {this.entityToPojo = entityToPojo;}
//    @Autowired
//    public void setPojoToEntity(PojoToEntityTransformer pojoToEntity) {this.pojoToEntity = pojoToEntity;}
//
//    private static Logger logger = Logger.getLogger(CarDAO_h.class);
//
//
//    @Override
//    public Long insert(Car car, boolean getID) {
//        logger.warn("trying to insert a new car");
//        CarEntity carEntity = pojoToEntity.toCarEntity(car);
//        manager.persist(carEntity);
//        logger.warn("inserting a new car with ID="+carEntity.getId());
//        if (getID) {return carEntity.getId();}
//        else return 0L;
//    }
//
//    @Override
//    public Car getById(long id) {
//        logger.warn("getting car by ID="+id);
//        CarEntity carEntity = manager.find(CarEntity.class, id);
//        if (carEntity == null) return null;
//        return entityToPojo.toCar(carEntity);
//    }
//
//    @Override
//    public List<Car> getAll() {
//        logger.warn("getting all cars");
//        List<CarEntity> carEntities = manager.createQuery("Select a From CarEntity a", CarEntity.class).getResultList();
//        List<Car> cars = new ArrayList<>();
//        for (CarEntity carEntity : carEntities) {
//            cars.add(entityToPojo.toCar(carEntity));
//        }
//        return cars;
//    }
//
//    @Override
//    public void updateById(Long id, Car car) {
//        logger.warn("trying to update a car with id="+id);
//        CarEntity carEntity = manager.find(CarEntity.class, id);
//        if (car.getManufacturer() != null) carEntity.setManufacturer(car.getManufacturer());
//        if (car.getModel() != null) carEntity.setModel(car.getModel());
//        if (car.getRegnum() != null) carEntity.setRegnum(car.getRegnum());
//        if (car.getColor() != null) carEntity.setColor(car.getColor());
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        logger.warn("deleting car with id="+id);
//        CarEntity carEntity = manager.find(CarEntity.class, id);
//        manager.remove(carEntity);
//    }
//
//}
