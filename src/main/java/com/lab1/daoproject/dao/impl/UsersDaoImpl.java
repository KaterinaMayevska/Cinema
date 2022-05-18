package com.lab1.daoproject.dao.impl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.lab1.daoproject.dao.IUserDao;
import com.lab1.daoproject.entities.Users;

public class UsersDaoImpl extends DaoImpl<Users> implements IUserDao {

    @Override
    public List<Users> findAll() {
        List<Users> usersList = new ArrayList<>();
        try {
            Users users =  new Users();
            String queryString = "SELECT * FROM " + users.getTableName();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Users users1 = new Users();
                users1.parse(resultSet);
                usersList.add(users1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public Users findById(Long userId){
        try {
            Users entity = new Users();
            String queryString = "SELECT * FROM " + entity.getTableName() +
                    " WHERE " + entity.getIdName() + "=?";

            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, userId);
            var resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                entity.parse(resultSet);
                return entity;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Users findByLogin(String login){
        try {
            Users entity = new Users();
            String queryString = "SELECT * FROM " + entity.getTableName() +
                    " WHERE login =?";

            var ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, login);
            var resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                entity.parse(resultSet);
                return entity;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void deleteById(Long id){
        try {
            Users entity = new Users();
            String queryString = "DELETE FROM " + entity.getTableName() +
                    " WHERE " + entity.getIdName() + "=?";

            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, id);
            ptmt.executeUpdate();
            System.out.println("Data deleted Successfully from table " + entity.getTableName());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateById(Long id, Users newUser){
        try {
            Users entity = new Users();
            String queryString = entity.getUpdateQuery();
            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, id);
            newUser.serialize(ptmt, true);
            ptmt.executeUpdate();
            System.out.println("Data updated Successfully from table " + entity.getTableName());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }













}
