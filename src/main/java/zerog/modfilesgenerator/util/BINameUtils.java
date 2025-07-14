package zerog.modfilesgenerator.util;

import org.apache.commons.text.WordUtils;

public class BINameUtils {

    public static String getProperName(String bi_name){return WordUtils.capitalizeFully(bi_name, '_').replace("_", "");}

    public static String getPackagePath(String path){
        return path.replace("\\", ".").split("java\\.")[1];
    }
}
