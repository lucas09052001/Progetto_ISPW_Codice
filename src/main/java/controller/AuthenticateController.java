package controller;

import boundery.BounderyEnum;
import main.AppController;
import dao.user_dao.UserDAO;
import dao.user_dao.UserDAODB;
import dao.user_dao.UserDAOFile;
import dao.user_dao.UserDAONoPersistance;
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
            if(user == null){
                throw new IllegalStateException("No matching credentials");
            }else if(!password.equals(user.getPassword())){
                throw new IllegalStateException("No matching credentials");
            }else{
                //Aggiorna classe di sessione
                sessionInfo.setUsername(username);
                sessionInfo.setPersistencyPolicy(persistencyPolicy);
                sessionInfo.setNextBoundery(BounderyEnum.HOMEPAGE);
            }

        } catch (IllegalStateException | IllegalArgumentException | DAOException e) {

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
