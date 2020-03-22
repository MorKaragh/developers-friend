package ui.dialogs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Host;
import model.UserOnHost;
import org.apache.commons.lang3.StringUtils;
import ui.utils.Styles;

public class AddHostDialog extends BorderPane {

    private final Button saveButton;
    private final Button closeButton;
    private final TextField hostNameFld;
    private final TextField usernameFld;
    private Listener listener;

    public AddHostDialog() {
        hostNameFld = new TextField();
        usernameFld = new TextField();
        saveButton = createSaveButton();
        closeButton = createCloseButton();
        HBox buttonsLayout = createButtonsLayout();
        VBox form = createForm();
        Label label = createHeaderLabel();

        setCenter(form);
        setBottom(buttonsLayout);
        setTop(label);
    }

    private Button createCloseButton() {
        Button closeButton = new Button("close");
        closeButton.setTextFill(Color.RED);
        closeButton.setOnAction(actionEvent -> {
            if (listener != null) {
                listener.onCLose();
            }
        });
        return closeButton;
    }

    private Button createSaveButton() {
        Button saveButton = new Button("save");
        saveButton.setTextFill(Color.BLUE);
        saveButton.setOnAction(actionEvent -> {
            if (!validate()) {
                return;
            };
            if (listener != null) {
                listener.onSave(gatherData());
            }
        });
        return saveButton;
    }

    private boolean validate() {
        boolean isValid = true;
        if (StringUtils.isBlank(hostNameFld.getText())) {
            hostNameFld.setStyle(Styles.textFieldBorder("red"));
            isValid = false;
        }
        if (StringUtils.isBlank(usernameFld.getText())) {
            usernameFld.setStyle(Styles.textFieldBorder("red"));
            isValid = false;
        }
        return isValid;
    }

    private Host gatherData() {
        Host host = new Host().setHostname(hostNameFld.getText());
        host.getUserOnHosts().add(new UserOnHost().setUsername(usernameFld.getText()));
        return host;
    }

    private VBox createForm() {
        VBox form = new VBox();
        form.setSpacing(5D);
        form.setPadding(new Insets(0, 10, 0, 10));
        form.getChildren().addAll(
                wrapTextFieldWithLabel(hostNameFld, "host address"),
                wrapTextFieldWithLabel(usernameFld, "username")
        );
        return form;
    }

    private Label createHeaderLabel() {
        Label label = new Label("New hostname and/or username");
        label.setStyle(Styles.fontSizePt(15));
        label.setPadding(new Insets(10, 5, 20, 5));
        return label;
    }

    private HBox createButtonsLayout() {
        HBox buttonsLayout = new HBox();
        buttonsLayout.setSpacing(10);
        buttonsLayout.getChildren().addAll(saveButton, closeButton);
        buttonsLayout.setPadding(new Insets(20, 10, 10, 0));
        buttonsLayout.setAlignment(Pos.BOTTOM_RIGHT);
        return buttonsLayout;
    }

    private HBox wrapTextFieldWithLabel(TextField hostNameFld, String labelText) {
        hostNameFld.setPromptText(labelText);
        hostNameFld.setPrefWidth(250);
        HBox hostNameBox = new HBox();
        Label hostNameLabel = new Label(labelText);
        hostNameLabel.setPrefWidth(100);
        hostNameBox.getChildren().addAll(hostNameLabel, hostNameFld);
        hostNameLabel.setAlignment(Pos.BASELINE_LEFT);
        return hostNameBox;
    }

    public AddHostDialog setListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public void mountData(Host host, UserOnHost userOnHost) {
        if (host != null) {
            hostNameFld.setText(host.getHostname());
        }
        if (userOnHost != null) {
            usernameFld.setText(userOnHost.getUsername());
        }
    }

    public interface Listener {
        void onSave(Host host);
        void onCLose();
    }

}
