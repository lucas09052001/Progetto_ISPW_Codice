package controller.authenticate_controller;

import boundery.Boundaries;
import dao.user_dao.*;
import utilities.PersistencyPolicy;
import utilities.SessionInfo;
import entity.user.User;
import exceptions.DAOException;
import main.Observer;

public class AuthenticateControllerV1 implements AuthenticateController{

    UserDAOFactory daoFactory;
    Observer observer;
    SessionInfo sessionInfo;

    public AuthenticateControllerV1(Observer observer) {
        this.observer = observer;
        sessionInfo = SessionInfo.getSessionInfo();
        daoFactory = new UserDAOFactory();
    }

    @Override
    public void login(String username, String password, PersistencyPolicy persistencyPolicy){

        System.out.println("[AUTHENTICATE-CONTROLLER] Starting 'login'");
        try{

            if(persistencyPolicy == PersistencyPolicy.NULL){    //User hasn't chosen a persistency policy -> Exception is thrown and log in UC restarted.
                throw new IllegalArgumentException("Choose persistency policy");
            }else{
                sessionInfo.setPersistencyPolicy(persistencyPolicy);
            }

            //Gather User data from selected persistency
            UserDAO dao = daoFactory.generate();
            User user = dao.fetchUserInfo(username);

            if((user == null)  || (!password.equals(user.getPassword()))){
                //No matching username found or password doesn't match
                observer.errorOccurred("No matching credentials");
            }else{
                //Update SessionInfo class
                sessionInfo.setUsername(username);
                sessionInfo.setPersistencyPolicy(persistencyPolicy);

                observer.updateNewBoundary(Boundaries.HOMEPAGE);
            }

        } catch ( IllegalArgumentException | DAOException e) {
            observer.errorOccurred(e.getMessage());
        }
    }
}
