package com.yberdaliyev.models.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yerlan on 21.03.2017.
 */
@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends Repository<T,ID> {
    void delete(T deleted);
    void deleteById(ID id);
    List<T> findAll();
    T findOne(ID id);
    T save(T persisted);
}
