package zerog.modfilesgenerator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zerog.modfilesgenerator.util.Config;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 650);
        //Another one JSON generator
        stage.setTitle("Json generator! "+ Config.version);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Controller.saveConfigs();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}