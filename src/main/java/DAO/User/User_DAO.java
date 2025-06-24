package DAO.User;

import Entity.User;
import Exceptions.DAO_Exception;

public interface User_DAO {
    public User authenticate(String username, String password) throws DAO_Exception;
}
