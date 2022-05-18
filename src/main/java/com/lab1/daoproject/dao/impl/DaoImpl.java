package com.lab1.daoproject.dao.impl;

import com.lab1.daoproject.dao.Dao;
import com.lab1.daoproject.entities.ConnectionFactory;
import com.lab1.daoproject.entities.EntityBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoImpl<T extends EntityBase> implements Dao<T> {
    Connection connection;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public DaoImpl() {
        try {
            connection = ConnectionFactory.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long add(T t) {
        try {
            String queryString = t.getInsertQuery();
            ptmt = connection.prepareStatement(queryString);
            t.serialize(ptmt, false);
            ptmt.executeUpdate();
            queryString = "SELECT LAST_INSERT_ID();";
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            System.out.println("Element Added Successfully in Table " + t.getTableName());
            if (resultSet.next()) {
                return resultSet.getLong("LAST_INSERT_ID()");
            }
        } catch (SQLException e ) {
            System.out.println(e);
          //  e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public void update(T t) {
        try {
            String queryString = t.getUpdateQuery();
            ptmt = connection.prepareStatement(queryString);
            t.serialize(ptmt, true);
            ptmt.executeUpdate();
            System.out.println("Table " + t.getTableName() + " Updated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T t) {
        try {
            String queryString = "DELETE FROM " + t.getTableName() +
                    " WHERE " + t.getIdName() + "=?";

            ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, t.getId());
            ptmt.executeUpdate();
            System.out.println("Data deleted Successfully from table " + t.getTableName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<T> findAll() { return null; }

    @Override
    public T findById(Long id) { return null; }

    public void cleanUp() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (ptmt != null) {
                ptmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
