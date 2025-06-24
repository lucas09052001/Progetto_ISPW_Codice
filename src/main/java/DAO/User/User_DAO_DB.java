package DAO.User;

import DAO.ConnectionFactory;
import Entity.User;
import Exceptions.Critical_Exception;
import Exceptions.DAO_Exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User_DAO_DB implements User_DAO{
    User user = new User();
    Connection connection;

    public User_DAO_DB(){}

    public User authenticate(String username, String password) throws DAO_Exception{
        //JDBC STUFF
        connection = ConnectionFactory.upgrade();

        System.out.println("[SYSTEM] Connection to DB established"); //CONSOLE DEBUG

        try {
            String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
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
                throw new DAO_Exception("No matching credentials");
            }

            return user;

        } catch (SQLException e) {
            System.out.println("[EE] SQL error " + e.getMessage()); //CONSOLE DEBUG
            throw new Critical_Exception(e.getMessage());
        }

    }

}
