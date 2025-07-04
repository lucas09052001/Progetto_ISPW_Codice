package controller.profile_controller;

import boundery.Boundaries;
import dao.user_dao.*;
import entity.SessionInfo;
import entity.user.User;
import entity.user.UserDTO;
import exceptions.CriticalException;
import exceptions.DAOException;
import main.Observer;

import java.security.cert.PolicyNode;

public class ProfileControllerV1 implements ProfileController {

    User user;
    UserDAO userDAO;
    UserDTO userDTO;
    SessionInfo sessionInfo;
    Observer observer;

    public ProfileControllerV1(Observer observer){
        sessionInfo = SessionInfo.getSessionInfo();
        this.observer = observer;
        userDTO = new UserDTO();
    }

    public UserDTO fetchUserInfo(){

        switch (sessionInfo.getPersistencyPolicy()){
            case DB -> userDAO = new UserDAODB();
            case FILE -> userDAO = new UserDAOFile();
            case NO_PERSISTANCE -> userDAO = new UserDAONoPersistance();
            case NULL -> throw new CriticalException();
        }

        try {
            user = userDAO.fetchUserInfo(sessionInfo.getUsername());

            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setRating(user.getRating());
            userDTO.setPoints(user.getPoints());

            return userDTO;

        } catch (DAOException e) {

            //This is unreachable code. This exception gets triggered only when fetching from persistency via username returns no matches
            //as the user is already authenticated when this class is running this exception will never be thrown.

            sessionInfo.setLastError(e.getMessage());
            sessionInfo.setNextBoundery(Boundaries.ERROR);

            //AppController.errorEncounterd();

            sessionInfo.setNextBoundery(Boundaries.AUTHENTICATE);

            return null;
        }

    }

}
