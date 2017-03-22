package com.yberdaliyev.models.repositories;

import com.yberdaliyev.models.entities.AdminEntity;
import com.yberdaliyev.models.entities.LoginEntity;
import org.springframework.data.repository.Repository;

/**
 * Created by Yerlan on 21.03.2017.
 */
public interface LoginRepository extends Repository<LoginEntity,String> {
    void deleteByLogin(String login);
    LoginEntity findOne(String login);
    LoginEntity save(LoginEntity persisted);
    void delete(LoginEntity deleted);
}
