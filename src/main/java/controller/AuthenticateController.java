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

    public void login(String username, String password, PersistencyPolicy persistencyPolicy){

        SessionInfo sessionInfo = SessionInfo.getSessionInfo();

        try {
            switch (persistencyPolicy) {
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
                    throw new IllegalArgumentException("No persistency policy was selected");   //No persistencyPolicy was selected
            }

            User user = dao.authenticate(username, password);

            //Aggiorna classe di sessione
            sessionInfo.setUser(user);
            sessionInfo.setPersistencyPolicy(persistencyPolicy);
            sessionInfo.setBounderyEnum(BounderyEnum.HOMEPAGE);

            //Avvia il dispatcher
            Dispatcher.start();

        } catch (IllegalArgumentException | DAOException e) {

            sessionInfo.setBounderyEnum(BounderyEnum.ERROR);
            sessionInfo.setLastError(e.getMessage());
            Dispatcher.start();

            sessionInfo.setBounderyEnum(BounderyEnum.AUTHENTICATE);
            Dispatcher.start();
        }

    }
}
