package zerog.modfilesgenerator.generators;

import zerog.modfilesgenerator.Controller;
import zerog.modfilesgenerator.annotations.VersionIndependent;
import zerog.modfilesgenerator.enums.WorkMode;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import static zerog.modfilesgenerator.Controller.helpersPath;
import static zerog.modfilesgenerator.util.CopyFiles.copyFileUsingChannel;

public class TranslationGenerator {

    @VersionIndependent
    public TranslationGenerator(String langName, String bi_name, String bi_translation, String pathToTranslation, String pathToResourceFolder, String modId, WorkMode mode, boolean createNewLangFile, GeckoGenerator geckoGenerator) throws IOException {
        if(createNewLangFile && mode == WorkMode.NEW_TRANSLATION){
            //TODO add translation generation
            Controller.sendToConsole("Created new translation file!");
        }else {
            File translationsHelper = new File(helpersPath + "\\translationHelper.json");
            PrintWriter pw = new PrintWriter(new FileWriter(translationsHelper, StandardCharsets.UTF_8));
            FileReader fr = new FileReader(pathToTranslation, StandardCharsets.UTF_8);
            Scanner scan = new Scanner(fr);

            String str = scan.nextLine();
            pw.println(str);
            str = scan.nextLine();

            int i = 0;

            while (!str.contains("}")) {
                pw.println(str+(str.contains(",") ? "" : ","));
                str = scan.nextLine();
                i++;
            }
            switch (mode) {
                case ONE_SIDE_BLOCK, PILLAR, ALL_SIDES, BLOCK_WGM, GECKO_BLOCK -> {
                    pw.println("  \"block." + modId + "." + bi_name + "\": \"" + bi_translation + "\"");
                }
                case ITEM, ITEM_WGM, GECKO_ITEM -> {
                    pw.println("  \"item." + modId + "." + bi_name + "\": \"" + bi_translation + "\"");
                }
                case GECKO_ENTITY -> {
                    pw.println("  \"item." + modId + "." + bi_name + "_spawn_egg\": \"" + bi_translation + "\",");
                    pw.println("  \"entity." + modId + "." + bi_name + "\": \"" + bi_translation + "\"");
                }
            }
            pw.print(str);

            fr.close();
            pw.close();

            copyFileUsingChannel(translationsHelper, new File(pathToTranslation));

            Controller.sendToConsole("Added "+bi_name+" to translation file!");
        }
    }
}
