package com.lab1.daoproject.dao.impl;

import com.lab1.daoproject.dao.ISeanceDao;
import com.lab1.daoproject.dao.ITicketDao;
import com.lab1.daoproject.entities.Films;
import com.lab1.daoproject.entities.Seances;
import com.lab1.daoproject.entities.Tickets;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketsDaoImpl extends DaoImpl<Tickets> implements ITicketDao {

    @Override
    public List<Tickets> findAll() {
        List<Tickets> ticketsList = new ArrayList<>();;
        try {
            Tickets tickets =  new Tickets();
            String queryString = "SELECT * FROM " + tickets.getTableName();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Tickets tickets1 = new Tickets();
                tickets1.parse(resultSet);
                ticketsList.add(tickets1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketsList;
    }

    @Override
    public Tickets findById(Long ticketId){
        try {
            Tickets entity = new Tickets();
            String queryString = "SELECT * FROM " + entity.getTableName() +
                    " WHERE " + entity.getIdName() + "=?";
            System.out.println(queryString);
            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, ticketId);
            var resultSet = ptmt.executeQuery();
            if (resultSet.next()) {
                entity.parse(resultSet);
                return entity;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateTicket(Tickets tickets) throws IllegalArgumentException{
        try {
            String queryString = tickets.getUpdateQuery();
            System.out.println(queryString);
            ptmt = connection.prepareStatement(queryString);
            tickets.serialize(ptmt, true);
            ptmt.executeUpdate();
            System.out.println("Table " + tickets.getTableName() + " Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Tickets> findBySeance(Long seanceId) throws NullPointerException{
        List<Tickets> seanceTickets = new ArrayList<>();
        try {
            Tickets entity = new Tickets();
            String queryString = "SELECT * FROM " + entity.getTableName() +
                    " WHERE seance_id =?";
            System.out.println(queryString);
            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, seanceId);
            var resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Tickets tickets1 = new Tickets();
                tickets1.parse(resultSet);
                seanceTickets.add(tickets1);
            }

            return seanceTickets;
        } catch (SQLException e) {
            System.out.println("null pointer");
        }
        return null;
    }

    @Override
    public void deleteById(Long id){
        try {
            Tickets entity = new Tickets();
            String queryString = "DELETE FROM " + entity.getTableName() +
                    " WHERE id =?";
            System.out.println(queryString);
            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, id);
            ptmt.executeUpdate();
            System.out.println("Data deleted Successfully from table " + entity.getTableName());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void updateById(Long id, Tickets newTicket){
        try {
            Tickets entity = new Tickets();
            String queryString = entity.getUpdateQuery();
            var ptmt = connection.prepareStatement(queryString);
            ptmt.setLong(1, id);
            newTicket.serialize(ptmt, true);
            ptmt.executeUpdate();
            System.out.println("Data updated Successfully from table " + entity.getTableName());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
