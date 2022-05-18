package com.lab1.daoproject.dao;

import com.lab1.daoproject.dao.impl.FilmsDaoImpl;
import com.lab1.daoproject.dao.impl.SeancesDaoImpl;
import com.lab1.daoproject.dao.impl.TicketsDaoImpl;
import com.lab1.daoproject.dao.impl.UsersDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;
    public IFilmDao createFilmDao() { return new FilmsDaoImpl();    }
    public IUserDao createUserDao(){ return new UsersDaoImpl();
    }
    public ITicketDao createTicketDao() { return new TicketsDaoImpl();
    }
    public ISeanceDao createSeanceDao() {return new SeancesDaoImpl();
    }


    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }
}
