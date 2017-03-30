package com.yberdaliyev.transformers;

import com.yberdaliyev.models.enums.USER_ROLES;
import com.yberdaliyev.repositories.CarRepository;
import com.yberdaliyev.repositories.ClientRepository;
import com.yberdaliyev.repositories.DriverRepository;
import com.yberdaliyev.repositories.OrderRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yberdaliyev.entities.*;
import com.yberdaliyev.models.pojos.*;
/**
 * Created by Yerlan on 20.03.2017.
 */
@Component
public class PojoToEntityTransformerImpl implements PojoToEntityTransformer {
    Logger logger = Logger.getLogger(PojoToEntityTransformerImpl.class);
    private ClientRepository clientRepository;
    private DriverRepository driverRepository;
    private OrderRepository orderRepository;
    private CarRepository carRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {this.clientRepository = clientRepository;}
    @Autowired
    public void setDriverRepository(DriverRepository driverRepository) {this.driverRepository = driverRepository;}
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {this.orderRepository = orderRepository;}
    @Autowired
    public void setCarRepository(CarRepository carRepository) {this.carRepository = carRepository;}

    @Override
    public AdminEntity toAdminEntity(Admin admin) {
        if (admin==null) return null;
        LoginEntity login = new LoginEntity(admin.getLogin(),
                                            admin.getPwd(),
                                            USER_ROLES.ROLE_ADMIN,
                                            admin.getEmail(),
                                            true);

        return new AdminEntity(admin.getFirstname(),
                admin.getLastname(),
                admin.getPatronymic(),
                admin.getBirthdate(),
                login);

    }

    @Override
    public CarEntity toCarEntity(Car car) {
        if (car==null) return null;
        Driver driver = car.getDriver();
        return new CarEntity(   car.getManufacturer(),
                car.getModel(),
                car.getRegnum(),
                car.getColor(),
                driver==null?null:toDriverEntity(driver));
    }

    @Override
    public ClientEntity toClientEntity(Client client) {
        logger.warn("in toClientEntity " +this.hashCode());
        if (client==null) return null;
        if (client.getId()!=null) {
              return clientRepository.findOne(client.getId());
        }

        LoginEntity login = new LoginEntity(client.getLogin(),
                client.getPwd(),
                USER_ROLES.ROLE_CLIENT,
                client.getEmail(),
                true);

        ClientEntity clientEntity = new ClientEntity( client.getFirstname(),
                client.getLastname(),
                client.getPatronymic(),
                client.getDate_registered(),
                client.getOrders_amount(),
                client.getBirthdate(),
                login,
                new OrderEntity(client.getOrder()));
        logger.warn("finished toClientEntity "+this.hashCode());
        return clientEntity;
    }

    @Override
    public DriverEntity toDriverEntity(Driver driver) {
        if (driver==null) return null;
        if (driver.getId()!=null) {
            return driverRepository.findOne(driver.getId());
        }

        LoginEntity login = new LoginEntity(driver.getLogin(),
                driver.getPwd(),
                USER_ROLES.ROLE_DRIVER,
                driver.getEmail(),
                true);

        OrderEntity order = orderRepository.findOne(driver.getOrder());
        CarEntity carEntity = carRepository.findOne(driver.getCar());

        return new DriverEntity( driver.getExperience_years(),
                carEntity,
                driver.getFirstname(),
                driver.getLastname(),
                driver.getPatronymic(),
                driver.getBirthdate(),
                login,
                order);
    }

    @Override
    public OrderEntity toOrderEntity(Order order) {
        logger.warn("in toOrderEntity "+this.hashCode());
        if (order==null) return null;
        Client client = order.getClient();
        Driver driver = order.getDriver();
        OrderEntity orderEntity = new OrderEntity( order.getFrom(),
                                 order.getTo(),
                                 order.getPrice_per_km(),
                                 order.getPickup_time(),
                                 client==null?null:toClientEntity(client),
                                 driver==null?null:toDriverEntity(driver),
                                 order.getStatus() );
        logger.warn("finished toOrderEntity "+this.hashCode());
        return orderEntity;
         }
}
