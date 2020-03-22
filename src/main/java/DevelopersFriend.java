import config.ApplicationProperties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.MainView;
import ui.RemoteConfigView;

public class DevelopersFriend extends Application {

    public static void main(String[] args) {
        launch(args);
    }



    public void start(Stage stage) throws Exception {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(new MainView());
        Scene scene = new Scene(stackPane, ApplicationProperties.getDouble("screen.width"), ApplicationProperties.getDouble("screen.height"));
        stage.setScene(scene);
        stage.show();
    }


}
