package com.lab1.daoproject.commands;

import com.lab1.daoproject.entities.Films;
import com.lab1.daoproject.service.FilmService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllFilmsCommand extends CommandBase{
    private final FilmService service = new FilmService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("films", service.getAll());



        return FILM_PAGE;
    }
}
