package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserChecker {

    public static boolean checkIfLoginDataMatch(String login, String password){
        try {
            ResultSet resultLogin = QueryExecutor.executeSelect("SELECT \"login\" FROM \"loginData\" WHERE \"login\"='"+login+"'");
            resultLogin.next();
            ResultSet resultPassword = QueryExecutor.executeSelect("SELECT \"password\"FROM\"loginData\"WHERE\"login\"='"+login+"'");
            resultPassword.next();
            if(resultLogin.getString(1).equals(login)&&resultPassword.getString(1).equals(password)){
                return true;
            }
            else
                System.out.println("COS TU SIE ZJEBAO");
                return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean checkIfLoginExist(String login){
        try {
            ResultSet result = QueryExecutor.executeSelect("SELECT \"login\" from \"loginData\" WHERE \"login\" = '"+login+"'");
        if(result.next()){
                return false;
            }else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public static List<String> getUserData(String login){
        try {
        ResultSet result = QueryExecutor.executeSelect("SELECT * from \"loginData\" WHERE \"login\" = '"+login+"'");
        result.next();
        List<String> userData = new ArrayList<>();
        userData.add(result.getString(1));
        userData.add(result.getString(2));
        userData.add(result.getString(3));
        userData.add(result.getString(4));
        userData.add(result.getString(5));
        userData.add(result.getString(6));
        userData.add(result.getString(7));
        return userData;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
