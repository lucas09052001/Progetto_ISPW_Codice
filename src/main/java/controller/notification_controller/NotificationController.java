package controller.notification_controller;

import entity.notification.NotificationDTO;

import java.util.ArrayList;

public interface NotificationController {
    void fetchAll();
    NotificationDTO handNext();
}
