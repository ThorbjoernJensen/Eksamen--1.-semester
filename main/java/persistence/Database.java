package persistence;

import exceptionHandling.ExceptionHandling;
import exceptionHandling.SUPER;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection connection;
    private final String USER;
    private final String PASSWORD;
    private final String URL;

    public Database(String user, String password, String url) throws ExceptionHandling {
        USER = user;
        PASSWORD = password;
        URL = url;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO: Make own throwable exception and let it bubble upwards
            throw new ExceptionHandling("Fucking fejl i databsen driveren din ko");
        }
    }

    public Connection connect() throws IOException, SUPER {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            ExceptionHandling.logfile(e);
            throw new SUPER("Fucking fejl i databsen din ko");
        }
        return connection;
    }

}
