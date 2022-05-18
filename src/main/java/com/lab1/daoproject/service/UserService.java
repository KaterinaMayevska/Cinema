package com.lab1.daoproject.service;

import com.lab1.daoproject.dao.DaoFactory;
import com.lab1.daoproject.entities.Films;
import com.lab1.daoproject.entities.Users;
import com.mysql.cj.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class UserService {

    private static final DaoFactory factory = DaoFactory.getInstance();



    private Boolean isDataValid(List<String> data) {
        for (var str: data) {
            if (StringUtils.isNullOrEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public Users findByLogin(String login){
        return factory.createUserDao().findByLogin(login);
    }

    public List<Users> getAll() {
        return factory.createUserDao().findAll();
    }

    private Users createUser(String login, String password, String email, Boolean isAdmin) {
        if ( !email.contains("@")) {
                return null;
            }
        return new Users(login, password, email, isAdmin);

    }

    public Boolean isUserExist(String login) {
        return login != null && factory.createUserDao().findByLogin(login) != null;
    }

    public Long addUser(String login, String password, String email, Boolean isAdmin) {
        var user = createUser(login, password, email, isAdmin);
        return user == null ? null : factory.createUserDao().add(user);
    }

    public Long findUser(String login, String password) {
        List<String> data = Arrays.asList(login, password);
        if (!isDataValid(data)) {
            return null;
        }
        Users user = factory.createUserDao().findByLogin(login);
        if (user == null ||
                !user.getPassword().equals(password)) {
            return null;
        }
        return user.getId();
    }
}
