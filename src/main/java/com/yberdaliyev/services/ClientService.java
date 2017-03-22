package com.yberdaliyev.services;

import com.yberdaliyev.models.daos.IClientDAO;
import com.yberdaliyev.models.entities.ClientEntity;
import com.yberdaliyev.models.entities.OrderEntity;
import com.yberdaliyev.models.pojos.Car;
import com.yberdaliyev.models.pojos.Client;
import com.yberdaliyev.models.pojos.Order;
import com.yberdaliyev.models.repositories.ClientRepository;
import com.yberdaliyev.models.repositories.OrderRepository;
import com.yberdaliyev.models.transformers.EntityToPojoTransformer;
import com.yberdaliyev.models.transformers.PojoToEntityTransformer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

/**
 * Created by Yerlan on 26.02.2017.
 */
@Service
public class ClientService implements IClientService {
    private static Logger logger = Logger.getLogger(ClientService.class);
    private ClientRepository repository;

    private EntityToPojoTransformer entityToPojo;
    private PojoToEntityTransformer pojoToEntity;

    @Autowired
    public void setEntityToPojo(EntityToPojoTransformer entityToPojo) {this.entityToPojo = entityToPojo;}
    @Autowired
    public void setPojoToEntity(PojoToEntityTransformer pojoToEntity) {this.pojoToEntity = pojoToEntity;}
    @Autowired
    public void setRepository(ClientRepository repository) {this.repository = repository;}


    @Override
    public Client generateClient(Long id,
                                 String firstname,
                                 String lastname,
                                 String patronymic,
                                 Date date_registered,
                                 Integer orders_amount,
                                 Date birthdate,
                                 String login,
                                 String email,
                                 Order order) {
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
    public void updateClient(Long id,
                             String firstname,
                             String lastname,
                             String patronymic,
                             Date date_registered,
                             Integer orders_amount,
                             Date birthdate,
                             String login,
                             String email,
                             Order order) {

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

        repository.save(pojoToEntity.toClientEntity(client));
    }

    @Override
    public Long insert(Client client) {
        return repository.save(pojoToEntity.toClientEntity(client)).getId();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Client getClient(Long id) {
        Client client = entityToPojo.toClient(repository.findOne(id));
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
        logger.warn("getting all clients");
        List<ClientEntity> clientEntities = repository.findAll();
        List<Client> clients = new ArrayList<>();
        for (ClientEntity clientEntity : clientEntities) {
            clients.add(entityToPojo.toClient(clientEntity));
        }
        return clients;
    }

    @Override
    public void updateOrder(Long client_id, Long order_id) {
         ClientEntity clientEntity = repository.findOne(client_id);
         OrderEntity orderEntity = new OrderEntity();
         orderEntity.setId(order_id);
         clientEntity.setOrder(orderEntity);
         repository.save(clientEntity);
    }

    @Override
    public void increaseOrdersCount(Long client_id) {
        ClientEntity clientEntity = repository.findOne(client_id);
        clientEntity.setOrders_amount(clientEntity.getOrders_amount()+1);
        repository.save(clientEntity);
    }
}
