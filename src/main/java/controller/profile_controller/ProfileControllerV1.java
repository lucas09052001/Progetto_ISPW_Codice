package controller.profile_controller;

import dao.user_dao.*;
import utilities.SessionInfo;
import entity.user.User;
import entity.user.UserDTO;
import exceptions.DAOException;
import main.Observer;

//Profile controller doesn't need to update AppController,
// Dashboard is the only clickable element during Profile UC so its controller will be responsible for that
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

        System.out.println("[PROFILE-CONTROLLER] Starting 'getUserInfo'");
        try {
            //Fetching user info from persistency
            User user = userDAO.fetchUserInfo(sessionInfo.getUsername());

            //Returning to Boundary via DTO
            return new UserDTO(user);

        } catch (DAOException e) {
            observer.errorOccurred(e.getMessage());
            return null;
        }

    }

}
