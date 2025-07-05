package repository;

import entity.notification.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationRepository {

    private static NotificationRepository instance;
    private List<Notification> listaNotifiche;

    private NotificationRepository() {
        listaNotifiche = new ArrayList<>();

        // Notifiche di esempio
        listaNotifiche.add(new Notification(1, "alice", "bob", "TETTE", false));
        listaNotifiche.add(new Notification(1, "alice", "bob", "CULO", false));
        listaNotifiche.add(new Notification(1, "alice", "bob", "FIGA", false));
        listaNotifiche.add(new Notification(2, "bob", "alice", "CAZZO", false));
    }

    public static NotificationRepository getNotificationRepository() {
        if (instance == null) {
            instance = new NotificationRepository();
        }
        return instance;
    }

    public List<Notification> getCustomNotificationList() {
        return listaNotifiche;
    }

    public void setCustomNotificationList(List<Notification> listaNotifiche) {
        this.listaNotifiche = listaNotifiche;
    }
}

