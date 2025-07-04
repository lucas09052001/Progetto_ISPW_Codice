package controller.dashboard_controller;

import entity.SessionInfo;
import main.Observer;

import javax.swing.*;

public class DashBoardControllerFactory {

    SessionInfo sessionInfo;

    public DashBoardControllerFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public DashBoardController generate(Observer observer){

        DashBoardController returnee;

        System.out.println("    [DASHBOARD-CONTROLLER-FACTORY] Generating");
        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new DashBoardControllerV1(observer);
            }

            case null, default -> {
                System.out.println("    [DASHBOARD-CONTROLLER-FACTORY] Reached unreachable code");
                throw new RuntimeException();
            }
        }

        System.out.println("    [DASHBOARD-CONTROLLER-FACTORY] Returning");
        return returnee;

    }

}
