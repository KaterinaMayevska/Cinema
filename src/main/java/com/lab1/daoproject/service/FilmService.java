package com.lab1.daoproject.service;

import com.lab1.daoproject.dao.DaoFactory;
import com.lab1.daoproject.entities.Films;
import com.mysql.cj.util.StringUtils;

import java.util.List;

public class FilmService {
    private static DaoFactory factory = DaoFactory.getInstance();

    public List<Films> getAll() {
        return factory.createFilmDao().findAll();
    }

    public Films findByLogin(String name){
        return factory.createFilmDao().findByLogin(name);
    }

    public Films findById(Long id){ return factory.createFilmDao().findById(id);}

    public void deleteFilm(Long id){ factory.createFilmDao().deleteById(id);}

    public void createFilm(Films films) {
        var foodId = factory.createFilmDao().add(films);
           }




}
