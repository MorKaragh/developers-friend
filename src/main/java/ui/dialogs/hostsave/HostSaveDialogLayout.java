package ui.dialogs.hostsave;

import engine.model.Host;
import engine.model.UserOnHost;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import ui.dialogs.savedialog.AbstractSavingDialogLayout;
import ui.utils.ComponentUtils;
import ui.utils.Styles;

class HostSaveDialogLayout extends AbstractSavingDialogLayout<Host>{

    private final TextField hostNameFld;
    private final TextField usernameFld;

    public HostSaveDialogLayout() {
        hostNameFld = new TextField();
        usernameFld = new TextField();
        createForm();
    }

    public boolean isValid() {
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

    public Host gatherData() {
        Host result = new Host();
        result.setHostname(hostNameFld.getText());
        result.getUserOnHosts().add(new UserOnHost().setUsername(usernameFld.getText()));
        return result;
    }

    private void createForm() {
        setSpacing(5D);
        setPadding(new Insets(0, 10, 0, 10));
        getChildren().addAll(
                ComponentUtils.wrapTextFieldWithLabel(hostNameFld, "host address"),
                ComponentUtils.wrapTextFieldWithLabel(usernameFld, "username")
        );
    }

    public void mountData(Host host) {
        hostNameFld.setText(host.getHostname());
        if (host.hasOnlyOneUser()){
            usernameFld.setText(host.getUserOnHosts().get(0).getUsername());
        }
    }
}
