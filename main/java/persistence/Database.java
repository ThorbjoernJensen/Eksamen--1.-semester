package persistence;

import exceptionHandling.ExceptionHandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String USER;
    private final String PASSWORD;
    private final String URL;

    public Database(String user, String password, String url) throws Exception {
        USER = user;
        PASSWORD = password;
        URL = url;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionHandling(e);
        }
    }

    public Connection connect() throws Exception {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new ExceptionHandling(e);
        }
        return connection;
    }
}
