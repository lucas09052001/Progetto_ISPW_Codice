package controller.authenticate_controller;

import dao.user_dao.UserDAOFactory;
import entity.SessionInfo;
import main.Observer;

public class AuthenticateControllerFactory {

    SessionInfo sessionInfo;

    public AuthenticateControllerFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public AuthenticateController generate(Observer observer){

        AuthenticateController returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new AuthenticateControllerV1(observer);
            }

            case null, default -> { //Unreachable Code
                throw new RuntimeException("AuthenticateControllerFactory reached unreachable code");
            }
        }

        return returnee;
    }

}
