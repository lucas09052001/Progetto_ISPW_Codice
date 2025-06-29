package controller;

import boundery.BounderyEnum;
import dao.notification_dao.NotificationDAO;
import dao.notification_dao.NotificationDAODB;
import dao.notification_dao.NotificationDAOFile;
import dao.notification_dao.NotificationDAONoPersistance;
import entity.PersistencyPolicy;
import entity.SessionInfo;
import entity.notification.CustomNotification;
import entity.notification.CustomNotificationDTO;
import exceptions.CriticalException;
import exceptions.DAOException;
import main.AppController;

import java.util.ArrayList;

public class NotificationController {

    SessionInfo sessionInfo = SessionInfo.getSessionInfo();
    ArrayList<CustomNotification> notificationList;
    NotificationDAO dao;
    int lastHandedNotification = 0;

    public NotificationController() {
        //No set up needed
    }

    public void fetchAllNotification(){

        String username = sessionInfo.getUsername();
        PersistencyPolicy persistencyPolicy = sessionInfo.getPersistencyPolicy();
        switch (persistencyPolicy){
            case DB -> dao = new NotificationDAODB(username);
            case FILE -> dao = new NotificationDAOFile(username);
            case NO_PERSISTANCE -> dao = new NotificationDAONoPersistance(username);
            case NULL -> throw new CriticalException();
        }

        try {
            System.out.println("[CONTROLLER] NotificationController asking dao layer to fetch notifications... ");
            notificationList = dao.fetchAllUserNotification(username);
            System.out.println("[CONTROLLER] Done.");

        } catch (DAOException e) {
            sessionInfo.setLastError(e.getMessage());
            sessionInfo.setNextBoundery(BounderyEnum.ERROR);

            AppController.errorEncounterd();

            sessionInfo.setNextBoundery(BounderyEnum.AUTHENTICATE);
        }

    }

    public CustomNotificationDTO handNextNotification(){
        CustomNotificationDTO notificationDTO;

        if(!(lastHandedNotification == notificationList.size())){
            notificationDTO = new CustomNotificationDTO(notificationList.get(lastHandedNotification));
            lastHandedNotification++;
        }else{
            notificationDTO = null;
        }

        return notificationDTO;
    }

}
