package com.lab1.daoproject.dao;

import com.lab1.daoproject.entities.Films;
import com.lab1.daoproject.entities.Seances;

import java.sql.Date;
import java.util.List;

public interface ISeanceDao extends Dao<Seances>{
    List<Seances> findAll() ;
    void deleteById(Long id);
    void updateById(Long id, Seances newSeance);
    Seances findById(Long filmId);
    List<Seances> findByFilm(Long filmId);
    Seances findByDate(Date date);


}
