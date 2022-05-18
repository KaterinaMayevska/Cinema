package com.lab1.daoproject.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Long add(T t);
    void update(T t);
    void delete(T t);

    List<T> findAll();
    T findById(Long id);


}