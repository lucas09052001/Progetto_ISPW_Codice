package controller;

import boundery.BounderyEnum;
import main.AppController;
import dao.user.UserDAO;
import dao.user.UserDAODB;
import dao.user.UserDAOFile;
import dao.user.UserDAONoPersistance;
import entity.PersistencyPolicy;
import entity.SessionInfo;
import entity.user.User;
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

            User user = dao.fetchUserInfo(username);

            if(!password.equals(user.getPassword())){
                throw new DAOException("No matching credentials");
            }

            //Aggiorna classe di sessione
            sessionInfo.setUsername(username);
            sessionInfo.setPersistencyPolicy(persistencyPolicy);
            sessionInfo.setNextBoundery(BounderyEnum.HOMEPAGE);

        } catch (IllegalArgumentException | DAOException e) {

            sessionInfo.setLastError(e.getMessage());
            sessionInfo.setNextBoundery(BounderyEnum.ERROR);

            AppController.errorEncounterd();

            sessionInfo.setNextBoundery(BounderyEnum.AUTHENTICATE);

        } finally {
            //Notifica l'appController che lo UseCase è terminato
            AppController.useCaseCompletion();
        }

    }
}
