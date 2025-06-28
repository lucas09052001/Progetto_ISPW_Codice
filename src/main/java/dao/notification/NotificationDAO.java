package dao.notification;

import entity.notification.CustomNotification;
import exceptions.DAOException;

import java.util.ArrayList;
import java.util.List;

public interface NotificationDAO {
    public ArrayList<CustomNotification> fetchAllUserNotification(String username) throws DAOException;
}
