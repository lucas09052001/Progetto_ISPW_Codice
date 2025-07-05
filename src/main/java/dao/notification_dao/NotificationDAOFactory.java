package dao.notification_dao;

import exceptions.FactoryException;
import utilities.SessionInfo;

public class NotificationDAOFactory {

    SessionInfo sessionInfo;

    public NotificationDAOFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public NotificationDAO generate() throws FactoryException {

        NotificationDAO returnee;

        switch (sessionInfo.getPersistencyPolicy()){
            case DB -> returnee = new NotificationDAODB();
            case FILE -> returnee = new NotificationDAOFile();
            case NO_PERSISTANCE -> returnee = new NotificationDAONoPersistance();
            case null, default -> throw new FactoryException("[NOTIFICATION-DAO-FACTORY] Reached unreachable code");
        }
        return returnee;
    }
}
