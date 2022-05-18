package com.lab1.daoproject;

import com.lab1.daoproject.commands.*;
import com.lab1.daoproject.service.FilmService;
import lombok.SneakyThrows;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    public Controller(){
        super();
    }

    CommandHelper commandHelper = CommandHelper.createInstance();
    @Override
    public void init() {
        commandHelper = CommandHelper.createInstance();
    }

   // @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
        System.out.println("the command is " + request.getParameter("actionType"));
    }

  //  @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        System.out.println("the command is " + request.getParameter("actionType"));
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            System.out.println(request);


            CommandBase command = commandHelper.getCommand(request);
            System.out.println(request.getRequestURI());
            System.out.println(command);
            if (command == null) {
                System.out.println("ERROR: Invalid command received");
                return;
            }
            var com = command.execute(request, response);



            RequestDispatcher rd = request.getRequestDispatcher(com);
            rd.forward(request, response);


        } catch (IOException | ServletException throwables) {
            throwables.printStackTrace();
        }


    }
}