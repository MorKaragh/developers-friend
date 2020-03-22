package ui.dialogs;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddHostDialog extends AnchorPane {

    public AddHostDialog() {
        VBox form = new VBox();

        TextField hostNameFld = new TextField();
        hostNameFld.setPromptText("host address");

        TextField usernameFld = new TextField();
        usernameFld.setPromptText("username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");

        Button saveButton = new Button("save");
        Button closeButton = new Button("close");

        form.getChildren().addAll(hostNameFld, usernameFld, passwordField, saveButton, closeButton);

        form.setSpacing(5D);

        getChildren().add(form);
        setTopAnchor(form, 10D);
    }
}
