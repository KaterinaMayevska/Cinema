package com.lab1.daoproject.commands;

import com.lab1.daoproject.entities.Films;
import com.lab1.daoproject.entities.Seances;
import com.lab1.daoproject.entities.Tickets;
import com.lab1.daoproject.service.FilmService;
import com.lab1.daoproject.service.SeanceService;
import com.lab1.daoproject.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AllSeanceTickets extends CommandBase{

    private final TicketService service = new TicketService();
    private final SeanceService seanceService = new SeanceService();
    private final FilmService filmService = new FilmService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String filmName = request.getParameter("name");
        String seanceDate = request.getParameter("seanceDate");
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date fd = format.parse(seanceDate);
            java.sql.Date date = new java.sql.Date(fd.getTime());
            FilmService filmService = new FilmService();
            Films films = filmService.findByLogin(filmName);
            if(films == null){
                request.getSession().setAttribute("errorMsgTicket", "no such film");
                return TICKET_SEANCES_PAGE;}
            Long film_id = films.getId();

            var seances = seanceService.findByDate(date);
            if(seances == null){
                request.getSession().setAttribute("errorMsgTicket", "no such seance");
            }
            else {
                List<Tickets> ticketsList = service.getSeanceTickets(seances.getId());
                if(ticketsList.isEmpty()){
                    request.getSession().setAttribute("errorMsgTicket", "no tickets");
                    return TICKET_SEANCES_PAGE;}
                request.getSession().setAttribute("theTickets", ticketsList);
                request.getSession().setAttribute("theFilms", films);
                request.getSession().setAttribute("theSeances", seances);
                System.out.println("all tickets" + service.getAll());

            }
            return TICKET_SEANCES_PAGE;

        } catch (ParseException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMsgTicket", "incorrect date");
            return TICKET_SEANCES_PAGE;
        }


}}
