package com.yberdaliyev.services;

import com.yberdaliyev.models.entities.ClientEntity;
import com.yberdaliyev.models.pojos.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yerlan on 02.03.2017.
 */

public interface IClientService {
    ClientEntity generateClient(String id,
                          String firstname,
                          String lastname,
                          String patronymic,
                          String date_registered,
                          String orders_amount,
                          String birthdate,
                          String login,
                          String email,
                          String order);

    boolean updateClient(String id,
                         String firstname,
                         String lastname,
                         String patronymic,
                         String date_registered,
                         String orders_amount,
                         String birthdate,
                         String login,
                         String email,
                         String order);
    Long insert(ClientEntity client, boolean getID);
    boolean delete(Long id);
    ClientEntity getClient(Long id);
    HashMap<Long,String> getClientNamesMappedById(List<Long> IDs);
    List<ClientEntity> getAll();
    boolean updateOrder(Long client_id, Long order_id);
    boolean increaseOrdersCount(Long client_id);

}
