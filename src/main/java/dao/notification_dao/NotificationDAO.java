package dao.notification_dao;

import entity.notification.CustomNotification;
import exceptions.DAOException;

import java.util.ArrayList;

public interface NotificationDAO {
    public ArrayList<CustomNotification> fetchAllUserNotification(String username) throws DAOException;
}
