package ui;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RemoteConfigView extends AnchorPane {

    public RemoteConfigView() {
        VBox form = new VBox();

        TextField hostNameFld = new TextField();
        hostNameFld.setPromptText("host address");

        TextField usernameFld = new TextField();
        usernameFld.setPromptText("username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");

        form.getChildren().addAll(hostNameFld, usernameFld, passwordField);

        form.setSpacing(5D);

        getChildren().add(form);
        setTopAnchor(form, 10D);
    }
}
