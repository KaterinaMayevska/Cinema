package com.lab1.daoproject.dao;

import com.lab1.daoproject.entities.Films;
import com.lab1.daoproject.entities.Users;

import java.util.List;

public interface IUserDao extends Dao<Users>{
    Users findByLogin(String login);
    List<Users> findAll() ;
    void deleteById(Long id);
    void updateById(Long id, Users newUser);
}
