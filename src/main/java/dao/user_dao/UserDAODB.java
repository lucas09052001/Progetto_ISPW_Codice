package dao.user_dao;

import dao.ConnectionFactory;
import entity.user.User;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAODB implements UserDAO {

    static final String FETCH_USER_INFO_QUERY = "SELECT username, password, rating, points FROM Users WHERE username = ?";
    static final String UPDATE_USER_QUERY = "UPDATE Users SET points = ? WHERE username = ?";

    public UserDAODB(){
        //No set up needed
    }

    @Override
    public User fetchUserInfo(String username) throws DAOException {
        System.out.println("[USER-DAO] Fetching user info");

        User returnee;

        try (Connection connection = ConnectionFactory.upgrade();
             PreparedStatement stmt = connection.prepareStatement(FETCH_USER_INFO_QUERY)){

            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                returnee = new User();

                returnee.setUsername(username);
                returnee.setPassword(rs.getString("password"));
                returnee.setRating(rs.getInt("rating"));
                returnee.setPoints(rs.getInt("points"));

            }else{
                System.out.println("[USER-DAO] No matching user found");
                returnee = null;
            }

            return returnee;

        } catch (SQLException e) {
            System.out.println("[USER-DAO][EE] Error: " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void updateUser(User user) throws DAOException {
        System.out.println("[USER-DAO] Updating User info");

        try (Connection connection = ConnectionFactory.upgrade();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_USER_QUERY)){


            stmt.setInt(1,user.getPoints());
            stmt.setString(2, user.getUsername());

            if(stmt.executeUpdate() == 0){
                throw new DAOException("No update on DB");
            }

        } catch (SQLException e) {
            System.out.println("[USER-DAO][EE] SQL error " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }
}
