package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuerryExecutor {

    public static ResultSet executeSelect(String selectQuerry){
        try {
            Connection connection = DBConnector.Connect();
            Statement statement = connection.createStatement();
            return statement.executeQuery(selectQuerry);

        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    public static void executeQuerry(String querry){

        try {
            Connection connection = DBConnector.Connect();
            Statement statement = connection.createStatement();
            statement.execute(querry);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
