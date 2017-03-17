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
    public Client generateClient(String id,
                                 String firstname,
                                 String lastname,
                                 String patronymic,
                                 String date_registered,
                                 String orders_amount,
                                 String birthdate,
                                 String login,
                                 String email,
                                 String order) {
        Client client = new Client();
        client.setId(Long.parseLong(id));
        client.setFirstname(firstname);
        client.setLogin(login);
        client.setLastname(lastname);
        client.setPatronymic(patronymic);
        client.setOrder(Long.parseLong(order));
        client.setDate_registered(Date.valueOf(date_registered));
        client.setBirthdate(Date.valueOf(birthdate));
        client.setOrders_amount(Long.parseLong(orders_amount));
        client.setEmail(email);
        return client;
    }

    @Override
    public boolean updateClient(String id,
                                String firstname,
                                String lastname,
                                String patronymic,
                                String date_registered,
                                String orders_amount,
                                String birthdate,
                                String login,
                                String email,
                                String order) {
        long client_id = Long.parseLong(id);
        Properties columns = new Properties();
        if (!firstname.isEmpty()) columns.put("firstname",firstname);
        if (!lastname.isEmpty()) columns.put("lastname",lastname);
        if (!patronymic.isEmpty()) columns.put("patronymic",patronymic);
        if (!date_registered.isEmpty()) columns.put("date_registered",date_registered);
        if (!orders_amount.isEmpty()) columns.put("orders_amount",orders_amount);
        if (!birthdate.isEmpty()) columns.put("birthdate",birthdate);
        if (!login.isEmpty()) columns.put("login",login);
        if (!email.isEmpty()) columns.put("email",email);
        if (!order.isEmpty()) columns.put("\"order\"",order);
        return clientDAO.updateById(client_id,columns);
    }

    @Override
    public Long insert(Client client, boolean getID) {
        return clientDAO.insert(client, getID);
    }

    @Override
    public boolean delete(Long id) {
        if (clientDAO.deleteById(id)) {
            return true;
        }
        return false;
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
    public ArrayList<Client> getAll() {
        return clientDAO.getAll();
    }

    @Override
    public boolean updateOrder(Long client_id, Long order_id) {
        if (clientDAO.updateOrder(client_id,order_id)) return true;
        return false;
    }
    @Override
    public boolean increaseOrdersCount(Long client_id) {
        return clientDAO.increaseOrdersCount(client_id);
    }
}
