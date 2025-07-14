package zerog.modfilesgenerator.util;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.*;
import java.util.List;
import java.util.Map;

import static zerog.modfilesgenerator.Controller.helpersPath;
import static zerog.modfilesgenerator.Controller.sendToConsole;
import static zerog.modfilesgenerator.util.CopyFiles.copyFileUsingChannel;

public class JavaFileUtils {

    public static void rewriteJavaFile(@Nullable String packageName, @Nullable List<String> importNames, Map<String, List<String>> replacementMap, @Nullable File helper, @NonNull InputStream source, @NonNull File target, String failMessage) throws IOException {
        boolean delHelper = false;
        if(helper == null){
            helper = new File(helpersPath+"\\helper-"+target.getName());
        }
        String importName = null;

        if(importNames != null && importNames.size() == 1){
            importName = importNames.get(0);
        }

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(source));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new PrintWriter(helper)))){
            int i = 0;
            String current_string;

            if(packageName != null){
                writer.println("package " + packageName + ";");
                writer.println("");
            }

            while((current_string = reader.readLine()) != null){
                i++;
                if(i == 3){
                    if(importName != null){
                        writer.println("import " + importName + ";");
                    }else if(importNames != null){
                        for(String impName : importNames){
                            writer.println("import "+impName + ";");
                        }
                    }
                }
                for(String key : replacementMap.keySet()){
                    if (current_string.contains(key)) {
                        if (key.equals("//For replace by auto-generation") || key.equals("//Initialize new entity")) {
                            for (String replacement : replacementMap.get(key)) {
                                writer.println(replacement);
                            }
                            break;
                        }

                        if (replacementMap.get(key).size() > 1) {
                            current_string = "";
                            for (String replacement : replacementMap.get(key)) {
                                writer.println(replacement);
                            }
                        } else {
                            current_string = current_string.replace(key, replacementMap.get(key).get(0));
                        }
                    }
                }
                writer.println(current_string);
            }
        }catch (Exception e){
            sendToConsole(failMessage, e);
        }
        copyFileUsingChannel(helper, target);
        if(delHelper){
            //tempFile.delete();
        }
    }
}
