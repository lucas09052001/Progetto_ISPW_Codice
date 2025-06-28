package dao.user;

import entity.user.User;
import exceptions.DAOException;
import repository.UserRepository;

import java.util.List;

public class UserDAONoPersistance implements UserDAO {

    public UserDAONoPersistance(){
        //No set up needed
    }

    @Override
    public User fetchUserInfo(String username) throws DAOException {

        List<User> listaUtenti = UserRepository.getUserRepository().getListaUtenti();
        System.out.println("[SYSTEM] Users have been fetched from volatile-memory repository");

        for(User u : listaUtenti){
            if(u.getUsername().equals(username)){
                System.out.println("[SYSTEM] A user with matching credentials was found");
                return u;
            }
        }

        System.out.println("[SYSTEM] No user with matching credentials was found");
        throw new DAOException("No matching credentials");
    }

}
