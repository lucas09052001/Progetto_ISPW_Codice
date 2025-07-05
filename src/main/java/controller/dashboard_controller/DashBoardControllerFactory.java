package controller.dashboard_controller;

import exceptions.FactoryException;
import utilities.SessionInfo;
import main.Observer;


public class DashBoardControllerFactory {

    SessionInfo sessionInfo;

    public DashBoardControllerFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public DashBoardController generate(Observer observer) throws FactoryException {

        DashBoardController returnee;

        System.out.println("[DASHBOARD-CONTROLLER-FACTORY] Generating");
        switch (sessionInfo.getVersion()){
            case V1 -> returnee = new DashBoardControllerV1(observer);
            case null, default -> throw new FactoryException("[DASHBOARD-CONTROLLER-FACTORY] Reached unreachable code");
        }

        System.out.println("    [DASHBOARD-CONTROLLER-FACTORY] Returning");
        return returnee;

    }

}
