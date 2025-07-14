package zerog.modfilesgenerator.util;

import zerog.modfilesgenerator.Controller;

import java.io.*;
import java.nio.channels.FileChannel;


public class CopyFiles {
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    public static void copyFileUsingChannel(File source, File dest){
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel(); FileChannel destChannel = new FileOutputStream(dest).getChannel()) {
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }
        catch (Exception ignored){
            Controller.sendToConsole("Failed to copy files!");
        }
    }

    public static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }
}
