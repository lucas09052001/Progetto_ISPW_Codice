package dao.user_dao;

import entity.user.User;
import exceptions.DAOException;

public interface UserDAO {
    User fetchUserInfo(String username) throws DAOException;
    void updateUser(User updatee) throws DAOException;
}
