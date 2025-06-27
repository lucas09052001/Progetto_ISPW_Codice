package dao.user;

import entity.User;
import exceptions.DAOException;

public interface UserDAO {
    public User fetchUserInfo(String username) throws DAOException;
}
