package com.lab1.daoproject.commands;

import com.lab1.daoproject.dao.impl.FilmsDaoImpl;
import com.lab1.daoproject.entities.Films;
import com.lab1.daoproject.service.FilmService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddFilmCommand extends CommandBase{
    private final FilmService service = new FilmService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        request.setAttribute("name",name);
        System.out.println("the name is " + name);

        String director = request.getParameter("director");
        String releaseDate = request.getParameter("releaseDate");
        String genre = request.getParameter("genre");
        String info = request.getParameter("info");
        System.out.println("Get new film");
        try {
            System.out.println("get data");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fd = format.parse(releaseDate);
            java.sql.Date date = new java.sql.Date(fd.getTime());
            System.out.println(releaseDate);
            Films films = new Films(name, director, date ,  genre, info );
            System.out.println(name);
            System.out.println(films);
            var id = service.findByLogin(name);
            if(id == null){
                service.createFilm(films);
            }
            else {request.getSession().setAttribute("errorMsgFilm", "film already exists");
                return ADD_FILM;}

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("invalid date");
        }


        return MAIN_PAGE;
    }


}
