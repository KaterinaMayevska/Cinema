package com.lab1.daoproject.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class CommandHelper {
    private static CommandHelper instance = null;
    private CommandHelper() {}
    public HashMap<String, CommandBase> commandType = initMap();

    public HashMap<String, CommandBase> initMap() {
        HashMap<String, CommandBase> map = new HashMap<>();
        map.put("/", new CommandMainPage());
        map.put("main.jsp", new CommandMainPage());
        map.put("register", new CommandRegister());
        map.put("login", new CommandLogin());
        map.put("welcome", new CommandWelcome());
        map.put("addFilms", new AddFilmCommand());
        map.put("films", new AllFilmsCommand());
        map.put("seances", new AllSeancesCommand());
        map.put("addSeances", new AddSeancesCommand());
        map.put("seanceTickets", new AllSeanceTickets());
        map.put("users", new AllUsersCommand());
        map.put("buyTicket", new BuyTicketCommand());
        map.put("userTickets", new UserTicketCommand());
        map.put("deleteFilm", new DeleteFilmCommand());
        map.put("deleteSeance", new DeleteSeanceCommand());
        return map;
    }

    public static CommandHelper createInstance() {
        if (instance == null) {
            instance = new CommandHelper();
        }
        return instance;
    }



    public CommandBase getCommand(HttpServletRequest request){
        CommandBase command = commandType.get(request.getParameter("actionType"));
        System.out.println(command);
        var map = initMap();
        commandType.containsKey(command);


        if(command == null){
            command = new CommandMainPage();
        }
        return command;
    }







}
