package controller.homepage_controller;

import exceptions.FactoryException;
import utilities.SessionInfo;
import main.Observer;

public class HomePageControllerFactory {
    SessionInfo sessionInfo;

    public HomePageControllerFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public HomePageController generate(Observer observer) throws FactoryException {

        HomePageController returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> returnee = new HomePageControllerV1(observer);
            case null, default -> throw new FactoryException("[CONTROLLER][CE] Reached unreachable code");
        }

        return returnee;

    }
}
