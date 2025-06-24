package controller;

import boundery.BounderyEnum;
import dao.user.UserDAO;
import dao.user.UserDAODB;
import dao.user.UserDAOFile;
import dao.user.UserDAONoPersistance;
import entity.PersistencyPolicy;
import entity.SessionInfo;
import entity.User;
import exceptions.DAOException;

public class AuthenticateController {

    UserDAO dao;

    public AuthenticateController() {
        //No set up needed
    }

    public void login(String username, String password, PersistencyPolicy persistency_policy){

        SessionInfo session_info = SessionInfo.get_session_info();

        try {
            switch (persistency_policy) {
                case DB:
                    dao = new UserDAODB();
                    break;
                case FILE:
                    dao = new UserDAOFile();
                    break;
                case NO_PERSISTANCE:
                    dao = new UserDAONoPersistance();
                    break;
                case NULL:
                    System.out.println("[EE] No persistency policy was selected");
                    throw new IllegalArgumentException("No persistency policy was selected");   //No persistency_policy was selected
            }

            User user = dao.authenticate(username, password);

            //Aggiorna classe di sessione
            session_info.setUser(user);
            session_info.setPersistency_policy(persistency_policy);
            session_info.setBounderyEnum(BounderyEnum.HOMEPAGE);

            //Avvia il dispatcher
            Dispatcher.start();

        } catch (IllegalArgumentException | DAOException e) {

            session_info.setBounderyEnum(BounderyEnum.ERROR);
            session_info.setLastError(e.getMessage());
            Dispatcher.start();

            session_info.setBounderyEnum(BounderyEnum.AUTHENTICATE);
            Dispatcher.start();
        }

    }
}
