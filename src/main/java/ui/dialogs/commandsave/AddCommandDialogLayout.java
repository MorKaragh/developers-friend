package ui.dialogs.commandsave;

import config.InitializationException;
import engine.model.Command;
import engine.model.Host;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import ui.dialogs.savedialog.AbstractSavingDialogLayout;
import ui.terminal.TerminalView;
import ui.utils.ComponentUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

class AddCommandDialogLayout extends AbstractSavingDialogLayout<Command> {

    private final Host selectedHost;
    private TextArea command;
    private TextField name;
    private CheckBox onlyForHost;

    public AddCommandDialogLayout(Host selectedHost) {
        this.selectedHost = selectedHost;
        command = initTextArea();
        name = new TextField();
        name.setPromptText("description");
        onlyForHost = new CheckBox(getCheckboxLabel());
        onlyForHost.setPadding(new Insets(0, 0, 0, 5D));
        HBox description = ComponentUtils.wrapTextFieldWithLabel(name, "description");
        description.setPadding(new Insets(0, 5, 0, 5));
        getChildren().addAll(description, command, onlyForHost);
        setSpacing(5D);
    }

    private TextArea initTextArea() {
        TextArea command = new TextArea();
        command.setStyle("-fx-control-inner-background:#000000; -fx-text-fill: #ffffff;");
        try (InputStream inputStream = Objects.requireNonNull(TerminalView.class.getClassLoader()
                .getResource("UbuntuMono-R.ttf")).openStream()) {
            command.setFont(Font.loadFont(inputStream, 14));
        } catch (IOException e) {
            throw new InitializationException("cannot find font for terminal", e);
        }
        return command;
    }

    private String getCheckboxLabel() {
        String base = "only for selected host";
        if (selectedHost != null) {
            base += " " + selectedHost.toString();
        }
        return base;
    }

    @Override
    public Command gatherData() {
        return null;
    }

    @Override
    public void mountData(Command data) {

    }

    @Override
    public boolean isValid() {
        return false;
    }
}
