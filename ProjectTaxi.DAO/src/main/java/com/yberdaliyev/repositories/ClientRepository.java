package com.yberdaliyev.repositories;

import com.yberdaliyev.entities.ClientEntity;
import com.yberdaliyev.entities.LoginEntity;

/**
 * Created by Yerlan on 21.03.2017.
 */
public interface ClientRepository extends BaseRepository<ClientEntity,Long> {

    ClientEntity findByLogin(LoginEntity loginEntity);
}
