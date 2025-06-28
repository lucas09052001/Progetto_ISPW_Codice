package dao.notification;

import entity.notification.CustomNotification;
import entity.notification.CustomNotificationDTO;
import exceptions.DAOException;
import repository.CustomNotificationRepository;

import java.util.ArrayList;
import java.util.List;

public class NotificationDAONoPersistance implements NotificationDAO{


    String username;
    CustomNotificationRepository repository = CustomNotificationRepository.getNotificationRepository();
    ArrayList<CustomNotification> notificationsList = new ArrayList<>();

    public NotificationDAONoPersistance(String username) {
        this.username = username;
    }

    @Override
    public ArrayList<CustomNotification> fetchAllUserNotification(String username) throws DAOException {

        System.out.println("[DAO] Fetching user's notifications from repositories");
        for(CustomNotification n : repository.getCustomNotificationList()){
            if(n.getReceiverUsername().equals(username)){
                notificationsList.add(n);
            }
        }

        return notificationsList;
    }

    public void updateRepository(List<CustomNotification> notificationsList){
        repository.setCustomNotificationList(notificationsList);
    }
}
