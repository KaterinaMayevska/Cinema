package com.lab1.daoproject.commands;

import com.lab1.daoproject.service.FilmService;
import com.lab1.daoproject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllUsersCommand extends CommandBase{

    private final UserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("users", service.getAll());



        return USERS_PAGE;
    }
}
