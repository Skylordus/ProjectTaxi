package com.yberdaliyev.models.repositories;

import com.yberdaliyev.models.entities.DriverEntity;
import com.yberdaliyev.models.entities.LoginEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Yerlan on 21.03.2017.
 */
public interface DriverRepository extends BaseRepository<DriverEntity,Long> {

    DriverEntity findByLogin(LoginEntity loginEntity);
}
