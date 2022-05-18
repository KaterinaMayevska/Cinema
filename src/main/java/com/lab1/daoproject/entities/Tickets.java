package com.lab1.daoproject.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tickets")
public class Tickets extends EntityBase implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "line")
    private int line;

    @Column(name = "place")
    private int place;


  /*  @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Users user;

   */
    private Long user_id;


   /* @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seance_id")
    private Seances seance;

    */
    private Long seance_id;

    public Tickets(int line, int place, Long user, Long seance) {
        this.line = line;
        this.place = place;
        this.user_id = user;
        this.seance_id = seance;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
/*
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Seances getSeance() {
        return seance;
    }

    public void setSeance(Seances seance) {
        this.seance = seance;
    }


 */
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getSeance_id() {
        return seance_id;
    }

    public void setSeance_id(Long seance_id) {
        this.seance_id = seance_id;
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
        return "tickets";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (line, place, user_id, seance_id) VALUES(?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET line=?, place=?,user_id=?, " +
                "seance_id=? WHERE " + getIdName() + "=?";
    }

    @Override
    public void parse(ResultSet resultSet) throws SQLException {
        id = resultSet.getLong("id");
        line = resultSet.getInt("line");
        place= resultSet.getInt("place");
        user_id = resultSet.getLong("user_id");
        seance_id = resultSet.getLong("seance_id");

    }

    @Override
    public void serialize(PreparedStatement statement, boolean withId) throws SQLException {
        statement.setInt(1, line);
        statement.setInt(2, place);
        statement.setLong(3, user_id);
        statement.setLong(4, seance_id);
        if (withId) {
            statement.setLong(5, id);
        }
    }
}
