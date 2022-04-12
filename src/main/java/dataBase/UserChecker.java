package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserChecker {

    public static boolean checkUser(String login,String password){

        try {
            ResultSet resultLogin = QueryExecutor.executeSelect("SELECT \"login\" FROM \"loginData\" WHERE \"login\"='"+login+"'");
            resultLogin.next();
            ResultSet resultPassword = QueryExecutor.executeSelect("SELECT \"password\"FROM\"loginData\"WHERE\"password\"='"+password+"'");
            resultPassword.next();
            if(resultLogin.getString(1).equals(login)&&resultPassword.getString(1).equals(password)){
                return true;
            }
            else
                System.out.println("COS TU SIE ZJEBAO");
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }
}
