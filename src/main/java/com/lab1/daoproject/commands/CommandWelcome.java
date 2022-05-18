package com.lab1.daoproject.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandWelcome extends CommandBase{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return WELCOME_PAGE;
    }

}
