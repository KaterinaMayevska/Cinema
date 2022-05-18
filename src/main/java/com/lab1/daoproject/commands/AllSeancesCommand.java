package com.lab1.daoproject.commands;

import com.lab1.daoproject.entities.Seances;
import com.lab1.daoproject.service.SeanceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllSeancesCommand extends CommandBase{
    private final SeanceService service = new SeanceService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("allSeances", service.getAll());
        request.getSession().setAttribute("allFilmsS", service.getAllFilms());

        System.out.println(service.getAll());

        return ALL_SEANCES_PAGE;
    }
}
