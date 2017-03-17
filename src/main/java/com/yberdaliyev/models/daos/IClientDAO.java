package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.pojos.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IClientDAO {
    Long insert(Client client, boolean getID);
    ArrayList<Client> getAll();
    boolean updateById(Long id, Properties columns);
    boolean updateOrder(Long client_id, Long order_id);
    boolean deleteById(Long id);
    Client getById(long id);
    boolean increaseOrdersCount(Long client_id);
}
