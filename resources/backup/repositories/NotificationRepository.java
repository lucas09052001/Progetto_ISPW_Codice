package repository;

import entity.notification.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationRepository {

    private static NotificationRepository instance;
    private List<Notification> notificationList;

    private NotificationRepository() {
        notificationList = new ArrayList<>();

        // Notifiche di esempio
        notificationList.add(new Notification("alice", "bob", "I accepted your loan request!", false));
        notificationList.add(new Notification("alice", "carlos", "I declined your loan request.", false));
        notificationList.add(new Notification("bob", "alice", "I returned the object I borrowed!", false));
        notificationList.add(new Notification("bob", "carlos", "I returned the object I borrowed!", false));
        notificationList.add(new Notification("system", "alice", "New discounts have been added!", false));
        notificationList.add(new Notification("system", "bob", "Loan an item !", false));
    }

    public static NotificationRepository getNotificationRepository() {
        if (instance == null) {
            instance = new NotificationRepository();
        }
        return instance;
    }

    public List<Notification> getCustomNotificationList() {
        return notificationList;
    }

    public void setCustomNotificationList(List<Notification> listaNotifiche) {
        this.notificationList = listaNotifiche;
    }
}

