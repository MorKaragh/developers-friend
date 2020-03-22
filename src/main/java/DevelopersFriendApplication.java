import config.ApplicationProperties;
import engine.ApplicationEngine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.MainView;
import utils.FxUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DevelopersFriendApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        Scene scene = new Scene(new MainView(new ApplicationEngine()), ApplicationProperties.getDouble("screen.width"), ApplicationProperties.getDouble("screen.height"));
        stage.setScene(scene);
        FxUtils.setStageLogo(stage);
        stage.setTitle("Developer's friend");
        stage.show();
    }


}
