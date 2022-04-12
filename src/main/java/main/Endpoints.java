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
    @PostMapping(path = "/api/createPost",consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<User> createPost(@RequestBody Post post){
        List<String > postList= new ArrayList();
        postList.add(post.getTitle());
        postList.add(post.getImage());
        postList.add(post.getDescription());
        List<String> rows = new ArrayList<>();
        rows.add("title");
        rows.add("image");
        rows.add("description");
        QueryExecutor.insertInto("postsData",rows,postList);

        return  new ResponseEntity("{\"status\":\"Success\"}", HttpStatus.OK);
    }

    /** Usunięcie postów */
    @DeleteMapping(path = "/api/post/drop")
    public ResponseEntity postDrop(){
        QueryExecutor.executeQuery("DELETE FROM \"postsData\"");
        return new ResponseEntity ("{\"status\":\"Success\"}",HttpStatus.OK);
    }

    /** Lista wszystkich postów */
    @GetMapping(path = "/api/post/get")
    public ResponseEntity postGet(){
        List<Post> postList = new ArrayList<>();
        Post post = new Post();
        ResultSet result = QueryExecutor.executeSelect("SELECT * from \"postsData\"");
        try {
            while(result.next()){
                post.setId(Integer.parseInt(result.getString(1)));
                post.setTitle(result.getString(2));
                post.setImage(result.getString(3));
                post.setDescription(result.getString(4));
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(postList,HttpStatus.OK);
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
        if(UserChecker.checkUser(user.getLogin(),user.getPassword())){
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
            QueryExecutor.executeQuery("INSERT INTO \"loginData\"(\"login\",\"password\",\"userName\",\"userLastName\") VALUES ('" + user.getLogin() + "','" + user.getPassword() + "','" + user.getUserName() + "','" + user.getUserLastName() + "')");
            return new ResponseEntity("{\"status\":\"Success\"}", HttpStatus.OK);
        }
    }

    /** Lista wszystkich użytkowników */
    @GetMapping(path = "/api/user/get")
    public ResponseEntity getAll(){

        List<User> userList = new ArrayList<>();
        User user = new User();
        ResultSet result = QueryExecutor.executeSelect("SELECT * from \"loginData\"");
        try {
            while(result.next()){
                 user.setLogin(result.getString(2));
                 user.setPassword(result.getString(3));
                 user.setUserName(result.getString(4));
                 user.setUserLastName(result.getString(5));
                 userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(userList,HttpStatus.OK);
    }

    /** Usunięcie wszystkich użytkowników */
    @DeleteMapping(path = "/api/user/drop")
    public ResponseEntity drop(){
        QueryExecutor.executeQuery("DELETE FROM \"loginData\"");
        return new ResponseEntity ("{\"status\":\"Success\"}",HttpStatus.OK);
    }}
