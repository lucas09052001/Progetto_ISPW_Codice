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

        System.out.println("[USER-DAO] Fetching User info");

        List<User> users = repository.getListaUtenti();

        for(User u : users){
            if(u.getUsername().equals(username)){
                return u;
            }
        }

        System.out.println("[USER-DAO] No matching user found");
        return null;
    }

    @Override
    public void updateUser(User updatee) throws DAOException {
        System.out.println("[USER-DAO] Updating User info");

        try {
            boolean matchFound = false;

            List<User> users = repository.getListaUtenti();

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
                throw new IllegalStateException("Error: No match in user repository");
            }

            repository.setListaUtenti(users);

        } catch (IllegalStateException e) {
            throw new DAOException(e.getMessage());
        }

    }
}
