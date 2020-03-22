import config.ApplicationProperties;
import engine.ApplicationEngine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.MainView;

public class DevelopersFriendApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        Scene scene = new Scene(new MainView(new ApplicationEngine()), ApplicationProperties.getDouble("screen.width"), ApplicationProperties.getDouble("screen.height"));
        stage.setScene(scene);
        stage.show();
    }


}
