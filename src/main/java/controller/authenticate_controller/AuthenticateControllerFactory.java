package controller.authenticate_controller;

import exceptions.FactoryException;
import utilities.SessionInfo;
import main.Observer;

public class AuthenticateControllerFactory {

    SessionInfo sessionInfo;

    public AuthenticateControllerFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public AuthenticateController generate(Observer observer) throws FactoryException {

        AuthenticateController returnee;

        System.out.println("[AUTHENTICATE-CONTROLLER-FACTORY] Generating");
        switch (sessionInfo.getVersion()){
            case V1 -> returnee = new AuthenticateControllerV1(observer);
            case null, default -> throw new FactoryException("AuthenticateControllerFactory reached unreachable code");
        }

        return returnee;
    }

}
