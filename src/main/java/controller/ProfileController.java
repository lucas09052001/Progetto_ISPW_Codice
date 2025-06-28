package controller;

import boundery.BounderyEnum;
import dao.user.*;
import entity.SessionInfo;
import entity.user.User;
import entity.user.UserDTO;
import exceptions.CriticalException;
import exceptions.DAOException;
import main.AppController;

public class ProfileController {

    User user;
    UserDAO userDAO;
    UserDTO userDTO;
    SessionInfo sessionInfo;

    public ProfileController(){
        sessionInfo = SessionInfo.getSessionInfo();
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
            sessionInfo.setNextBoundery(BounderyEnum.ERROR);

            AppController.errorEncounterd();

            sessionInfo.setNextBoundery(BounderyEnum.AUTHENTICATE);

            return null;
        }

    }

}
