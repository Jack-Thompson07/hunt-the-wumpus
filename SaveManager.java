import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveManager {
    

    public SaveManager(){
        String json = "";
        JSONParser p = new JSONParser(); 
        
    
        try{
          Scanner s = new Scanner(new File(file + ".json"));
          while(s.hasNextLine()){
            JSON += s.nextLine();
          }
          s.close();

          JSONArray jsa =  (JSONArray) p.parse(JSON);
          for(Object o:jsa){
            
          }
        }
        catch(Exception e){

        }
    }
}
