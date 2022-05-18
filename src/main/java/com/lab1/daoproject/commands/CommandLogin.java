package com.lab1.daoproject.commands;

import com.lab1.daoproject.dao.impl.UsersDaoImpl;
import com.lab1.daoproject.entities.Users;
import com.lab1.daoproject.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ObjectInputFilter;
import java.util.Objects;

public class CommandLogin extends CommandBase{
    private final UserService service = new UserService();

    private String redirect(String login, Long id, HttpServletRequest request) {
        request.getSession().setAttribute("id", id);
        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("errorMsgLogin", null);
        action = CommandAction.Redirect;
        return MAIN_PAGE;
    }
    private String error(HttpServletRequest request) {
        var error = "Invalid login or password!";
        request.getSession().setAttribute("errorMsgLogin", error);
        return LOGIN_PAGE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        if (Objects.equals(login, "")) {
            request.getSession().setAttribute("errorMsgLogin", "empty login");
            return LOGIN_PAGE;
        }
        String password = request.getParameter("password");
        var id = service.findUser(login, password);
        if(id!= null){
            request.setAttribute("user", login);
            UsersDaoImpl usersDao = new UsersDaoImpl();
            Users users = usersDao.findByLogin(login);
            redirect(login, id, request);
            HttpSession session = request.getSession();
            session.setAttribute("login", users.getLogin());
            session.setAttribute("userId", users.getId());
            session.setAttribute("isAdmin",users.getIsAdmin() );
        }
        else error(request);

        return id == null ? error(request) : redirect(login, id, request);
    }
}
