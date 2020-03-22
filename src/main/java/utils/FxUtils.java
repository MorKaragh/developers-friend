package utils;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.Objects;

public class FxUtils {

    public static void setStageLogo(Stage stage) {
        try {
            String file = Objects.requireNonNull(FxUtils.class.getClassLoader()
                    .getResource("logo.jpg")).getFile();
            stage.getIcons().add(new Image(new FileInputStream(file)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
