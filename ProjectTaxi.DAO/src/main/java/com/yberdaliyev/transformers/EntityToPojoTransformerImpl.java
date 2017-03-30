package com.yberdaliyev.transformers;

import org.springframework.stereotype.Component;
import com.yberdaliyev.entities.*;
import com.yberdaliyev.models.pojos.*;
/**
 * Created by Yerlan on 20.03.2017.
 */
@Component
public class EntityToPojoTransformerImpl implements EntityToPojoTransformer {

    @Override
    public Admin toAdmin(AdminEntity adminEntity) {
        if (adminEntity==null) return null;
        return new Admin(   adminEntity.getId(),
                adminEntity.getFirstname(),
                adminEntity.getLastname(),
                adminEntity.getPatronymic(),
                adminEntity.getBirthdate(),
                adminEntity.getLogin().getLogin(),
                adminEntity.getLogin().getEmail(),
                adminEntity.getLogin().getPwd());
    }

    @Override
    public Car toCar(CarEntity carEntity) {
        if (carEntity==null) return null;
        return new Car( carEntity.getId(),
                carEntity.getManufacturer(),
                carEntity.getModel(),
                carEntity.getRegnum(),
                carEntity.getColor(),
                toDriver(carEntity.getDriver()));
    }

    @Override
    public Client toClient(ClientEntity clientEntity) {
        if (clientEntity==null) return null;
        OrderEntity order = clientEntity.getOrder();

        return new Client( clientEntity.getId(),
                clientEntity.getFirstname(),
                clientEntity.getLastname(),
                clientEntity.getPatronymic(),
                clientEntity.getDate_registered(),
                clientEntity.getOrders_amount(),
                clientEntity.getBirthdate(),
                clientEntity.getLogin().getLogin(),
                clientEntity.getLogin().getEmail(),
                (order==null)?null:order.getId(),
                clientEntity.getLogin().getPwd() );
    }

    @Override
    public Order toOrder(OrderEntity orderEntity) {
        if (orderEntity==null) return null;
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
    public Driver toDriver(DriverEntity driverEntity) {
        if (driverEntity==null) return null;
        Long car = driverEntity.getCar()==null?null:driverEntity.getCar().getId();
        Long order = driverEntity.getOrder()==null?null:driverEntity.getOrder().getId();

        return new Driver( driverEntity.getId(),
                driverEntity.getExperience_years(),
                car,
                driverEntity.getFirstname(),
                driverEntity.getLastname(),
                driverEntity.getPatronymic(),
                driverEntity.getBirthdate(),
                driverEntity.getLogin().getLogin(),
                driverEntity.getLogin().getEmail(),
                order,
                driverEntity.getLogin().getPwd());
    }



    @Override
    public MyUserDetails toUserDetails(LoginEntity loginEntity) {
        if (loginEntity==null) return null;
        return new MyUserDetails(loginEntity.getLogin(),
                                 loginEntity.getPwd(),
                                 loginEntity.getRole(),
                                 loginEntity.getEmail(),
                                 loginEntity.isEnabled());
    }
}
