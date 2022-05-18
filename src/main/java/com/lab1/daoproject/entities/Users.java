package com.lab1.daoproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users extends EntityBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name="login", unique = true)
    private String login;

    @Column(name="password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name="is_admin")
    private Boolean isAdmin ;
    public Users( String login, String password, String email, Boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public String getUsername() {
        return login;
    }




    @Override
    public void parse(ResultSet resultSet) throws SQLException {
        id = resultSet.getLong(getIdName());
        login = resultSet.getString("login");
        password = resultSet.getString("password");
        email = resultSet.getString("email");
        isAdmin = resultSet.getBoolean("is_admin");

    }

    @Override
    public void serialize(PreparedStatement statement, boolean withId) throws SQLException {
        statement.setString(1, login);
        statement.setString(2, password);
        statement.setString(3, email);
        statement.setBoolean(4, isAdmin);
        if (withId) {
            statement.setLong(5, id);
        }
    }


    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + getTableName() +
                " (login, password, email, is_admin) VALUES(?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET login=?, password=?, email=? WHERE " + getIdName() + "=?";
    }

    @Override
    public String getIdName() {
        return "id";
    }


}
