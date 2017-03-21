package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.IClientDAO;
import com.yberdaliyev.models.pojos.Client;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class ClientService implements IClientService {
    private IClientDAO clientDAO;
    private static Logger logger = Logger.getLogger(ClientService.class);


    @Autowired
    public ClientService(IClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public Client generateClient(long id,
                                 String firstname,
                                 String lastname,
                                 String patronymic,
                                 Date date_registered,
                                 int orders_amount,
                                 Date birthdate,
                                 String login,
                                 String email,
                                 long order) {
        Client client = new Client();
        client.setId(id);
        client.setFirstname(firstname);
        client.setLogin(login);
        client.setLastname(lastname);
        client.setPatronymic(patronymic);
        client.setOrder(order);
        client.setDate_registered(date_registered);
        client.setBirthdate(birthdate);
        client.setOrders_amount(orders_amount);
        client.setEmail(email);
        return client;
    }

    @Override
    public void updateClient(long id,
                             String firstname,
                             String lastname,
                             String patronymic,
                             Date date_registered,
                             int orders_amount,
                             Date birthdate,
                             String login,
                             String email,
                             long order) {

        Client client = new Client(id,
                firstname,
                lastname,
                patronymic,
                date_registered,
                orders_amount,
                birthdate,
                login,
                email,
                order,
                null);

        clientDAO.updateById(id,client);
    }

    @Override
    public Long insert(Client client, boolean getID) {
        return clientDAO.insert(client, getID);
    }

    @Override
    public void delete(Long id) {
        clientDAO.deleteById(id);
    }

    @Override
    public Client getClient(Long id) {
        Client client = clientDAO.getById(id);
        return client;
    }

    @Override
    public HashMap<Long,String> getClientNamesMappedById(List<Long> IDs) {
        HashMap<Long,String> map = new HashMap<>();
        Iterator<Long> it = IDs.iterator();
        Long id;
        Client client;
        logger.warn(IDs);
        while (it.hasNext()) {
            id = it.next();
            client = getClient(id);
            map.put(id,client.getFirstname()+" "+client.getPatronymic()+" "+client.getLastname());
        }
        return map;
    }


    @Override
    public List<Client> getAll() {
        return clientDAO.getAll();
    }

    @Override
    public void updateOrder(Long client_id, Long order_id) {
        clientDAO.updateOrder(client_id,order_id);

    }
    @Override
    public void increaseOrdersCount(Long client_id) {
        clientDAO.increaseOrdersCount(client_id);
    }
}
