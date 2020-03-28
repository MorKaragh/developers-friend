package ui.tree.dialogs;

import engine.model.Host;
import engine.model.UserOnHost;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.apache.commons.lang3.StringUtils;
import ui.abstractions.savedialog.AbstractSavingDialogLayout;
import ui.model.UserHostPair;
import ui.utils.Styles;

class HostSaveDialogLayout extends AbstractSavingDialogLayout<UserHostPair>{

    private final TextField hostNameFld;
    private final TextField usernameFld;

    public HostSaveDialogLayout() {
        hostNameFld = new TextField();
        usernameFld = new TextField();
        createForm();
    }

    public boolean validate() {
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

    public UserHostPair gatherData() {
        UserHostPair result = new UserHostPair();
        result.setHost(new Host().setHostname(hostNameFld.getText()));
        result.setUserOnHost(new UserOnHost().setUsername(usernameFld.getText()));
        return result;
    }

    private void createForm() {
        setSpacing(5D);
        setPadding(new Insets(0, 10, 0, 10));
        getChildren().addAll(
                wrapTextFieldWithLabel(hostNameFld, "host address"),
                wrapTextFieldWithLabel(usernameFld, "username")
        );
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

    public void mountData(UserHostPair userHost) {
        if (userHost.getHost() != null) {
            hostNameFld.setText(userHost.getHost().getHostname());
        }
        if (userHost.getUserOnHost() != null) {
            usernameFld.setText(userHost.getUserOnHost().getUsername());
        }
    }
}
