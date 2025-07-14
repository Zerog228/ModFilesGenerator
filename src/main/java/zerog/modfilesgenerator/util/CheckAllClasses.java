package zerog.modfilesgenerator.util;

import javafx.util.Pair;
import zerog.modfilesgenerator.Controller;

import java.io.*;

public class CheckAllClasses {
    public Pair<Boolean, String> check (String registerEntitiesClass, String ... classPath){
        boolean regE = false, initE = false, b = true;
        String breaker = "Unknown";

        File regEntitiesFile = new File(registerEntitiesClass);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new ReverseLineInputStream(regEntitiesFile)))){
            String str;
            while((str = reader.readLine()) != null){
                if(str.contains("//For replace by auto-generation")){
                    regE = true;
                }
                if(str.contains("//Initialize new entity")){
                    initE = true;
                }
            }
            if(!regE || !initE){
                breaker = registerEntitiesClass;
                return new Pair<>(false, breaker);
            }
        } catch (IOException e) {
            Controller.sendToConsole("Something went wrong on file reading! "+registerEntitiesClass, e);
        }

        next: for(String path : classPath){
            File file = new File(path);
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(new ReverseLineInputStream(file)))){
                String str;
                while((str = reader.readLine()) != null){
                    if(str.contains("//For replace by auto-generation")){
                        continue next;
                    }
                }
                b = false;
                breaker = path;
                break;
            } catch (IOException e) {
                Controller.sendToConsole("Something went wrong on file reading! "+path, e);
            }
        }
        return new Pair<>(b, breaker);
    }
}
