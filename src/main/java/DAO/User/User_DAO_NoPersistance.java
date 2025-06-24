package DAO.User;

import Entity.User;
import Exceptions.DAO_Exception;
import Repository.User_Repository;

import java.util.List;

public class User_DAO_NoPersistance implements User_DAO{

    public User_DAO_NoPersistance(){
        //No set up needed
    }

    @Override
    public User authenticate(String username, String password) throws DAO_Exception {

        List<User> lista_utenti = User_Repository.get_user_repository().getLista_utenti();
        System.out.println("[SYSTEM] Users have been fetched from volatile-memory repository");

        for(User u : lista_utenti){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                System.out.println("[SYSTEM] A user with matching credentials was found");
                return u;
            }
        }

        System.out.println("[SYSTEM] No user with matching credentials was found");
        throw new DAO_Exception("No matching credentials");

    }
}
