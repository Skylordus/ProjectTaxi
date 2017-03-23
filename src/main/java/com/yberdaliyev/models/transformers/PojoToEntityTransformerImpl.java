package com.yberdaliyev.models.transformers;

import com.yberdaliyev.models.entities.*;
import com.yberdaliyev.models.enums.USER_ROLES;
import com.yberdaliyev.models.pojos.*;
import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * Created by Yerlan on 20.03.2017.
 */
@Component
public class PojoToEntityTransformerImpl implements PojoToEntityTransformer {


    @Override
    public AdminEntity toAdminEntity(Admin admin) {
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
        Driver driver = car.getDriver();
        return new CarEntity(   car.getManufacturer(),
                car.getModel(),
                car.getRegnum(),
                car.getColor(),
                driver==null?null:toDriverEntity(driver));
    }

    @Override
    public ClientEntity toClientEntity(Client client) {

        LoginEntity login = new LoginEntity(client.getLogin(),
                client.getPwd(),
                USER_ROLES.ROLE_CLIENT,
                client.getEmail(),
                true);
        Order order = client.getOrder();

        return new ClientEntity( client.getFirstname(),
                client.getLastname(),
                client.getPatronymic(),
                client.getDate_registered(),
                client.getOrders_amount(),
                client.getBirthdate(),
                login,
                (order==null)?null:toOrderEntity(order));
    }

    @Override
    public DriverEntity toDriverEntity(Driver driver) {
        LoginEntity login = new LoginEntity(driver.getLogin(),
                driver.getPwd(),
                USER_ROLES.ROLE_CLIENT,
                driver.getEmail(),
                true);
        Order order = driver.getOrder();
        return new DriverEntity( driver.getExperience_years(),
                toCarEntity(driver.getCar()),
                driver.getFirstname(),
                driver.getLastname(),
                driver.getPatronymic(),
                driver.getBirthdate(),
                login,
                (order==null)?null:toOrderEntity(order));
    }

    @Override
    public OrderEntity toOrderEntity(Order order) {
        Client client = order.getClient();
        Driver driver = order.getDriver();

         return new OrderEntity( order.getFrom(),
                                 order.getTo(),
                                 order.getPrice_per_km(),
                                 order.getPickup_time(),
                                 client==null?null:toClientEntity(client),
                                 driver==null?null:toDriverEntity(driver),
                                 order.getStatus() );
         }
}
