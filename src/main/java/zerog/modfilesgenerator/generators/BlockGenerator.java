package zerog.modfilesgenerator.generators;

import zerog.modfilesgenerator.Controller;
import zerog.modfilesgenerator.annotations.VersionIndependent;
import zerog.modfilesgenerator.enums.WorkMode;

import java.io.*;
import java.util.List;

import static zerog.modfilesgenerator.util.CopyFiles.copyFileUsingChannel;

@VersionIndependent
public class BlockGenerator{

    public BlockGenerator(String bi_name, File jsonFile, String pathToBlock,
                          String pathToItem, String modId, WorkMode mode, List<String> imagePath, String blocksTextureFolder) throws IOException {
        File block_file = new File(pathToBlock + "\\" + bi_name + ".json");
        FileWriter fw = new FileWriter(block_file);
        PrintWriter pw = new PrintWriter(fw);

        File item_file = new File(pathToItem + "\\" + bi_name + ".json");
        FileWriter fw2 = new FileWriter(item_file);
        PrintWriter pw2 = new PrintWriter(fw2);

        String textureFolderName = blocksTextureFolder.replace("\\", "/").split("textures/")[1];
        String blockModelFolderName = pathToBlock.replace("\\", "/").split("models/")[1];

        switch (mode) {
            case ONE_SIDE_BLOCK -> {
                pw.println("{");
                pw.println("  \"parent\": \"block/cube_all\",");
                pw.println("  \"textures\": {");
                pw.println("    \"all\": \""+modId+":"+textureFolderName+"/" + bi_name + "\"");
                pw.println("  }");
                pw.println("}");

                String file1 = imagePath.get(0);
                File source = new File(file1);
                File dest = new File(blocksTextureFolder+"\\"+bi_name+".png");
                copyFileUsingChannel(source, dest);
            }
            case PILLAR -> {
                pw.println("{");
                pw.println("  \"parent\": \"minecraft:block/cube_column\",");
                pw.println("  \"textures\": {");
                pw.println("    \"end\": \""+modId+":"+textureFolderName+"/" + bi_name + "1\",");
                pw.println("    \"side\": \""+modId+":"+textureFolderName+"/" + bi_name + "2\"");
                pw.println("  }");
                pw.println("}");

                String file1 = imagePath.get(0);
                String file2 = imagePath.get(1);
                File source = new File(file1);
                File dest = new File(blocksTextureFolder+"\\"+bi_name+"1.png");
                copyFileUsingChannel(source, dest);
                File source2 = new File(file2);
                File dest2 = new File(blocksTextureFolder+"\\"+bi_name+"2.png");
                copyFileUsingChannel(source2, dest2);
            }
            case ALL_SIDES -> {
                pw.println("{");
                pw.println("\"parent\": \"block/cube\",");
                pw.println("  \"textures\": {");
                pw.println("    \"up\": \""+modId+":"+textureFolderName+"/"+bi_name+"/"+bi_name+"1\",");
                pw.println("    \"north\": \""+modId+":"+textureFolderName+"/"+bi_name+"/"+bi_name+"2\",");
                pw.println("    \"west\": \""+modId+":"+textureFolderName+"/"+bi_name+"/"+bi_name+"3\",");
                pw.println("    \"south\": \""+modId+":"+textureFolderName+"/"+bi_name+"/"+bi_name+"4\",");
                pw.println("    \"east\": \""+modId+":"+textureFolderName+"/"+bi_name+"/"+bi_name+"5\",");
                pw.println("    \"down\": \""+modId+":"+textureFolderName+"/"+bi_name+"/"+bi_name+"6\",");
                pw.println("    \"particle\": \""+modId+":"+textureFolderName+"/"+bi_name+"/"+bi_name+"1\"");
                pw.println("  }");
                pw.println("}");

                File f1 = new File(blocksTextureFolder+"\\"+bi_name);
                f1.mkdir();
                int i = 0;
                for(String str : imagePath){
                    i++;
                    File source = new File(str);
                    File dest = new File(blocksTextureFolder+"\\"+bi_name+"\\"+bi_name+i+".png");
                    copyFileUsingChannel(source, dest);
                }
            }
            case BLOCK_WGM -> {
                File dest = new File(pathToBlock+"\\"+bi_name+".json");
                copyFileUsingChannel(jsonFile, dest);

                String file1 = imagePath.get(0);
                File source = new File(file1);
                File dest2 = new File(blocksTextureFolder+"\\"+bi_name+".png");
                copyFileUsingChannel(source, dest2);
            }
        }

        Controller.sendToConsole("Created new block! "+bi_name);

        //Adding block model to item package
        pw2.println("{");
        pw2.println("  \"parent\": \""+modId+":"+blockModelFolderName+"/" + bi_name + "\"");
        pw2.println("}");

        pw.close();
        pw2.close();

        Controller.sendToConsole("Created new blockItem! "+bi_name);
    }
}
