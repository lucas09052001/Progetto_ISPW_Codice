package dao.notification_dao;

import dao.ConnectionFactory;
import entity.notification.Notification;
import exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;

public class NotificationDAODB implements NotificationDAO{

    private ArrayList<Notification> notificationsList = new ArrayList<>();
    static final String FETCH_ALL_NOTIFICATION_QUERY = "SELECT senderUsername, message, seen FROM Notification WHERE receiverUsername = ?";

    public NotificationDAODB(){
        //No set up needed
    }

    @Override
    public ArrayList<Notification> fetchAllUserNotification(String username) throws DAOException {

        try (Connection connection = ConnectionFactory.upgrade();
             PreparedStatement stmt = connection.prepareStatement(FETCH_ALL_NOTIFICATION_QUERY)){

            System.out.println("[NOTIFICATION-DAO] Connection to DB established");

            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){

                String senderUsername = rs.getString("senderUsername");
                String message = rs.getString("message");
                boolean seen = rs.getBoolean("seen");

                notificationsList.add(new Notification(senderUsername, message, seen));
            }
            System.out.println("[DAO] Done.");

            return notificationsList;

        } catch (SQLException e) {
            System.out.println("[EE] SQL error " + e.getMessage()); //CONSOLE DEBUG
            throw new DAOException(e.getMessage());
        }
    }
}
