package utils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {


    public static String  getHtml(String name){
        String file="";
        try {
            file = Files.readString(Paths.get(System.getProperty("user.dir")+"/src/main/resources/templates/"+name+".html"));

        }catch (IOException e){
            e.printStackTrace();
        }
        return file;
    }
}
