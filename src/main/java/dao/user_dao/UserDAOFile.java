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

        System.out.println("            [DAO] Starting fetchUserInfo");

        try {

            System.out.println("            [DAO] Buffering Json");
            List<User> users = mapper.readValue(file, new TypeReference<>() {});

            System.out.println("            [DAO] Extracting data of interest");
            for (User u : users) {
                if (u.getUsername().equals(username)) {
                    System.out.println("            [DAO] Match Found. Returning");
                    return u;
                }
            }

            System.out.println("            [DAO] No match found.");
            return null;

        } catch (IOException e) {
            System.out.println("           [DAO][CE] Critical error: " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void updateUser(User updatee) throws DAOException {
        System.out.println("        [DAO] Starting updateUser");

        try {
            boolean matchFound = false;

            System.out.println("        [DAO] Buffering Json");
            List<User> users = mapper.readValue(file, new TypeReference<>() {});

            System.out.println("        [DAO] Updating data of interest");
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
                System.out.println("        [DAO][CE] No match was found although there has to be");
                throw new IllegalStateException("Error: No match in user json");
            }

            System.out.println("        [DAO] Updating Json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
            System.out.println("        [DAO] Returning");

        } catch (IllegalStateException | IOException e) {
            throw new DAOException(e.getMessage());
        }
    }
}
