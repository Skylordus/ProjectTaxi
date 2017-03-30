package com.yberdaliyev.services;

import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Order;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IClientService {
    Client generateClient(Long id,
                          String firstname,
                          String lastname,
                          String patronymic,
                          Date date_registered,
                          Integer orders_amount,
                          Date birthdate,
                          String login,
                          String email,
                          Order order);

    void updateClient(Long id,
                      String firstname,
                      String lastname,
                      String patronymic,
                      Date date_registered,
                      Integer orders_amount,
                      Date birthdate,
                      String login,
                      String email,
                      Order order);
    Long insert(Client client);
    void delete(Long id);
    Client getClient(Long id);
    HashMap<Long,String> getClientNamesMappedById(List<Long> IDs);
    List<Client> getAll();
    void updateOrder(Long client_id, Long order_id);
    void increaseOrdersCount(Long client_id);

}
