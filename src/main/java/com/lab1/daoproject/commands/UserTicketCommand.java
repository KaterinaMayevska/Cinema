package com.lab1.daoproject.commands;

import com.lab1.daoproject.entities.Films;
import com.lab1.daoproject.entities.Seances;
import com.lab1.daoproject.entities.Tickets;
import com.lab1.daoproject.service.FilmService;
import com.lab1.daoproject.service.SeanceService;
import com.lab1.daoproject.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserTicketCommand extends CommandBase{
    private final TicketService service = new TicketService();
    private final SeanceService seanceService = new SeanceService();
    private final FilmService filmService = new FilmService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        List<Tickets> ticketsList = service.getAll();
        List<Seances> seancesList = service.getAllSeances();
        List<Films> filmsList = service.getAllFilms();
        List<Tickets> userTickets = new ArrayList<>();
        List<Seances> userSeances = new ArrayList<>();
        List<Films> userFilms = new ArrayList<>();
        for (int i = 0; i< ticketsList.size(); i++){
            if(ticketsList.get(i).getUser_id() == userId){
                userTickets.add(ticketsList.get(i));
                Seances uSeance = seanceService.findById(ticketsList.get(i).getSeance_id());
                userSeances.add(uSeance);
                userFilms.add(filmService.findById(uSeance.getFilm_id()));
            }
        }
        request.getSession().setAttribute("myTickets", userTickets);
        request.getSession().setAttribute("myFilms", userFilms);
        request.getSession().setAttribute("mySeances", userSeances);
        return USER_TICKET_PAGE;



    }
}
