package com.lab1.daoproject.runner;

import com.lab1.daoproject.Controller;
import com.lab1.daoproject.commands.AllFilmsCommand;
import com.lab1.daoproject.dao.DaoFactory;
import com.lab1.daoproject.dao.IFilmDao;
import com.lab1.daoproject.dao.impl.FilmsDaoImpl;
import com.lab1.daoproject.dao.impl.SeancesDaoImpl;
import com.lab1.daoproject.dao.impl.TicketsDaoImpl;
import com.lab1.daoproject.dao.impl.UsersDaoImpl;
import com.lab1.daoproject.entities.*;
import com.lab1.daoproject.service.FilmService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class TestConnection {
    public static Connection connection;
    public static Statement statement = null;

    public static void main(String[] args) {


        try {
            System.out.println("Connection test");
            ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
            System.out.println("Connected to DB :)");
            System.out.println("/////////////////////");
            System.out.println("");

            System.out.println("Films test");
            FilmsDaoImpl filmDao = new FilmsDaoImpl();
            List<Films> films = filmDao.findAll();
            System.out.println(films);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String from_date = "2001-12-19";
            Date fd = format.parse(from_date);
            java.sql.Date sqlDate = new java.sql.Date(fd.getTime());
            Films newFilm = new Films("Lord of Rings again", "Peter Jackson", sqlDate, "fantasy", "no info");
            newFilm.setId(3L);
            filmDao.add(newFilm);
            filmDao.deleteById(2L);
            filmDao.updateById(3L, newFilm);
            try {
                filmDao.updateFilm(11L, "Lord of Rings", "Peter Jackson", "fantasy", sqlDate, "no info");
            } catch (Exception e) {
                System.out.println(e);
            }
            filmDao.deleteById(11L);
            filmDao.cleanUp();

            System.out.println("////////");

            System.out.println("User test");
            UsersDaoImpl userDao = new UsersDaoImpl();
            List<Users> usersList = userDao.findAll();
            System.out.println(usersList);

            Users users = new Users("anna", "11245", "1@gmail.com", true);
            try {
                userDao.add(users);
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(userDao.findByLogin("a"));
            System.out.println("////////");


            userDao.cleanUp();

            System.out.println("Seances test");
            SeancesDaoImpl seancesDao = new SeancesDaoImpl();
            DateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String from_dateTime = "2021-12-19 00:00:00";
            java.sql.Date timeValue = new java.sql.Date(formatTime.parse(from_dateTime).getTime());
            //  Time newTime = new fdT.getTime();
            // String formattedDate = myDate.format(myFormatObj);

            Seances seances = new Seances(150.00, timeValue, 5L);
            seancesDao.add(seances);
            List<Seances> seancesList = seancesDao.findAll();
            System.out.println(seancesList);
            Seances findseance = seancesDao.findById(6L);
            System.out.println(findseance);
            seancesDao.cleanUp();
            System.out.println("////////////////");


            System.out.println("Tickets test");
            Tickets tickets = new Tickets(11, 12, null, 4L);
            TicketsDaoImpl ticketsDao = new TicketsDaoImpl();
            ticketsDao.add(tickets);
            List<Tickets> ticketsList = ticketsDao.findAll();
            System.out.println(ticketsList);
            ticketsDao.cleanUp();

            System.out.println("///////////////////");
            FilmService filmService = new FilmService();
            System.out.println(filmService.getAll());
            filmService.createFilm(newFilm);









        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());

        }
    }
}
