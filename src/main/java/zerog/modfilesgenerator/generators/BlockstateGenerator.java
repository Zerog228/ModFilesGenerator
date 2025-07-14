package zerog.modfilesgenerator.generators;

import zerog.modfilesgenerator.Controller;
import zerog.modfilesgenerator.annotations.VersionIndependent;
import zerog.modfilesgenerator.enums.ProgramVersion;
import zerog.modfilesgenerator.enums.WorkMode;
import zerog.modfilesgenerator.util.BINameUtils;

import java.io.*;

@VersionIndependent
public class BlockstateGenerator {

    public BlockstateGenerator(String bi_name, File jsonFile, String pathToBlockstate, String modId, WorkMode mode, boolean rotatable, String pathToBlockFolder, ProgramVersion version) throws IOException {
        File blockstate = new File(pathToBlockstate+ "\\" + bi_name + ".json");
        FileWriter fw = new FileWriter(blockstate);
        PrintWriter pw = new PrintWriter(fw);

        if(!rotatable){
            pw.println("{");
            pw.println("  \"variants\": {");
            pw.println("    \"\": { \"model\": \"" +modId+ ":block/" + bi_name + "\" }");
            pw.println("  }");
            pw.println("}");
        }else {
            pw.println("{");
            pw.println("  \"variants\": {");
            pw.println("    \"facing=north\": { \"model\": \"" + modId + ":block/" + bi_name + "\", \"uvlock\": true },");
            pw.println("    \"facing=east\":  { \"model\": \"" + modId + ":block/" + bi_name + "\", \"y\":  90, \"uvlock\": false },");
            pw.println("    \"facing=south\": { \"model\": \"" + modId + ":block/" + bi_name + "\", \"y\": 180, \"uvlock\": false },");
            pw.println("    \"facing=west\":  { \"model\": \"" + modId + ":block/" + bi_name + "\", \"y\": 270, \"uvlock\": false }");
            pw.println("  }");
            pw.println("}");

            String packagePath = BINameUtils.getPackagePath(pathToBlockFolder);
            String properName = BINameUtils.getProperName(bi_name);

            try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(pathToBlockFolder+"\\"+(BINameUtils.getProperName(bi_name))+".java")));
            BufferedReader reader = version == ProgramVersion.V1_19_2_GECKO_3 ? new BufferedReader(new InputStreamReader(BlockstateGenerator.class.getResourceAsStream("/blockFiles/HorizontalFacingBlock.txt"))) :
                    new BufferedReader(new InputStreamReader(BlockstateGenerator.class.getResourceAsStream("/blockFiles/HorizontalFacingBlockNew.txt")))){
                String str;
                while((str = reader.readLine()) != null){
                   if(str.contains("PATH_TO_PACKAGE")){
                       str = str.replace("PATH_TO_PACKAGE", packagePath);
                   }
                   if(str.contains("BI_NAME")){
                        str = str.replace("BI_NAME", properName);
                    }
                    writer.println(str);
                }
            }catch (Exception e){
                Controller.sendToConsole("Something went wrong on creating HorizontalFacingBlock");
                e.printStackTrace();
            }
        }
        pw.close();
        Controller.sendToConsole("Created new blockstate for "+bi_name);
    }
}
