package controller.notification_controller;

import dao.notification_dao.NotificationDAO;
import dao.notification_dao.NotificationDAOFactory;
import exceptions.FactoryException;
import utilities.SessionInfo;
import main.Observer;

public class NotificationControllerFactory {
    SessionInfo sessionInfo;
    NotificationDAOFactory daoFactory;

    public NotificationControllerFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
        daoFactory = new NotificationDAOFactory();
    }

    public NotificationController generate(Observer observer) throws FactoryException {

        NotificationController returnee;
        NotificationDAO dao = daoFactory.generate();

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new NotificationControllerV1(observer, dao);
            }
            case null, default -> {
                throw new FactoryException("[NOTIFICATION-CONTROLLER-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }
}
