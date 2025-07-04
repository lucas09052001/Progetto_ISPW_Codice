package dao.notification_dao;

import entity.notification.Notification;
import exceptions.DAOException;
import repository.CustomNotificationRepository;

import java.util.ArrayList;
import java.util.List;

public class NotificationDAONoPersistance implements NotificationDAO{


    CustomNotificationRepository repository = CustomNotificationRepository.getNotificationRepository();
    ArrayList<Notification> notificationsList = new ArrayList<>();

    public NotificationDAONoPersistance() {
        //No set up needed
    }

    @Override
    public ArrayList<Notification> fetchAllUserNotification(String username) throws DAOException {

        System.out.println("[DAO] Fetching user's notifications from repositories");
        for(Notification n : repository.getCustomNotificationList()){
            if(n.getReceiverUsername().equals(username)){
                notificationsList.add(n);
            }
        }

        return notificationsList;
    }

    public void updateRepository(List<Notification> notificationsList){
        repository.setCustomNotificationList(notificationsList);
    }
}
