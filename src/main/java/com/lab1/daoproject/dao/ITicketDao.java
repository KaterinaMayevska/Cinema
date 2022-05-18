package com.lab1.daoproject.dao;

import com.lab1.daoproject.entities.Seances;
import com.lab1.daoproject.entities.Tickets;

import java.util.List;

public interface ITicketDao extends Dao<Tickets>{
    List<Tickets> findAll() ;
    void deleteById(Long id);
    void updateById(Long id, Tickets newTicket);
    Tickets findById(Long ticketId);
    List<Tickets> findBySeance(Long seanceId);
    void updateTicket(Tickets tickets);
}
