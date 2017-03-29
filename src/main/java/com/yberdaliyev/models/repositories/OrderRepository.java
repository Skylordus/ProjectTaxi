package com.yberdaliyev.models.repositories;

import com.yberdaliyev.models.entities.OrderEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yerlan on 21.03.2017.
 */
public interface OrderRepository extends BaseRepository<OrderEntity,Long> {

    @Query("SELECT t FROM OrderEntity t WHERE t.driver IS NULL")
    List<OrderEntity> findByDriverEqualsNull();

    @Modifying
    @Transactional
    @Query("DELETE FROM OrderEntity t WHERE t.id = :id")
    void deleteById(@Param("id") Long id);
}
