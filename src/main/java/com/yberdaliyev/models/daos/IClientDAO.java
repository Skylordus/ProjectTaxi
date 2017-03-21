package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.pojos.Client;
import java.util.List;
import java.util.Properties;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IClientDAO {
    Long insert(Client client, boolean getID);
    List<Client> getAll();
    void updateById(Long id, Client client);
    void updateOrder(Long client_id, Long order_id);
    void  deleteById(Long id);
    Client getById(long id);
    void increaseOrdersCount(Long client_id);
}
