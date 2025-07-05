package controller.notification_controller;

import boundery.Boundaries;
import dao.notification_dao.NotificationDAO;
import utilities.SessionInfo;
import entity.notification.Notification;
import entity.notification.NotificationDTO;
import exceptions.DAOException;
import main.Observer;

import java.util.ArrayList;

public class NotificationControllerV1 implements NotificationController {

    SessionInfo sessionInfo;
    ArrayList<Notification> notificationList;
    NotificationDAO notificationDAO;
    int lastHandedNotification;
    Observer observer;

    public NotificationControllerV1(Observer observer, NotificationDAO notificationDAO) {
        sessionInfo = SessionInfo.getSessionInfo();
        this.observer = observer;
        this.notificationDAO = notificationDAO;
        lastHandedNotification = 0;
    }

    public void fetchAll(){

        try {
            System.out.println("[CONTROLLER] NotificationController asking dao layer to fetch notifications... ");
            notificationList = notificationDAO.fetchAllUserNotification(sessionInfo.getUsername());
            System.out.println("[CONTROLLER] Done.");

        } catch (DAOException e) {
            sessionInfo.setLastError(e.getMessage());
            observer.updateNewBoundery(Boundaries.ERROR);

        }

    }

    public NotificationDTO handNext(){
        NotificationDTO notificationDTO;

        if(lastHandedNotification != notificationList.size()){
            notificationDTO = new NotificationDTO(notificationList.get(lastHandedNotification));
            lastHandedNotification++;
        }else{
            notificationDTO = null;
        }

        return notificationDTO;
    }

}
