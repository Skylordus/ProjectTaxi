package com.yberdaliyev.repositories;

import com.yberdaliyev.entities.DriverEntity;
import com.yberdaliyev.entities.LoginEntity;

/**
 * Created by Yerlan on 21.03.2017.
 */
public interface DriverRepository extends BaseRepository<DriverEntity,Long> {

    DriverEntity findByLogin(LoginEntity loginEntity);
}
