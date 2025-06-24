package dao.user;

import entity.User;
import exceptions.DAOException;

public interface UserDAO {
    public User authenticate(String username, String password) throws DAOException;
}
