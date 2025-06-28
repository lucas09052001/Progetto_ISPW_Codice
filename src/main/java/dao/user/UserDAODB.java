package dao.user;

import dao.ConnectionFactory;
import entity.user.User;
import exceptions.CriticalException;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAODB implements UserDAO {

    User user = new User();
    final String fetchUserInfoQuery = "SELECT username, password, rating, points FROM Users WHERE username = ?";

    public UserDAODB(){
        //No set up needed
    }

    @Override
    public User fetchUserInfo(String username) throws DAOException {

        try (Connection connection = ConnectionFactory.upgrade();
             PreparedStatement stmt = connection.prepareStatement(fetchUserInfoQuery)){

            System.out.println("[SYSTEM] Connection to DB established");

            stmt.setString(1,username);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                System.out.println("[SYSTEM] User was fetched from DB"); //CONSOLE DEBUG

                user.setUsername(username);
                user.setPassword(rs.getString("password"));
                user.setRating(rs.getInt("rating"));
                user.setPoints(rs.getInt("points"));

            }else{
                System.out.println("[EE] User was not found in DB"); //CONSOLE DEBUG
                throw new DAOException("No matching credentials");
            }

            return user;

        } catch (SQLException e) {
            System.out.println("[EE] SQL error " + e.getMessage()); //CONSOLE DEBUG
            throw new CriticalException(e.getMessage());
        }
    }

}
