package controller.notification_controller;

import dao.notification_dao.NotificationDAO;
import utilities.SessionInfo;
import entity.notification.Notification;
import entity.notification.NotificationDTO;
import exceptions.DAOException;
import main.Observer;

import java.util.ArrayList;


public class NotificationControllerV1 implements NotificationController {

    SessionInfo sessionInfo;    //This controller needs session-data
    ArrayList<Notification> notificationList;   //Buffered notification coming from persistency layer
    NotificationDAO notificationDAO;
    int lastHandedNotification; //Holds information about the last notification shown through boundary in the array above
    Observer observer;  //Reference to AppController via Interface

    public NotificationControllerV1(Observer observer, NotificationDAO notificationDAO) {
        sessionInfo = SessionInfo.getSessionInfo();
        this.observer = observer;
        this.notificationDAO = notificationDAO;
        lastHandedNotification = 0;
        fetchAll();
    }

    public void fetchAll(){ //Fetch from persistency all instances and store them in array

        try {
            System.out.println("[CONTROLLER] NotificationController asking dao layer to fetch notifications... ");
            notificationList = notificationDAO.fetchAllUserNotification(sessionInfo.getUsername());
            System.out.println("[CONTROLLER] Done.");

        } catch (DAOException e) {
            observer.errorOccurred(e.getMessage());
        }

    }

    public NotificationDTO handNext(){  //Hand to boundery next notification on the list (guarded by lastHandedNotification)
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
