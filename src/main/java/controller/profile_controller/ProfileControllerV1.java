package controller.profile_controller;

import boundery.Boundaries;
import dao.user_dao.*;
import entity.SessionInfo;
import entity.user.User;
import entity.user.UserDTO;
import exceptions.CriticalException;
import exceptions.DAOException;
import main.Observer;

public class ProfileControllerV1 implements ProfileController {

    UserDAO userDAO;
    Observer observer;
    SessionInfo sessionInfo;

    public ProfileControllerV1(Observer observer, UserDAO dao){
        this.observer = observer;
        this.userDAO = dao;
        sessionInfo = SessionInfo.getSessionInfo();
    }

    public UserDTO getUserInfo(){

        System.out.println("        [CONTROLLER] Starting 'getUserInfo'");
        try {

            System.out.println("        [CONTROLLER] Asking DAO to fetch UserInfo");
            User user = userDAO.fetchUserInfo(sessionInfo.getUsername());
            return new UserDTO(user);

        } catch (DAOException e) {
            //This is unreachable code. This exception gets triggered only when fetching from persistency via username returns no matches
            //as the user is already authenticated when this class is running this exception will never be thrown.

            sessionInfo.setLastError(e.getMessage());
            observer.updateNewBoundery(Boundaries.ERROR);
            return null;
        }

    }

}
