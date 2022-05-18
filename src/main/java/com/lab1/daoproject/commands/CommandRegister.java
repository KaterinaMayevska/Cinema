package com.lab1.daoproject.commands;

import com.lab1.daoproject.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandRegister extends CommandBase {
    private final UserService service = new UserService();

    private String redirect(String login, Long id, HttpServletRequest request) {
        request.getSession().setAttribute("id", id);
        request.getSession().setAttribute("login", login);
        action = CommandAction.Redirect;
        request.getSession().setAttribute("errorMsgRegister", null);
        return WELCOME_PAGE;
    }

    private String error(HttpServletRequest request) {
        var error = "Invalid data or user already exist!";
        request.getSession().setAttribute("errorMsgRegister", error);
        return REGISTER_PAGE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        if (login == "") {
            request.getSession().setAttribute("errorMsgRegister", "empty login");
            return REGISTER_PAGE;
        }
        if (service.isUserExist(login)) {
            System.out.println("user exist");
            request.getSession().setAttribute("errorMsgRegister", "user exists");
            return REGISTER_PAGE;
        }
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == "") {
            request.getSession().setAttribute("errorMsgRegister", "empty email");
            return REGISTER_PAGE;
        }

        Boolean isAdmin = false;

        var id = service.addUser(login, password, email, isAdmin);

        return (id == null) ? error(request) : redirect(login, id, request);
    }
}
