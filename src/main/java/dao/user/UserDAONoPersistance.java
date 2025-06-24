package dao.user;

import entity.User;
import exceptions.DAOException;
import repository.UserRepository;

import java.util.List;

public class UserDAONoPersistance implements UserDAO {

    public UserDAONoPersistance(){
        //No set up needed
    }

    @Override
    public User authenticate(String username, String password) throws DAOException {

        List<User> lista_utenti = UserRepository.get_user_repository().getListaUtenti();
        System.out.println("[SYSTEM] Users have been fetched from volatile-memory repository");

        for(User u : lista_utenti){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                System.out.println("[SYSTEM] A user with matching credentials was found");
                return u;
            }
        }

        System.out.println("[SYSTEM] No user with matching credentials was found");
        throw new DAOException("No matching credentials");

    }
}
