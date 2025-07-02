package dao.user_dao;

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
    String username;
    final String fetchUserInfoQuery = "SELECT username, password, rating, points FROM Users WHERE username = ?";
    final String updateUserQuery = "UPDATE Users SET points = ? WHERE username = ?";

    public UserDAODB(){
        //No set up needed
    }

    public UserDAODB(String username) {
        this.username = username;
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

    @Override
    public void updateUser(User user) throws DAOException {
        System.out.println("        [DAO] Starting updateUser");

        try (Connection connection = ConnectionFactory.upgrade();
             PreparedStatement stmt = connection.prepareStatement(updateUserQuery)){

            System.out.println("        [DAO] Connection to DB established");

            stmt.setInt(1,user.getPoints());
            stmt.setString(2, user.getUsername());

            if(stmt.executeUpdate() == 0){
                throw new DAOException("No update on DB");
            }

        } catch (SQLException e) {
            System.out.println("[EE] SQL error " + e.getMessage()); //CONSOLE DEBUG
            throw new CriticalException(e.getMessage());
        }
    }
}
