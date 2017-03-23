package com.yberdaliyev.models.repositories;

import com.yberdaliyev.models.entities.AdminEntity;
import com.yberdaliyev.models.entities.LoginEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Yerlan on 21.03.2017.
 */
public interface LoginRepository extends Repository<LoginEntity,String> {
    void deleteByLogin(String login);
    LoginEntity findOne(String login);
    LoginEntity save(LoginEntity persisted);
    void delete(LoginEntity deleted);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM LoginEntity c WHERE c.login = :login")
    boolean existsByLogin(@Param("login") String login);


    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM LoginEntity c WHERE c.email = :email")
    boolean existsByEmail(@Param("email") String email);

}
