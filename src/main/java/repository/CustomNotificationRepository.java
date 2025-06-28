package repository;

import entity.notification.CustomNotification;

import java.util.ArrayList;
import java.util.List;

public class CustomNotificationRepository {

    private static CustomNotificationRepository instance;
    private List<CustomNotification> listaNotifiche;

    private CustomNotificationRepository() {
        listaNotifiche = new ArrayList<>();

        // Notifiche di esempio
        listaNotifiche.add(new CustomNotification(1, "alice", "bob", "TETTE", false));
        listaNotifiche.add(new CustomNotification(1, "alice", "bob", "CULO", false));
        listaNotifiche.add(new CustomNotification(1, "alice", "bob", "FIGA", false));
        listaNotifiche.add(new CustomNotification(2, "bob", "alice", "CAZZO", false));
    }

    public static CustomNotificationRepository getNotificationRepository() {
        if (instance == null) {
            instance = new CustomNotificationRepository();
        }
        return instance;
    }

    public List<CustomNotification> getCustomNotificationList() {
        return listaNotifiche;
    }

    public void setCustomNotificationList(List<CustomNotification> listaNotifiche) {
        this.listaNotifiche = listaNotifiche;
    }
}

