package dao.user;

import dao.ConnectionFactory;
import entity.User;
import exceptions.CriticalException;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAODB implements UserDAO {
    User user = new User();

    public UserDAODB(){
        //No set up needed
    }

    public User authenticate(String username, String password) throws DAOException {
        //JDBC STUFF
        //connection = ConnectionFactory.upgrade();
        System.out.println("[SYSTEM] Connection to DB established"); //CONSOLE DEBUG

        try (Connection connection = ConnectionFactory.upgrade()){
            String query = "SELECT username, password, rating, points FROM Users WHERE username = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                System.out.println("[SYSTEM] User was fetched from DB"); //CONSOLE DEBUG

                user.setUsername(username);
                user.setPassword(password);
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
