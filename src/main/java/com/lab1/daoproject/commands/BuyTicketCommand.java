package com.lab1.daoproject.commands;

import com.lab1.daoproject.entities.Tickets;
import com.lab1.daoproject.entities.Users;
import com.lab1.daoproject.service.SeanceService;
import com.lab1.daoproject.service.TicketService;
import com.lab1.daoproject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BuyTicketCommand extends CommandBase{
    private final TicketService service = new TicketService();
    private final UserService userService = new UserService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        Long ticketId = Long.valueOf(request.getParameter("ticketId"));
        System.out.println("ticke id is  "+ticketId);
        Tickets tickets = service.findById(ticketId);
        System.out.println("the ticket is  "+tickets);
        Users users = userService.findByLogin(login);
        System.out.println("the user is  "+users);
        tickets.setUser_id(users.getId());
        System.out.println("updated ticket is "+ tickets);
        service.updateTicket(tickets);
        return TICKET_SEANCES_PAGE;




    }
}
