package zerog.modfilesgenerator.generators;

import zerog.modfilesgenerator.Controller;
import zerog.modfilesgenerator.annotations.VersionIndependent;
import zerog.modfilesgenerator.enums.WorkMode;

import java.io.*;

import static zerog.modfilesgenerator.util.CopyFiles.copyFileUsingChannel;

public class ItemGenerator {

    @VersionIndependent
    public ItemGenerator(String bi_name, String pathToItemModel, String pathToItemTexture, String texture, String modId, WorkMode mode, File jsonFile) throws IOException {
        if(mode == WorkMode.ITEM){
            String itemTextureFolderName = pathToItemTexture.replace("\\", "/").split("textures/")[1];
            File item_file = new File(pathToItemModel + "\\" + bi_name + ".json");
            FileWriter fw = new FileWriter(item_file);
            PrintWriter pw = new PrintWriter(fw);

            pw.println("{");
            pw.println("  \"parent\": \"item/generated\",");
            pw.println("  \"textures\": {");
            pw.println("    \"layer0\": \""+modId+":"+itemTextureFolderName+"/" + bi_name + "\"");
            pw.println("  }");
            pw.println("}");
            pw.close();
            Controller.sendToConsole("Created new item! "+bi_name);
        }else{
            File dest = new File(pathToItemModel+"\\"+bi_name+".json");
            copyFileUsingChannel(jsonFile, dest);
        }
        File source = new File(texture);
        File dest = new File(pathToItemTexture+"\\"+bi_name+".png");
        copyFileUsingChannel(source, dest);
    }
}
