package dao;

import exceptions.CriticalException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Connection connection;

    private ConnectionFactory() {}

    //Viene eseguito ogni volta che è chiamato un metodo statico o è acceduta una variabile statica.
    static {
        try (InputStream input = new FileInputStream("resources/db.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty("LOGIN_USER");
            String pass = properties.getProperty("LOGIN_PASS");

            connection = DriverManager.getConnection(connectionUrl, user, pass);
        } catch (IOException | SQLException e) {
            //Qui ci sta bene tirare una eccezione personalizzata così che questa possa venir propagata
            //fino al controller e lì venir gestita.
            e.printStackTrace();
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

            System.out.println("[SYSTEM] Errore di sistema: " + e.getMessage());
            throw new CriticalException("Errore critico di sistema. L'applicazione verrà immediatamente terminata");
        }
    }
}