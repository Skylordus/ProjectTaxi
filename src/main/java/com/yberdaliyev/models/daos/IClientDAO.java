package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.entities.ClientEntity;
import com.yberdaliyev.models.pojos.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IClientDAO {
    Long insert(ClientEntity clientEntity, boolean getID);
    List<ClientEntity> getAll();
    boolean updateById(Long id, Properties columns);
    boolean updateOrder(Long client_id, Long order_id);
    boolean deleteById(Long id);
    ClientEntity getById(long id);
    boolean increaseOrdersCount(Long client_id);
}
