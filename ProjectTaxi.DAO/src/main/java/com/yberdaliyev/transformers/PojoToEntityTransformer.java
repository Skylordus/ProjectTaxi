package com.yberdaliyev.transformers;
import com.yberdaliyev.entities.*;
import com.yberdaliyev.models.pojos.*;
/**
 * Created by Yerlan on 20.03.2017.
 */
public interface PojoToEntityTransformer {
    AdminEntity toAdminEntity(Admin admin);
    CarEntity toCarEntity(Car car);
    ClientEntity toClientEntity(Client client);
    DriverEntity toDriverEntity(Driver driver);
    OrderEntity toOrderEntity(Order order);
}