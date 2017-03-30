package com.yberdaliyev.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yerlan on 21.03.2017.
 */
@NoRepositoryBean

public interface BaseRepository<T,ID extends Serializable> extends Repository<T,ID> {
    @Transactional
    void delete(T deleted);
    @Transactional
    void deleteById(ID id);
    @Transactional
    List<T> findAll();
    @Transactional
    T findOne(ID id);

    @Transactional
    @Modifying
    T save(T persisted);
}
