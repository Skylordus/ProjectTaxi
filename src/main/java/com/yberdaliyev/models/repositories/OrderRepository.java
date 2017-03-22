package com.yberdaliyev.models.repositories;

import com.yberdaliyev.models.entities.OrderEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Yerlan on 21.03.2017.
 */
public interface OrderRepository extends BaseRepository<OrderEntity,Long> {

    @Query("SELECT t FROM OrderEntity t WHERE t.driver = 'null'")
    List<OrderEntity> findByDriverEqualsNull();

}
