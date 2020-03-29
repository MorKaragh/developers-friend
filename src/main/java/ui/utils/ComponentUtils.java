package ui.utils;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ComponentUtils {


    public static HBox wrapTextFieldWithLabel(TextField hostNameFld, String labelText) {
        hostNameFld.setPromptText(labelText);
        hostNameFld.setPrefWidth(250);
        HBox hostNameBox = new HBox();
        Label hostNameLabel = new Label(labelText);
        hostNameLabel.setStyle(Styles.fontSizePt(12));
        hostNameLabel.setPrefWidth(100);
        hostNameBox.getChildren().addAll(hostNameLabel, hostNameFld);
        hostNameLabel.setAlignment(Pos.BASELINE_LEFT);
        return hostNameBox;
    }

}
