package com.lab1.daoproject.dao;

import com.lab1.daoproject.entities.Films;

import java.util.List;

public interface IFilmDao extends Dao<Films>{
    List<Films> findAll() ;
    void deleteById(Long id);
    void updateById(Long id, Films newFilm);
    Films findById(Long filmId);
    Films findByLogin(String name);
}
