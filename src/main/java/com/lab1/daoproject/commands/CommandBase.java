package com.lab1.daoproject.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandBase implements ICommand{
    protected CommandAction action = CommandAction.Forward;
    protected String MAIN_PAGE = "main.jsp";
    protected String LOGIN_PAGE = "login.jsp";
    protected String REGISTER_PAGE = "register.jsp";
    protected String WELCOME_PAGE = "welcome.jsp";
    protected String FILM_PAGE = "films.jsp";
    protected String ADD_FILM = "addFilms.jsp";
    protected String ADD_SEANCE = "addSeances.jsp";
    protected String USERS_PAGE = "users.jsp";
    protected String ALL_SEANCES_PAGE = "seances.jsp";
    protected String TICKET_SEANCES_PAGE = "seanceTickets.jsp";
    protected String USER_TICKET_PAGE = "userTicket.jsp";

    public CommandAction getActionType() { return action; }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("errorMsg", null);
            session.invalidate();
        }
        return WELCOME_PAGE;
    }
}
