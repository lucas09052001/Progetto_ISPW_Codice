package controller.notification_controller;

import entity.notification.NotificationDTO;


public interface NotificationController {
    void fetchAll();
    NotificationDTO handNext();
}
