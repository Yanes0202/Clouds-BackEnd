package main;

import dataBase.QueryExecutor;
import dataBase.UserChecker;
import net.minidev.json.JSONObject;
import post.Post;
import user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class Endpoints {
    /** Tworzenie posta */
    @PostMapping(path = "/api/post/create",consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<User> createPost(@RequestBody Post post){
        ArrayList<String> postList= new ArrayList<>();
        postList.add(post.getTitle());
        postList.add(post.getImage());
        postList.add(post.getDescription());
        ArrayList<String> rows = new ArrayList<>();
        rows.add("title");
        rows.add("image");
        rows.add("description");
        QueryExecutor.insertInto("postsData",rows,postList);

        return  new ResponseEntity("{\"status\":\"Success\"}", HttpStatus.OK);
    }

    /** Usunięcie postów */
    @DeleteMapping(path = "/api/post/drop")
    public ResponseEntity<String> postDrop(){
        QueryExecutor.executeQuery("DELETE FROM \"postsData\"");
        return new ResponseEntity<>("{\"status\":\"Success\"}",HttpStatus.OK);
    }

    /** Lista wszystkich postów */
    @GetMapping(path = "/api/post/get")
    public ResponseEntity<List<Post>> postGet(){
        List<Post> postList = new ArrayList<>();
        ResultSet result = QueryExecutor.executeSelect("SELECT * from \"postsData\"");
        try {
            while(result.next()){
                postList.add(new Post(Integer.parseInt(result.getString(1)), result.getString(2), result.getString(3), result.getString(4)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(postList,HttpStatus.OK);
    }

    /** Logowanie */
    @PostMapping(path = "/api/login",consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                MediaType.APPLICATION_XML_VALUE,
                MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<User> logowanie(@RequestBody User user){
        JSONObject json = new JSONObject();
        json.put("status","Success");
        json.put("token","Token12345");
        if(UserChecker.checkIfLoginDataMatch(user.getLogin(),user.getPassword())){
            return new ResponseEntity(json.toJSONString(), HttpStatus.OK);
        }else {
            return new ResponseEntity("{\"status\":\"Fail\"}",HttpStatus.NOT_FOUND);
        }
    }

    /** Rejestracja użytkownika */
    @PostMapping(path = "/api/register",consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<User> register(@RequestBody User user){
        if(user.getLogin().isEmpty()||user.getPassword().isEmpty()||user.getUserName().isEmpty()||user.getUserLastName().isEmpty()){
            return new ResponseEntity("{\"status\":\"Fail\"}",HttpStatus.EXPECTATION_FAILED);
        }else {
            if(UserChecker.checkIfLoginExist(user.getLogin())){
                ArrayList<String> userList= new ArrayList<>();
                userList.add(user.getLogin());
                userList.add(user.getPassword());
                userList.add(user.getUserName());
                userList.add(user.getUserLastName());
                ArrayList<String> rows = new ArrayList<>();
                rows.add("login");
                rows.add("password");
                rows.add("userName");
                rows.add("userLastName");
                QueryExecutor.insertInto("loginData",rows,userList);
                return new ResponseEntity("{\"status\":\"Success\"}", HttpStatus.OK);
            }else {
                return new ResponseEntity("{\"status\":\"Nickname Exist\"}",HttpStatus.EXPECTATION_FAILED);
            }
        }
    }

    /** Lista danych użytkownika */
    @GetMapping(value =  "/api/user/{login}")
    public ResponseEntity<User> getUser(@PathVariable String login){
        List<String> userData = UserChecker.getUserData(login);
        JSONObject json = new JSONObject();
        json.put("userLastName",userData.get(1));
        json.put("userName",userData.get(0));
        return new ResponseEntity(json,HttpStatus.OK);
    }

    /** Lista wszystkich użytkowników */
    @GetMapping(path = "/api/user/get")
    public ResponseEntity<List<User>> getAll(){

        List<User> userList = new ArrayList<>();
        ResultSet result = QueryExecutor.executeSelect("SELECT * from \"loginData\"");
        try {
            while(result.next()){
                userList.add(new User(result.getString(2),result.getString(3),result.getString(4),result.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    /** Usunięcie wszystkich użytkowników */
    @DeleteMapping(path = "/api/user/drop")
    public ResponseEntity<String> drop(){
        QueryExecutor.executeQuery("DELETE FROM \"loginData\"");
        return new ResponseEntity<>("{\"status\":\"Success\"}",HttpStatus.OK);
    }}
