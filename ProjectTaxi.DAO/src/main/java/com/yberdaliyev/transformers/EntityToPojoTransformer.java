package com.yberdaliyev.transformers;

import com.yberdaliyev.entities.*;
import com.yberdaliyev.models.pojos.*;

/**
 * Created by Yerlan on 20.03.2017.
 */
public interface EntityToPojoTransformer {
    Admin toAdmin(AdminEntity adminEntity);
    Car toCar(CarEntity carEntity);
    Client toClient(ClientEntity clientEntity);
    Driver toDriver(DriverEntity driverEntity);
    Order toOrder(OrderEntity orderEntity);
    MyUserDetails toUserDetails(LoginEntity loginEntity);
}
