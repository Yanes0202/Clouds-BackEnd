package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class QueryExecutor {

    public static ResultSet executeSelect(String selectQuery){
        try {
            Connection connection = DBConnector.Connect();
            Statement statement = connection.createStatement();
            return statement.executeQuery(selectQuery);

        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    public static void executeQuery(String querry){

        try {
            Connection connection = DBConnector.Connect();
            Statement statement = connection.createStatement();
            statement.execute(querry);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void insertInto(String table, List rows, List values ){
        String queryRows = "(";
        String queryValues = "(";
        for(Object value:rows){
            if(rows.get(rows.size()-1)==value){
                queryRows += "\""+value+"\")";
            }else {
                queryRows += "\""+value+"\", ";
            }
        }
        for(Object vale: values){
            if(values.get(values.size()-1)==vale){
                queryValues += "'"+vale+"')";
            }else {
                queryValues += "'"+vale+"',";
            }
        }
        executeQuery("INSERT INTO \""+table+"\""+queryRows+" VALUES "+queryValues);

    }

}
