package com.lab1.daoproject.service;

import com.lab1.daoproject.dao.DaoFactory;
import com.lab1.daoproject.dao.ITicketDao;
import com.lab1.daoproject.dao.impl.FilmsDaoImpl;
import com.lab1.daoproject.dao.impl.SeancesDaoImpl;
import com.lab1.daoproject.dao.impl.TicketsDaoImpl;
import com.lab1.daoproject.entities.Films;
import com.lab1.daoproject.entities.Seances;
import com.lab1.daoproject.entities.Tickets;
import com.mysql.cj.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private static DaoFactory factory = DaoFactory.getInstance();

    public List<Tickets> getAll() {
        return factory.createTicketDao().findAll();
    }

    public List<Tickets> getSeanceTickets(Long seanceId) throws NullPointerException{
        try {List<Tickets> ticketsList = factory.createTicketDao().findBySeance(seanceId);
            System.out.println("the tickets are "+ticketsList);
            return ticketsList;}
        catch (NullPointerException e) { return null;}

    }

    public Tickets findById (Long ticketId){
        return factory.createTicketDao().findById(ticketId);
    }

    public void updateTicket (Tickets tickets){
        ITicketDao ticketsDao = factory.createTicketDao();
        ticketsDao.updateTicket(tickets);

    }
    public List<Seances> getAllSeances(){
        List<Tickets> ticketsList = factory.createTicketDao().findAll();
        SeancesDaoImpl seancesDao = new SeancesDaoImpl();
        List<Seances> seances = new ArrayList<>();
        for (int i = 0; i< ticketsList.size(); i++ ){

            seances.add(seancesDao.findById(ticketsList.get(i).getSeance_id()));

        }
        return  seances;
    }
    public List<Films> getAllFilms(){
        List<Seances> seances = getAllSeances();
        FilmsDaoImpl filmsDao = new FilmsDaoImpl();
        List<Films> films = new ArrayList<>();
        for (int i = 0; i< seances.size(); i++ ){

            films.add(filmsDao.findById(seances.get(i).getFilm_id()));

        }
        return  films;
    }







    public void createTicket(Tickets tickets ) {
        if (tickets.getSeance_id() != null && factory.createSeanceDao().findById(tickets.getSeance_id()) != null) {
            factory.createTicketDao().add(tickets);
        }
        System.out.println("no such seance" );
    }



    public Boolean removeTicket(Long ticketId) {
        try {
            factory.createTicketDao().deleteById(ticketId);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }
}
