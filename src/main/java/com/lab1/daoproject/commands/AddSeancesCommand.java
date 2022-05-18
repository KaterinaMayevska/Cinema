package com.lab1.daoproject.commands;

import com.lab1.daoproject.entities.Films;
import com.lab1.daoproject.entities.Seances;
import com.lab1.daoproject.service.FilmService;
import com.lab1.daoproject.service.SeanceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddSeancesCommand extends CommandBase{
    private final SeanceService service = new SeanceService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String releaseDate = request.getParameter("date");
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date fd = format.parse(releaseDate);
            java.sql.Date date = new java.sql.Date(fd.getTime());
            FilmService filmService = new FilmService();
            Films films = filmService.findByLogin(name);
            if(films == null){
                request.getSession().setAttribute("errorMsgSeance", "no such film");
                return ADD_SEANCE;}
            Long film_id = films.getId();
            double pr=Double.parseDouble(price);
            if(pr<0){
                request.getSession().setAttribute("errorMsgSeance", "invalid price");
                return ADD_SEANCE;
            }

            var id = service.findByDate(date);
            if(id == null){
                Seances newSeance = new Seances(pr, date, film_id);
                service.createSeance(newSeance);
            }
            else {request.getSession().setAttribute("errorMsgSeance", "seance already exists");
                return ADD_SEANCE;}

        } catch (ParseException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMsgSeance", "incorrect date");
            return ADD_SEANCE;
        }


        return MAIN_PAGE;
    }

}
