package com.lab1.daoproject.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "films")
public class Films extends EntityBase{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "director")
    private String director;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "genre")
    private String genre;

    @Column(name = "info")
    private String info;

    public Films(String name, String director, Date releaseDate, String genre, String info) {
        this.name = name;
        this.director = director;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.info = info;
    }




    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<Seances> seances;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Films films = (Films) o;
        return Objects.equals(id, films.id) && Objects.equals(name, films.name) && Objects.equals(director, films.director) && Objects.equals(releaseDate, films.releaseDate) && Objects.equals(genre, films.genre) && Objects.equals(info, films.info) && Objects.equals(seances, films.seances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, director, releaseDate, genre, info, seances);
    }

    @Override
    public String getTableName() {
        return "films";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + getTableName() +
                " (name, director, genre, release_date, info) VALUES(?,?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET name=?, director=?, genre=?," +
                "release_date=?, info=? WHERE " + getIdName() + "=?";
    }

    @Override
    public String getIdName() {
        return "id";
    }

    @Override
    public void parse(ResultSet resultSet) throws SQLException {
        id = resultSet.getLong(getIdName());
        name = resultSet.getString("name");
        director = resultSet.getString("director");
        genre = resultSet.getString("genre");
        releaseDate = resultSet.getDate("release_date");
        info = resultSet.getString("info");
    }
    public boolean isValid() {
        return !name.isEmpty() && !director.isEmpty() && !genre.isEmpty();
    }

    @Override
    public void serialize(PreparedStatement statement, boolean withId) throws SQLException {
        statement.setString(1, name);
        statement.setString(2, director);
        statement.setString(3, genre);
        statement.setDate(4, releaseDate);
        statement.setString(5, info);
        if (withId) {
            statement.setLong(6, id);
        }
    }
}
