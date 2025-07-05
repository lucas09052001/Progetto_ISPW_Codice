package dao.user_dao;

import entity.user.User;
import exceptions.DAOException;
import repository.UserRepository;
import java.util.List;
import java.util.Objects;

public class UserDAONoPersistance implements UserDAO {

    UserRepository repository;

    public UserDAONoPersistance(){
        repository = UserRepository.getUserRepository();
    }

    @Override
    public User fetchUserInfo(String username) throws DAOException {

        List<User> users = repository.getListaUtenti();
        System.out.println("            [DAO] Users have been fetched from volatile-memory repository");

        for(User u : users){
            if(u.getUsername().equals(username)){
                System.out.println("            [DAO] A user with matching credentials was found");
                return u;
            }
        }

        System.out.println("            [DAO] No user with matching credentials was found");
        return null;
    }

    @Override
    public void updateUser(User updatee) throws DAOException {
        System.out.println("    [DAO] Starting updateUser");

        try {
            boolean matchFound = false;

            System.out.println("        [DAO] Buffering Json");
            List<User> users = repository.getListaUtenti();

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
                throw new IllegalStateException("Error: No match in user repository");
            }

            System.out.println("        [DAO] Updating repository");
            repository.setListaUtenti(users);
            System.out.println("        [DAO] Returning");

        } catch (IllegalStateException e) {
            throw new DAOException(e.getMessage());
        }

    }
}
