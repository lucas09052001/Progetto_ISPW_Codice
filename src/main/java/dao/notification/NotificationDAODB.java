package dao.notification;

import dao.ConnectionFactory;
import entity.notification.CustomNotification;
import entity.notification.CustomNotificationDTO;
import exceptions.CriticalException;
import exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;

public class NotificationDAODB implements NotificationDAO{

    String username;
    ArrayList<CustomNotification> notificationsList = new ArrayList<>();
    final String fetchAllNotificationQuery = "SELECT id, senderUsername, message, seen FROM CustomNotification WHERE receiverUsername = ?";

    public NotificationDAODB(String username){
        this.username = username;
    }

    @Override
    public ArrayList<CustomNotification> fetchAllUserNotification(String username) throws DAOException {

        try (Connection connection = ConnectionFactory.upgrade();
             PreparedStatement stmt = connection.prepareStatement(fetchAllNotificationQuery)){

            System.out.println("[DAO] Connection to DB established");

            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();

            System.out.println("[DAO] Query executed");
            System.out.println("[DAO] Handling query results");

            while (rs.next()){

                int id = rs.getInt("id");
                String senderUsername = rs.getString("senderUsername");
                String message = rs.getString("message");
                boolean seen = rs.getBoolean("seen");

                notificationsList.add(new CustomNotification(id, senderUsername, message, seen));
            }
            System.out.println("[DAO] Done.");

            return notificationsList;

        } catch (SQLException e) {
            System.out.println("[EE] SQL error " + e.getMessage()); //CONSOLE DEBUG
            throw new CriticalException(e.getMessage());
        }
    }
}
