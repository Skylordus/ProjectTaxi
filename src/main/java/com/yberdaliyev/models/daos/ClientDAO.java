package com.yberdaliyev.models.daos;

import com.yberdaliyev.models.entities.ClientEntity;
import com.yberdaliyev.models.pojos.Client;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository
@Transactional
public class ClientDAO implements IClientDAO {
    private IUserDAO userDAO;

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    public ClientDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private static Logger logger = Logger.getLogger(ClientDAO.class);



    @Override
    public ClientEntity getById(long id) {

        return manager.find(ClientEntity.class, id);

    }

    @Override
    public Long insert(ClientEntity clientEntity, boolean getID) {

        manager.persist(clientEntity);
        return 0l;
    }


    @Override
    public List<ClientEntity> getAll() {

        List<ClientEntity> clients = manager.createQuery("Select a From ClientEntity a", ClientEntity.class).getResultList();
        return clients;

    }

    @Override
    public boolean updateById(Long id, Properties columns) {
        return false;
    }

    @Override
    public boolean updateOrder(Long clientid, Long orderid) {
        return false;

    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean increaseOrdersCount(Long client_id) {
        return false;
    }
}
