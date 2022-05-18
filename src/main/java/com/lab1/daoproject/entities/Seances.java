package com.lab1.daoproject.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "seances")
public class Seances extends EntityBase implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    private Date date;

    @Column(name = "film_id")
    private Long film_id;


    public Seances(double price, Date date, Long film) {
        this.price = price;
        this.date = date;
        this.film_id = film;


    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String getIdName() {
        return "id";
    }


    @Override
    public String getTableName() {
        return "seances";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (date, price, film_id) VALUES(?,?,?)";
    }

    @Override
    public void parse(ResultSet resultSet) throws SQLException {
        id = resultSet.getLong("id");
        date = resultSet.getDate("date");
        price = resultSet.getDouble("price");
        film_id = resultSet.getLong("film_id");
    }

    @Override
    public void serialize(PreparedStatement statement, boolean withId) throws SQLException {
        statement.setDate(1, date);
        statement.setDouble(2, price);
        statement.setLong(3, film_id);
        if (withId) {
            statement.setLong(4, id);
        }
    }
}
