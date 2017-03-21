package com.yberdaliyev.services;

import com.yberdaliyev.models.entities.ClientEntity;
import com.yberdaliyev.models.pojos.Client;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IClientService {
    Client generateClient(long id,
                          String firstname,
                          String lastname,
                          String patronymic,
                          Date date_registered,
                          int orders_amount,
                          Date birthdate,
                          String login,
                          String email,
                          long order);

    void updateClient(long id,
                         String firstname,
                         String lastname,
                         String patronymic,
                         Date date_registered,
                         int orders_amount,
                         Date birthdate,
                         String login,
                         String email,
                         long order);
    Long insert(Client client, boolean getID);
    void delete(Long id);
    Client getClient(Long id);
    HashMap<Long,String> getClientNamesMappedById(List<Long> IDs);
    List<Client> getAll();
    void updateOrder(Long client_id, Long order_id);
    void increaseOrdersCount(Long client_id);

}
