package Main;

import DataBase.QuerryExecutor;
import DataBase.UserChecker;
import User.User;
import Utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class Endpoints {




    @GetMapping("/login")
    public String login(){
        return Utils.getHtml("login");
    }
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return Utils.getHtml("index");
    }

    @GetMapping("/dbError")
    public String dbError(){
        return "Błąd z Bazą danych";
    }

    @PostMapping("/index")
    public RedirectView indexx(@RequestParam(value = "login")String login, @RequestParam(value = "password")String password){


        try {
            ResultSet loginDB = QuerryExecutor.executeSelect("SELECT \"login\" FROM \"loginData\" WHERE \"login\"='"+login+"'");
            loginDB.next();
            ResultSet passwordDB = QuerryExecutor.executeSelect("SELECT \"password\" FROM \"loginData\" WHERE \"password\"='"+password+"'");
            passwordDB.next();
            if(login.equals(loginDB.getString(1))&&password.equals(passwordDB.getString(1))){
                return new RedirectView("/");
            }else
                return new RedirectView("/badlogin");
        } catch (SQLException e) {
            e.printStackTrace();
            return new RedirectView("/badlogin");
        }


    }

    @PostMapping(path = "/api/login",consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                MediaType.APPLICATION_XML_VALUE,
                MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<User> logowanie(@RequestBody User user){
        if(UserChecker.checkUser(user.getLogin(),user.getPassword())){
            return new ResponseEntity("Success", HttpStatus.OK);
        }else {
            return new ResponseEntity("Fail",HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping(path = "/api/register",consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<User> register(@RequestBody User user){
        QuerryExecutor.executeQuerry("INSERT INTO \"loginData\"(\"login\",\"password\",\"userName\",\"userLastName\") VALUES ('"+user.getLogin()+"','"+user.getPassword()+"','"+user.getName()+"','"+user.getLastName()+"')");
        return new ResponseEntity("Success",HttpStatus.OK);
    }

    @GetMapping(path = "/api/drop")
    public ResponseEntity drop(){
        QuerryExecutor.executeQuerry("DELETE FROM \"loginData\"");
        return new ResponseEntity ("Success",HttpStatus.OK);
    }
    /*
        List<String > repo = new ArrayList<>();

    @PostMapping("/add")
    public String put(@RequestParam(value = "name")String name){
        repo.add(name);
        return "Dodano imie "+name;
    }
    @GetMapping("/getAll")
    public String getAll(){
        String wynik = "";
        for(String elem : repo){
            if(wynik==""){
                wynik = elem;
            }else
            wynik += ", " + elem ;
        }
        return wynik;
    }
    @GetMapping("/get")
    public String get(@RequestParam(value = "id")int id){
        return repo.get(id);
    }*/
}
