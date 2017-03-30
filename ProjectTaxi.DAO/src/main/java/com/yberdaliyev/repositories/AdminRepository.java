package com.yberdaliyev.repositories;

import com.yberdaliyev.entities.AdminEntity;
import com.yberdaliyev.entities.LoginEntity;

/**
 * Created by Yerlan on 21.03.2017.
 */
public interface AdminRepository extends BaseRepository<AdminEntity,Long> {
    AdminEntity findByLogin(LoginEntity loginEntity);
}
