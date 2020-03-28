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
        ApplicationEngine engine = new ApplicationEngine();
        Scene scene = new Scene(new MainView(engine), ApplicationProperties.getDouble("screen.width"), ApplicationProperties.getDouble("screen.height"));
        engine.initState();
        stage.setScene(scene);
        FxUtils.setStageLogo(stage);
        stage.setTitle("Developer's friend");
        stage.show();
    }


}
