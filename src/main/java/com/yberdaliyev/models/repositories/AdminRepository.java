package com.yberdaliyev.models.repositories;

import com.yberdaliyev.models.entities.AdminEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Yerlan on 21.03.2017.
 */
public interface AdminRepository extends BaseRepository<AdminEntity,Long> {
    AdminEntity findByLogin(String login);
}