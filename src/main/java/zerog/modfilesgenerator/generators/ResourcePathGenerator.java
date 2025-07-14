package zerog.modfilesgenerator.generators;

import zerog.modfilesgenerator.Controller;
import zerog.modfilesgenerator.annotations.VersionIndependent;

import java.io.File;

public class ResourcePathGenerator {

    @VersionIndependent
    public ResourcePathGenerator(String pathToResources, String modId){
        boolean generate_assets = new File(pathToResources+"\\assets").mkdir();
        File generate_modId = new File(pathToResources+"\\assets\\"+modId);
        generate_modId.mkdir();
        String[] needed_folders = {"blockstates", "geo", "animations", "lang", "models", "models\\block", "models\\item", "textures", "textures\\block", "textures\\item", "textures\\entity"};
        for(String path : needed_folders){
            boolean b = new File(generate_modId+"\\"+path).mkdir();
            try {
                Controller.connectPathWithFolder(path, generate_modId.getAbsolutePath() + "\\" + path, b);
                Controller.sendToConsole("Generated path: "+path);
            }catch (Exception e){
                System.out.println("Failed to save new generated path in config! path: "+path);
                Controller.sendToConsole("Failed to generate path: "+path);
            }
        }
    }
}
