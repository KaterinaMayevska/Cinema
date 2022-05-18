package com.lab1.daoproject.dao.impl;

import com.lab1.daoproject.dao.IFilmDao;
import com.lab1.daoproject.entities.Films;
import com.lab1.daoproject.entities.Users;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class FilmsDaoImpl extends DaoImpl<Films> implements IFilmDao {
    @Override
    public List<Films> findAll() {
        List<Films> filmsList = new ArrayList<>();
        try {
            Films films = new Films();
            String queryString = "SELECT * FROM " + films.getTableName();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Films films1 = new Films();
                films1.parse(resultSet);
                filmsList.add(films1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmsList;
    }

    @Override
    public Films findByLogin(String name){
        try {
            Films entity = new Films();
            String queryString = "SELECT * FROM " + entity.getTableName() +
                    " WHERE name =?";

            var ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, name);
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
    public Films findById(Long filmId){
        try {
            Films entity = new Films();
            String queryString = "SELECT * FROM " + entity.getTableName() +
                    " WHERE " + entity.getIdName() + "=?";

            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, filmId);
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
            Films entity = new Films();
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
   public void updateById(Long id, Films newfilm){
        try {
            Films entity = new Films();
            String queryString = entity.getUpdateQuery();
            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, id);
            newfilm.serialize(ptmt, true);
            ptmt.executeUpdate();
            System.out.println("Data updated Successfully from table " + entity.getTableName());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
   }


    public void updateFilm(Long id, String name, String director, String genre, Date release_date, String info) throws IllegalArgumentException{
        Films maybeFilm = findById(id);
        if(maybeFilm == null)throw new RuntimeException("Film not found (Sorry)");
        if(name!=null && !name.isBlank()) maybeFilm.setName(name);
        if(director!=null && !director.isBlank()) maybeFilm.setDirector(director);
        if(genre!=null && !genre.isBlank()) maybeFilm.setGenre(genre);
        long millis=System.currentTimeMillis();
        Date current_date=new Date(millis);
        if(release_date!=null && !release_date.after(current_date) ) maybeFilm.setReleaseDate( release_date);
        if(info!=null && !info.isBlank()) maybeFilm.setInfo(info);

        try {
            String queryString = maybeFilm.getUpdateQuery();
            ptmt = connection.prepareStatement(queryString);
            maybeFilm.serialize(ptmt, true);
            ptmt.executeUpdate();
            System.out.println("Table " + maybeFilm.getTableName() + " Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
