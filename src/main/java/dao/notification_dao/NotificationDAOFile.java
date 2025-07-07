package dao.notification_dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.notification.Notification;
import exceptions.DAOException;
import utilities.PathUtility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAOFile implements NotificationDAO {

    ArrayList<Notification> notificationList = new ArrayList<>();

    public NotificationDAOFile(){
        //No set up needed
    }

    @Override
    public ArrayList<Notification> fetchAllUserNotification(String username) throws DAOException {
        //JSON STUFF
        ObjectMapper mapper = new ObjectMapper();

        try {
            File file = new File(PathUtility.getPathToNotificationJson());
            List<Notification> buffer = mapper.readValue(file, new TypeReference<>() {});
            System.out.println("[DAO] All notifications have been fetched from Json file: customNotification.Json");

            System.out.println("[DAO] Getting current user's notification");

            for (Notification n : buffer){
                if(n.getReceiverUsername().equals(username)){
                    notificationList.add(n);
                }
            }

            System.out.println("[DAO] Done.");

            return notificationList;

        } catch (IOException e) {
            System.out.println("[EE] Qualcosa è andato storto nel fetch dei dati dal Json");
            System.out.println(e.getMessage());

            throw new DAOException(e.getMessage());
        }
    }
}
