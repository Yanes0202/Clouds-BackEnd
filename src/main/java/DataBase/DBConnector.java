package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static String URL = "jdbc:postgresql://localhost/Fb";
    private static String USER = "postgres";
    private static String PASSWORD = "12345";



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
