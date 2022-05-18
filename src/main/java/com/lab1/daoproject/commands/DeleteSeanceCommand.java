package com.lab1.daoproject.commands;

import com.lab1.daoproject.entities.Seances;
import com.lab1.daoproject.entities.Tickets;
import com.lab1.daoproject.service.FilmService;
import com.lab1.daoproject.service.SeanceService;
import com.lab1.daoproject.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteSeanceCommand extends CommandBase{
    private final SeanceService seanceService = new SeanceService();
    private final TicketService ticketService = new TicketService();

    private String updatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new AllSeancesCommand().execute(request, response);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String seanceId = request.getParameter("seanceId");
        seanceService.removeSeance(Long.parseLong(seanceId));
        List<Tickets> ticketsList = ticketService.getSeanceTickets(Long.parseLong(seanceId));
        for(int j=0; j< ticketsList.size(); j++){ticketService.removeTicket(ticketsList.get(j).getId()); }

        String page = updatePage(request, response);


        return ALL_SEANCES_PAGE;
    }
}
