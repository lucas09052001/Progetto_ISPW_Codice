package dao.user;

import entity.user.User;
import exceptions.CriticalException;
import exceptions.DAOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserDAOFile implements UserDAO {

    public UserDAOFile(){
        //No set up needed
    }

    @Override
    public User fetchUserInfo(String username) throws DAOException {
        //JSON STUFF
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<User> utenti = mapper.readValue(new File("resources/Json/users.json"), new TypeReference<List<User>>() {});
            System.out.println("[SYSTEM] Users have been fetched from Json file: users.Json");

            for (User u : utenti) {
                if (u.getUsername().equals(username)) {
                    System.out.println("[SYSTEM] A user with matching username was found");
                    return u;
                }
            }

            System.out.println("[SYSTEM] No user with matching username was found");
            throw new DAOException("No matching credentials");

        } catch (IOException e) {
            System.out.println("[EE] Qualcosa è andato storto nel fetch dei dati dal Json");
            System.out.println(e.getMessage());

            throw new CriticalException("Errore di IO nel file Json");
        }
    }
}
