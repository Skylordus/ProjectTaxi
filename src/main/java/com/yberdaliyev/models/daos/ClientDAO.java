//package com.yberdaliyev.models.daos;
//
//import com.yberdaliyev.models.entities.ClientEntity;
//import com.yberdaliyev.models.entities.LoginEntity;
//import com.yberdaliyev.models.enums.USER_ROLES;
//import com.yberdaliyev.models.pojos.Client;
//import com.yberdaliyev.models.transformers.EntityToPojoTransformer;
//import com.yberdaliyev.models.transformers.PojoToEntityTransformer;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Repository
//@Transactional
//public class ClientDAO implements IClientDAO {
//
//    private EntityToPojoTransformer entityToPojo;
//    private PojoToEntityTransformer pojoToEntity;
//
//    @PersistenceContext
//    private EntityManager manager;
//
//    @Autowired
//    public void setEntityToPojo(EntityToPojoTransformer entityToPojo) {this.entityToPojo = entityToPojo;}
//    @Autowired
//    public void setPojoToEntity(PojoToEntityTransformer pojoToEntity) {this.pojoToEntity = pojoToEntity;}
//
//    private static Logger logger = Logger.getLogger(ClientDAO.class);
//
//    @Override
//    public Client getById(long id) {
//        logger.warn("getting client by ID="+id);
//        ClientEntity clientEntity = manager.find(ClientEntity.class, id);
//        if (clientEntity == null) return null;
//        return entityToPojo.toClient(clientEntity);
//    }
//
//    @Override
//    public Long insert(Client client, boolean getID) {
//        logger.warn("trying to insert a new client");
//        ClientEntity clientEntity = pojoToEntity.toClientEntity(client);
//        logger.warn("inserting LOGIN first");
//        LoginEntity loginEntity = new LoginEntity(client.getLogin(),
//                client.getPwd(),
//                USER_ROLES.ROLE_CLIENT,
//                true);
//        manager.persist(loginEntity);
//        manager.persist(clientEntity);
//        logger.warn("inserting a new client with ID="+clientEntity.getId());
//        if (getID) {return clientEntity.getId();}
//        else return 0L;
//    }
//
//
//    @Override
//    public List<Client> getAll() {
//        logger.warn("getting all clients");
//        List<ClientEntity> clientEntities = manager.createQuery("Select a From ClientEntity a", ClientEntity.class).getResultList();
//
//        List<Client> clients = new ArrayList<>();
//        for (ClientEntity clientEntity : clientEntities) {
//            clients.add(entityToPojo.toClient(clientEntity));
//        }
//        return clients;
//    }
//
//    @Override
//    public void updateById(Long id, Client client) {
//        logger.warn("trying to update a client with id="+id);
//        ClientEntity clientEntity = manager.find(ClientEntity.class, id);
//        if (client.getFirstname() != null) clientEntity.setFirstname(client.getFirstname());
//        if (client.getLastname() != null) clientEntity.setLastname(client.getLastname());
//        if (client.getPatronymic() != null) clientEntity.setPatronymic(client.getPatronymic());
//        if (client.getDate_registered() != null) clientEntity.setDate_registered(client.getDate_registered());
//        if (client.getEmail() != null) clientEntity.setEmail(client.getEmail());
//        if (client.getBirthdate() != null) clientEntity.setBirthdate(client.getBirthdate());
//        if (client.getLogin() != null) clientEntity.setLogin(client.getLogin());
//        if (client.getOrders_amount() > 0) clientEntity.setOrders_amount(client.getOrders_amount());
//        if (client.getOrder() > 0) clientEntity.setOrder(client.getOrder());
//    }
//
//    @Override
//    public void updateOrder(Long clientid, Long orderid) {
//        logger.warn("updating client ID="+clientid+" with order ID="+orderid);
//        ClientEntity clientEntity = manager.find(ClientEntity.class, clientid);
//        clientEntity.setOrder(orderid);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        logger.warn("deleting client with id="+id);
//        ClientEntity clientEntity = manager.find(ClientEntity.class, id);
//        manager.remove(clientEntity);
//    }
//
//    @Override
//    public void increaseOrdersCount(Long client_id) {
//        logger.warn("increasnig orders count of client id="+client_id);
//        ClientEntity clientEntity = manager.find(ClientEntity.class, client_id);
//        clientEntity.setOrders_amount(clientEntity.getOrders_amount()+1);
//    }
//}
