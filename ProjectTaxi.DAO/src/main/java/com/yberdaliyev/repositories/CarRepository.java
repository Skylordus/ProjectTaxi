package com.yberdaliyev.repositories;

import com.yberdaliyev.entities.CarEntity;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

/**
 * Created by Yerlan on 21.03.2017.
 */
@Repository
public interface CarRepository extends BaseRepository<CarEntity,Long> {

}
