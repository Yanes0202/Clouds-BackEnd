package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		/*String login = "adrian";
		try{
			//ResultSet result = QuerryExecutor.executeSelect("SELECT \"login\" FROM \"loginData\" WHERE \"login\"='"+login+"'");
			//ResultSet resultSet = QuerryExecutor.executeSelect("SELECT * FROM \"loginData\"");
			//result.next();
			//System.out.println(result.getString(1));
			//ResultSet result = QuerryExecutor.executeSelect("SELECT \"UserName\" FROM public.users WHERE \"UserName\"='Adrian'");
			//result.next();
			//String username = result.getString("UserName");
			//QuerryExecutor.executeQuerry("INSERT INTO public.users (\"UserId\",\"UserName\") VALUES (0,'Adrian')");
			//QuerryExecutor.executeQuerry("DELETE FROM public.users WHERE \"UserName\"='A'");

			//System.out.println("Nazwa użytkownika "+username);
		}catch (SQLException e){
			e.printStackTrace();
		}*/
	}

}
