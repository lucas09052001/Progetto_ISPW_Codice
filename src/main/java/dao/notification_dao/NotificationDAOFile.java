package dao.notification_dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.notification.CustomNotification;
import exceptions.CriticalException;
import exceptions.DAOException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAOFile implements NotificationDAO {

    String username;
    ArrayList<CustomNotification> notificationList = new ArrayList<>();

    public NotificationDAOFile(String username){
        this.username = username;
    }

    @Override
    public ArrayList<CustomNotification> fetchAllUserNotification(String username) throws DAOException {
        //JSON STUFF
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<CustomNotification> buffer = mapper.readValue(new File("resources/Json/customNotifications.json"), new TypeReference<List<CustomNotification>>() {});
            System.out.println("[DAO] All notifications have been fetched from Json file: customNotification.Json");

            System.out.println("[DAO] Getting current user's notification");

            for (CustomNotification n : buffer){
                if(n.getReceiverUsername().equals(username)){
                    notificationList.add(n);
                }
            }

            System.out.println("[DAO] Done.");

            return notificationList;

        } catch (IOException e) {
            System.out.println("[EE] Qualcosa è andato storto nel fetch dei dati dal Json");
            System.out.println(e.getMessage());

            throw new CriticalException("Errore di IO nel file Json");
        }
    }
}
