package dao.notification_dao;

import entity.SessionInfo;

public class NotificationDAOFactory {

    SessionInfo sessionInfo;

    public NotificationDAOFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public NotificationDAO generate(){

        NotificationDAO returnee;

        switch (sessionInfo.getPersistencyPolicy()){
            case DB -> {
                returnee = new NotificationDAODB();
            }
            case FILE -> {
                returnee = new NotificationDAOFile();
            }
            case NO_PERSISTANCE -> {
                returnee = new NotificationDAONoPersistance();
            }
            case null, default -> {
                System.out.println("[NOTIFICATION-DAO-FACTORY] Reached unreachable code");
                throw new RuntimeException("[NOTIFICATION-DAO-FACTORY] Reached unreachable code");
            }
        }
        return returnee;
    }
}
