package dao.notification_dao;

import entity.notification.Notification;
import exceptions.DAOException;

import java.util.ArrayList;

public interface NotificationDAO {
    public ArrayList<Notification> fetchAllUserNotification(String username) throws DAOException;
}
