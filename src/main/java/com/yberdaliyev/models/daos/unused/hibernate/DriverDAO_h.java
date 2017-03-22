//package com.yberdaliyev.models.daos.unused.hibernate;
//
//import com.yberdaliyev.models.daos.IDriverDAO;
//import com.yberdaliyev.models.entities.DriverEntity;
//import com.yberdaliyev.models.entities.LoginEntity;
//import com.yberdaliyev.models.enums.USER_ROLES;
//import com.yberdaliyev.models.pojos.Driver;
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
//@Repository
//@Transactional
//public class DriverDAO_h implements IDriverDAO {
//
//    private static Logger logger = Logger.getLogger(DriverDAO_h.class);
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
//    @Override
//    public Long insert(Driver driver, boolean getID) {
//        logger.warn("trying to insert a new driver");
//        DriverEntity driverEntity = pojoToEntity.toDriverEntity(driver);
//        logger.warn("inserting LOGIN first");
//        LoginEntity loginEntity = new LoginEntity(driver.getLogin(),
//                driver.getPwd(),
//                USER_ROLES.ROLE_DRIVER,
//                true);
//        manager.persist(loginEntity);
//        manager.persist(driverEntity);
//        logger.warn("inserting a new driver with ID="+driverEntity.getId());
//        if (getID) {return driverEntity.getId();}
//        else return 0L;
//    }
//
//    @Override
//    public Driver getById(long id) {
//        logger.warn("getting driver by ID="+id);
//        DriverEntity driverEntity = manager.find(DriverEntity.class, id);
//        if (driverEntity == null) return null;
//        return entityToPojo.toDriver(driverEntity);
//    }
//
//    @Override
//    public List<Driver> getAll() {
//        logger.warn("getting all drivers");
//        List<DriverEntity> driverEntities = manager.createQuery("Select a From DriverEntity a", DriverEntity.class).getResultList();
//
//        List<Driver> drivers = new ArrayList<>();
//        for (DriverEntity driverEntity : driverEntities) {
//            drivers.add(entityToPojo.toDriver(driverEntity));
//        }
//        return drivers;
//    }
//
//    @Override
//    public void updateById(Long id, Driver driver) {
//        logger.warn("trying to update a driver with id="+id);
//
////        DriverEntity driverEntity = manager.find(DriverEntity.class, id);
////        if (driver.getFirstname() != null) driverEntity.setFirstname(driver.getFirstname());
////        if (driver.getLastname() != null) driverEntity.setLastname(driver.getLastname());
////        if (driver.getPatronymic() != null) driverEntity.setPatronymic(driver.getPatronymic());
////        if (driver.getEmail() != null) driverEntity.setEmail(driver.getEmail());
////        if (driver.getBirthdate() != null) driverEntity.setBirthdate(driver.getBirthdate());
////        if (driver.getLogin() != null) driverEntity.setLogin(driver.getLogin());
////        if (driver.getCar() > 0) driverEntity.setCar(driver.getCar());
////        if (driver.getOrder() > 0) driverEntity.setOrder(driver.getOrder());
////        if (driver.getExperience_years() > 0) driverEntity.setExperience_years(driver.getExperience_years());
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        logger.warn("deleting driver with id="+id);
//        DriverEntity driverEntity = manager.find(DriverEntity.class, id);
//        manager.remove(driverEntity);
//    }
//
//    @Override
//    public void updateOrder(Long driver_id, Long order_id) {
//        logger.warn("updating driver ID="+driver_id+" with order ID="+order_id);
//        DriverEntity driverEntity = manager.find(DriverEntity.class, driver_id);
//        driverEntity.setOrder(order_id);
//    }
//}
