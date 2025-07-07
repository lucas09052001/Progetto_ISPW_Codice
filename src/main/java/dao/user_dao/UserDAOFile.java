package dao.user_dao;

import entity.user.User;
import exceptions.DAOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.PathUtility;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UserDAOFile implements UserDAO {

    String username;
    ObjectMapper mapper;
    File file;

    public UserDAOFile(){
        this.mapper = new ObjectMapper();
        this.file = new File(PathUtility.getPathToUserJson());
    }

    public UserDAOFile(String username) {
        this.username = username;
        this.mapper = new ObjectMapper();
        this.file = new File(PathUtility.getPathToUserJson());
    }

    @Override
    public User fetchUserInfo(String username) throws DAOException {

        System.out.println("[USER-DAO] Fetching User info");

        try {

            List<User> users = mapper.readValue(file, new TypeReference<>() {});

            for (User u : users) {
                if (u.getUsername().equals(username)) {
                    return u;
                }
            }

            System.out.println("[USER-DAO] No matching user found.");
            return null;

        } catch (IOException e) {
            System.out.println("[USER-DAO][EE] Error: " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void updateUser(User updatee) throws DAOException {
        System.out.println("[USER-DAO] Updating User info");

        try {
            boolean matchFound = false;

            List<User> users = mapper.readValue(file, new TypeReference<>() {});

            for (User u : users) {
                if (Objects.equals(u.getUsername(), updatee.getUsername())) {
                    //Assuming username and password won't change

                    matchFound = true;
                    u.setRating(updatee.getRating());
                    u.setPoints(updatee.getPoints());
                    break;
                }
            }

            if(!matchFound){
                System.out.println("[USER-DAO][EE] No match was found");
                throw new IllegalStateException("Error: No match in user json");
            }

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);

        } catch (IllegalStateException | IOException e) {
            throw new DAOException(e.getMessage());
        }
    }
}
