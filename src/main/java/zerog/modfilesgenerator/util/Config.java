package zerog.modfilesgenerator.util;

import org.apache.commons.io.FileUtils;
import zerog.modfilesgenerator.ConsoleNotification;
import zerog.modfilesgenerator.Controller;
import zerog.modfilesgenerator.enums.WorkMode;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

import static zerog.modfilesgenerator.Controller.sendToConsole;

public class Config{
    public static final String version = "1.0.0";

    private static final Path currentWorkingDir = Controller.currentWorkingDir;
    private static final File appConfig = new File(currentWorkingDir.toAbsolutePath()+"\\config");

    public static void checkTranslations() {
        boolean folder = new File(currentWorkingDir.toAbsolutePath() + "\\languages").mkdir();

        try{
            URI uri = Controller.class.getResource("/languages/").toURI();
            Path myPath;
            if (uri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                myPath = fileSystem.getPath("/languages/");
            } else {
                myPath = Paths.get(uri);
            }
            Stream<Path> walk = Files.walk(myPath, 1);
            for (Iterator<Path> it = walk.iterator(); it.hasNext(); ) {
                Path pathToTranslation = it.next();
                String filename = String.valueOf(pathToTranslation);
                if(filename.replace("\\", "/").split("/languages").length > 1){
                    filename = filename.replace("\\", "/").split("/languages")[1].replace("/", "");
                    File translationFile = new File(currentWorkingDir.toAbsolutePath() + "\\languages\\"+filename);
                    if(!translationFile.exists()){
                        try{
                            FileUtils.copyURLToFile(pathToTranslation.toUri().toURL(), translationFile);
                            sendToConsole(ConsoleNotification.CREATED_NEW_LANG_FILE.completeNotification(filename));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            sendToConsole("Failed to load translations!");
        }
    }

    public static void saveInConfig(String key, String value) throws IOException {
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(currentWorkingDir.toAbsolutePath()+"\\config", StandardCharsets.UTF_8));
        }catch (Exception e){
            FileWriter fw = new FileWriter(appConfig, StandardCharsets.UTF_8);
            fw.close();
            System.out.println("Created new config");
            sendToConsole("Created new config");
        }
        Properties prop = new Properties();
        prop.load(reader);
        try (Writer inputStream = new FileWriter(appConfig, StandardCharsets.UTF_8)) {
            // Setting the properties.
            prop.setProperty(key, value);
            // Storing the properties in the file with a heading comment.
            prop.store(inputStream, "!!!INFORMATION!!!");

            sendToConsole(ConsoleNotification.SAVED_TO_CONFIG.completeNotification(key));
        } catch (IOException ex) {
            ex.printStackTrace();
            sendToConsole("Failed to save "+key+" to config!");
        }
    }

    public static String getOrCreateDefault(String key, String def) {
        try{
            File cfg = new File(currentWorkingDir.toAbsolutePath() + "\\config");
            if (!cfg.exists()) {
                CopyFiles.copyInputStreamToFile(Config.class.getClassLoader().getResourceAsStream("config"), cfg);
            }
            BufferedReader reader = new BufferedReader(new FileReader(cfg, StandardCharsets.UTF_8));
            Properties prop = new Properties();
            prop.load(reader);
            reader.close();
            if (prop.get(key).toString() == null) {
                saveInConfig(key, def);
                return def;
            } else {
                return prop.get(key).toString();
            }
        }catch (Exception e){
            Controller.sendToConsole("Exception on config loading!", e);
            return def;
        }
    }

    public static HashMap<String, String> getWorkmodeTranslationMap(String lang) {
        HashMap<String, String> translationMap = new HashMap<>();
        try(InputStreamReader propsInput = new InputStreamReader(new FileInputStream(currentWorkingDir+"\\languages\\"+lang), StandardCharsets.UTF_8)) {
            Properties prop = new Properties();
            prop.load(propsInput);

            for(WorkMode mode : WorkMode.values()){
                translationMap.put(mode.name(), prop.get(mode.name()).toString());
            }

            //TODO Path tab name, tutorials and youtubers tabs translations, tooltip for rotatable and rotatable tag (button) itself
        }catch (Exception e){
            System.out.println("Failed to create translation map!");
            e.printStackTrace();
            System.out.println("=============================================");
            sendToConsole("Failed to create translation map!");
        }
        return translationMap;
    }

    public static String getTranslation(String key, String lang){
        try(InputStreamReader propsInput = new InputStreamReader(new FileInputStream(currentWorkingDir+"\\languages\\"+lang), StandardCharsets.UTF_8)) {
            Properties prop = new Properties();
            prop.load(propsInput);
            return (String) prop.get(key);
        }catch (Exception ignored){}
        return null;
        //TODO Initialize propInput once and then call it several times
    }

    public static HashMap<String, String> getReverseWorkmodeTranslationMap(HashMap<String, String> translationMap){
        HashMap<String,String> rev = new HashMap<>();
        for(Map.Entry<String, String> entry : translationMap.entrySet())
            rev.put(entry.getValue(), entry.getKey());
        return rev;
    }

    public static List<File> searchLanguages() throws NullPointerException{
        File[] translations = new File(currentWorkingDir.toAbsolutePath()+"/languages/").listFiles();
        return Arrays.asList(translations);
    }


}
