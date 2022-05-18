package com.lab1.daoproject.commands;

import com.lab1.daoproject.entities.Seances;
import com.lab1.daoproject.entities.Tickets;
import com.lab1.daoproject.service.FilmService;
import com.lab1.daoproject.service.SeanceService;
import com.lab1.daoproject.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class DeleteFilmCommand extends CommandBase{
    private final FilmService service = new FilmService();
    private final SeanceService seanceService = new SeanceService();
    private final TicketService ticketService = new TicketService();

    private String updatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new AllFilmsCommand().execute(request, response);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filmId = request.getParameter("filmId");
        service.deleteFilm(Long.parseLong(filmId));

        List<Seances> seances = seanceService.getFilmSeances(Long.parseLong(filmId));
        for (int i=0; i<seances.size(); i++){
            List<Tickets> ticketsList = ticketService.getSeanceTickets(seances.get(i).getId());
            for(int j=0; j< ticketsList.size(); j++){ticketService.removeTicket(ticketsList.get(j).getId()); }
            seanceService.removeSeance(seances.get(i).getId());}
        String page = updatePage(request, response);


        return FILM_PAGE;
    }
}
