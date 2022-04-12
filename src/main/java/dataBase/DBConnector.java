package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    protected static String URL = "jdbc:postgresql://localhost/Fb";
    protected static String USER = "postgres";
    protected static String PASSWORD = "12345";



    public static Connection Connect(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
