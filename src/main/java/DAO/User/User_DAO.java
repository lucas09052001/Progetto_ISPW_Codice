package DAO.User;

import Entity.User;

public interface User_DAO {
    public User authenticate(String username, String password);
}
