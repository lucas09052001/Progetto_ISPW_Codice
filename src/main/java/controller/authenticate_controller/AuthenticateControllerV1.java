package controller.authenticate_controller;

import boundery.Boundaries;
import dao.user_dao.*;
import entity.PersistencyPolicy;
import entity.SessionInfo;
import entity.user.User;
import exceptions.DAOException;
import exceptions.NoMatchException;
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

        System.out.println("    [CONTROLLER] Starting 'login'");
        try{

            sessionInfo.setPersistencyPolicy(persistencyPolicy);
            System.out.println("    [CONTROLLER] Asking DAO-FACTORY for dao");
            UserDAO dao = daoFactory.generate();

            System.out.println("    [CONTROLLER] Asking DAO to fetch user info");
            User user = dao.fetchUserInfo(username);

            if(user == null){
                sessionInfo.setPersistencyPolicy(PersistencyPolicy.NULL);
                throw new NoMatchException("No matching credentials");
            }else if(!password.equals(user.getPassword())){
                sessionInfo.setPersistencyPolicy(PersistencyPolicy.NULL);
                throw new NoMatchException("No matching credentials");
            }else{
                sessionInfo.setUsername(username);
                sessionInfo.setPersistencyPolicy(persistencyPolicy);
            }

            observer.updateNewBoundery(Boundaries.HOMEPAGE);

        } catch (NoMatchException | DAOException e) {
            observer.errorOccurred(e.getMessage());

        }
    }
}
