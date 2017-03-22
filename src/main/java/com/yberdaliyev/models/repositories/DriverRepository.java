package com.yberdaliyev.models.repositories;

import com.yberdaliyev.models.entities.DriverEntity;

/**
 * Created by Yerlan on 21.03.2017.
 */
public interface DriverRepository extends BaseRepository<DriverEntity,Long> {
    DriverEntity findByLogin(String login);
}
