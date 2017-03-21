package com.yberdaliyev.models.transformers;

import com.yberdaliyev.models.entities.*;
import com.yberdaliyev.models.pojos.*;
import org.springframework.stereotype.Component;

/**
 * Created by Yerlan on 20.03.2017.
 */
@Component
public class EntityToPojoTransformerImpl implements EntityToPojoTransformer {

    @Override
    public Admin toAdmin(AdminEntity adminEntity) {
        return new Admin(   adminEntity.getId(),
                adminEntity.getFirstname(),
                adminEntity.getLastname(),
                adminEntity.getPatronymic(),
                adminEntity.getBirthdate(),
                adminEntity.getLogin().getLogin(),
                adminEntity.getEmail(),
                adminEntity.getLogin().getPwd());
    }

    @Override
    public Car toCar(CarEntity carEntity) {

        return new Car( carEntity.getId(),
                carEntity.getManufacturer(),
                carEntity.getModel(),
                carEntity.getRegnum(),
                carEntity.getColor(),
                toDriver(carEntity.getDriver()));
    }

    @Override
    public Client toClient(ClientEntity clientEntity) {
        long orderID = (clientEntity.getOrder()==null) ? 0 : clientEntity.getOrder().getId();

        return new Client( clientEntity.getId(),
                clientEntity.getFirstname(),
                clientEntity.getLastname(),
                clientEntity.getPatronymic(),
                clientEntity.getDate_registered(),
                clientEntity.getOrders_amount(),
                clientEntity.getBirthdate(),
                clientEntity.getLogin().getLogin(),
                clientEntity.getEmail(),
                orderID,
                clientEntity.getLogin().getPwd() );
    }

    @Override
    public Driver toDriver(DriverEntity driverEntity) {
        long orderID = (driverEntity.getOrder()==null) ? 0 : driverEntity.getOrder().getId();

        return new Driver( driverEntity.getId(),
                driverEntity.getExperience_years(),
                toCar(driverEntity.getCar()),
                driverEntity.getFirstname(),
                driverEntity.getLastname(),
                driverEntity.getPatronymic(),
                driverEntity.getBirthdate(),
                driverEntity.getLogin().getLogin(),
                driverEntity.getEmail(),
                orderID,
                driverEntity.getLogin().getPwd());
    }

    @Override
    public Order toOrder(OrderEntity orderEntity) {

        return new Order( orderEntity.getId(),
                orderEntity.getFrom(),
                orderEntity.getTo(),
                orderEntity.getPrice_per_km(),
                orderEntity.getPickup_time(),
                toClient(orderEntity.getClient()),
                toDriver(orderEntity.getDriver()),
                orderEntity.getStatus() );
    }

    @Override
    public MyUserDetails toUserDetails(LoginEntity loginEntity) {
        return new MyUserDetails(loginEntity.getLogin(),
                                 loginEntity.getPwd(),
                                 loginEntity.getRole(),
                                 loginEntity.isEnabled());
    }
}
