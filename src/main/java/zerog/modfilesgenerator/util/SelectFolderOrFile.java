package zerog.modfilesgenerator.util;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import zerog.modfilesgenerator.Controller;

import javax.swing.*;

import java.io.IOException;

import static zerog.modfilesgenerator.util.Config.saveInConfig;

public class SelectFolderOrFile extends Thread{
    private Thread thread;
    private MouseEvent e;
    private String lastOpenedPath;

    public SelectFolderOrFile(String threadName, MouseEvent e, String lastOpenedPath){
        this.thread = new Thread(threadName);
        this.e = e;
        this.lastOpenedPath = lastOpenedPath;
    }

    @Override
    public void run() {
        JFileChooser fileChooser;
        if(e.getSource() instanceof TextField field){
            fileChooser = new JFileChooser(field.getText().length() > 0 ? field.getText() : lastOpenedPath);
            fileChooser.setFileSelectionMode(field.getId().contains("Folder") ? JFileChooser.DIRECTORIES_ONLY : JFileChooser.FILES_ONLY);
            int response = fileChooser.showDialog(null, field.getPromptText());
            if(response == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                field.setText(filePath);
                try {
                    Controller.lastOpenedPath = filePath;
                    saveInConfig("lastOpenedPath", filePath);

                    saveInConfig(field.getId(), filePath);
                } catch (IOException ex) {
                    Controller.sendToConsole("Failed to save path!", ex);
                }
            }
        }
    }
}
