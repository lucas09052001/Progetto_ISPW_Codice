package controller.homepage_controller;

import entity.SessionInfo;
import main.Observer;

public class HomePageControllerFactory {
    SessionInfo sessionInfo;

    public HomePageControllerFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public HomePageController generate(Observer observer){

        HomePageController returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new HomePageControllerV1(observer);
            }
            case null, default -> {
                System.out.println("        [CONTROLLER][CE] Reached unreachable code");
                throw new RuntimeException("[CONTROLLER][CE] Reached unreachable code");
            }
        }

        return returnee;

    }
}
