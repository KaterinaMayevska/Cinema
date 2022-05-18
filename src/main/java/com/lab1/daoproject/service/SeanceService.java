package com.lab1.daoproject.service;

import com.lab1.daoproject.dao.DaoFactory;
import com.lab1.daoproject.dao.ITicketDao;
import com.lab1.daoproject.dao.impl.FilmsDaoImpl;
import com.lab1.daoproject.dao.impl.TicketsDaoImpl;
import com.lab1.daoproject.entities.Films;
import com.lab1.daoproject.entities.Seances;
import com.lab1.daoproject.entities.Tickets;
import com.mysql.cj.util.StringUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SeanceService {
    private static DaoFactory factory = DaoFactory.getInstance();
    private static TicketService ticketService = new TicketService();

    public List<Seances> getAll() {
        return factory.createSeanceDao().findAll();
    }

    public Seances findByDate(Date date){
        return factory.createSeanceDao().findByDate(date);
    }

    public Seances findById (Long id){ return factory.createSeanceDao().findById(id);}

    public List<Films> getAllFilms(){
        List<Seances> seances = factory.createSeanceDao().findAll();
        FilmsDaoImpl filmsDao = new FilmsDaoImpl();
        List<Films> films = new ArrayList<>();
        for (int i = 0; i< seances.size(); i++ ){

            films.add(filmsDao.findById(seances.get(i).getFilm_id()));

        }
        return  films;
    }

    public List<Seances> getFilmSeances(Long filmId) {
        return factory.createSeanceDao().findByFilm(filmId);
    }

    public void createSeance(Seances seances) {
        if (seances.getFilm_id() != null && factory.createFilmDao().findById(seances.getFilm_id()) != null) {
            Long new_seance_id = factory.createSeanceDao().add(seances);
            ITicketDao ticketsDao = factory.createTicketDao();
            for(int i=1; i<6; i++){
                for(int j=1; j<6; j++){
                    Tickets tickets = new Tickets(i, j, 1L, new_seance_id);
                    // System.out.println(tickets);
                    ticketService.createTicket(tickets);

                }
            }


        }
        System.out.println("no such film ");
    }



    public Boolean removeSeance(Long seanceId) {
        try {
            factory.createSeanceDao().deleteById(seanceId);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }
}
