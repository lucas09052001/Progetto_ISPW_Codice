package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Connection connection;

    private ConnectionFactory()  {}

    static {
        try (InputStream input = new FileInputStream("resources/db.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty("LOGIN_USER");
            String pass = properties.getProperty("LOGIN_PASS");

            connection = DriverManager.getConnection(connectionUrl, user, pass);
        } catch (IOException | SQLException e) {
            //These exceptions get triggered only if connection to db gets rejected or resources file is
            //unreachable which is a failure externally dictated and as such it is not handled directly
            e.printStackTrace();
            throw new RuntimeException("Critical system error");
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Connection upgrade() {

        try (InputStream input = new FileInputStream("resources/db.properties")) {
            connection.close();

            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty("ACTIVE_USER");
            String pass = properties.getProperty("ACTIVE_PASS");

            return DriverManager.getConnection(connectionUrl, user, pass);
        } catch (IOException | SQLException e) {
            //These excepetions get triggered only if connection to db gets rejected or resources file is
            //unreachable which is a failure externally dictated and as such it is not handled directly
            throw new RuntimeException();
        }
    }
}