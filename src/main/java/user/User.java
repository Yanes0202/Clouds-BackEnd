package user;

public class User {
    private String login;
    private String password;
    private String userName;
    private String userLastName;

    public User(String login,String password,String userName,String userLastName){
        this.login=login;
        this.password=password;
        this.userName=userName;
        this.userLastName=userLastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }


}
