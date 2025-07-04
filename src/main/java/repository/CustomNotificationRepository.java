package repository;

import entity.notification.Notification;

import java.util.ArrayList;
import java.util.List;

public class CustomNotificationRepository {

    private static CustomNotificationRepository instance;
    private List<Notification> listaNotifiche;

    private CustomNotificationRepository() {
        listaNotifiche = new ArrayList<>();

        // Notifiche di esempio
        listaNotifiche.add(new Notification(1, "alice", "bob", "TETTE", false));
        listaNotifiche.add(new Notification(1, "alice", "bob", "CULO", false));
        listaNotifiche.add(new Notification(1, "alice", "bob", "FIGA", false));
        listaNotifiche.add(new Notification(2, "bob", "alice", "CAZZO", false));
    }

    public static CustomNotificationRepository getNotificationRepository() {
        if (instance == null) {
            instance = new CustomNotificationRepository();
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

