package com.yberdaliyev.models.repositories;

import com.yberdaliyev.models.entities.ClientEntity;
import com.yberdaliyev.models.entities.LoginEntity;

/**
 * Created by Yerlan on 21.03.2017.
 */
public interface ClientRepository extends BaseRepository<ClientEntity,Long> {
    ClientEntity findByLogin(String login);
}
