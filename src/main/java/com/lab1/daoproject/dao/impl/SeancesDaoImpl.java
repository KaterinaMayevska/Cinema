package com.lab1.daoproject.dao.impl;

import com.lab1.daoproject.dao.IFilmDao;
import com.lab1.daoproject.dao.ISeanceDao;
import com.lab1.daoproject.entities.Films;
import com.lab1.daoproject.entities.Seances;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeancesDaoImpl extends DaoImpl<Seances> implements ISeanceDao {

    @Override
    public List<Seances> findAll() {
        List<Seances> seancesList = new ArrayList<>();
        try {
            Seances seances =  new Seances();
            String queryString = "SELECT * FROM " + seances.getTableName();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Seances seances1 =  new Seances();
                seances1.parse(resultSet);
                seancesList.add(seances1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seancesList;
    }

    @Override
    public Seances findByDate(Date date){
        try {
            Seances entity = new Seances();
            String queryString = "SELECT * FROM " + entity.getTableName() +
                    " WHERE date =?";

            var ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, date.toString());
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
    public Seances findById(Long seanceId){
        try {
            Seances entity = new Seances();
            String queryString = "SELECT * FROM " + entity.getTableName() +
                    " WHERE " + entity.getIdName() + "=?";

            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, seanceId);
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
    public List<Seances> findByFilm(Long filmId){
        List<Seances> filmSeances = new ArrayList<>();
        try {
            Seances entity = new Seances();
            String queryString = "SELECT * FROM " + entity.getTableName() +
                    " WHERE film_Id=?";

            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, filmId);
            var resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                entity.parse(resultSet);
                filmSeances.add(entity);
            }
            return filmSeances;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void deleteById(Long id){
        try {
            Seances entity = new Seances();
            String queryString = "DELETE FROM " + entity.getTableName() +
                    " WHERE id =?";

            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, id);
            ptmt.executeUpdate();
            System.out.println("Data deleted Successfully from table " + entity.getTableName());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void updateById(Long id, Seances newSeance){
        try {
            Seances entity = new Seances();
            String queryString = entity.getUpdateQuery();
            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, id);
            newSeance.serialize(ptmt, true);
            ptmt.executeUpdate();
            System.out.println("Data updated Successfully from table " + entity.getTableName());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
