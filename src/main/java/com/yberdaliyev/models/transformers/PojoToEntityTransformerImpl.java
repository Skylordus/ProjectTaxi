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
                                            true);

        return new AdminEntity(admin.getFirstname(),
                admin.getLastname(),
                admin.getPatronymic(),
                admin.getBirthdate(),
                login,
                admin.getEmail());

    }

    @Override
    public CarEntity toCarEntity(Car car) {

        return new CarEntity(   car.getManufacturer(),
                car.getModel(),
                car.getRegnum(),
                car.getColor(),
                toDriverEntity(car.getDriver()));
    }

    @Override
    public ClientEntity toClientEntity(Client client) {

        LoginEntity login = new LoginEntity(client.getLogin(),
                client.getPwd(),
                USER_ROLES.ROLE_CLIENT,
                true);

        return new ClientEntity( client.getFirstname(),
                client.getLastname(),
                client.getPatronymic(),
                client.getDate_registered(),
                client.getOrders_amount(),
                client.getBirthdate(),
                login,
                client.getEmail(),
                toOrderEntity(client.getOrder()));
    }

    @Override
    public DriverEntity toDriverEntity(Driver driver) {
        LoginEntity login = new LoginEntity(driver.getLogin(),
                driver.getPwd(),
                USER_ROLES.ROLE_CLIENT,
                true);

        return new DriverEntity( driver.getExperience_years(),
                toCarEntity(driver.getCar()),
                driver.getFirstname(),
                driver.getLastname(),
                driver.getPatronymic(),
                driver.getBirthdate(),
                login,
                driver.getEmail(),
                toOrderEntity(driver.getOrder()));
    }

    @Override
    public OrderEntity toOrderEntity(Order order) {
         return new OrderEntity( order.getFrom(),
                                 order.getTo(),
                                 order.getPrice_per_km(),
                                 order.getPickup_time(),
                                 toClientEntity(order.getClient()),
                                 toDriverEntity(order.getDriver()),
                                 order.getStatus() );
         }
}
