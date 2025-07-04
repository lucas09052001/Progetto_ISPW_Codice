package controller.notification_controller;

import dao.notification_dao.NotificationDAO;
import dao.notification_dao.NotificationDAOFactory;
import entity.SessionInfo;
import main.Observer;

public class NotificationControllerFactory {
    SessionInfo sessionInfo;
    NotificationDAOFactory daoFactory;

    public NotificationControllerFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
        daoFactory = new NotificationDAOFactory();
    }

    public NotificationController generate(Observer observer){

        NotificationController returnee;
        NotificationDAO dao = daoFactory.generate();

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new NotificationControllerV1(observer, dao);
            }
            case null, default -> {
                System.out.println("    [NOTIFICATION-CONTROLLER-FACTORY] Reached unreachable code");
                throw new RuntimeException("[NOTIFICATION-CONTROLLER-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }
}
