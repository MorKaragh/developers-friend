package ui.commandspanel;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CommandsView extends VBox {

    CommandButtonsPanel commandButtonsPanel = new CommandButtonsPanel();

    public CommandsView() {
        getChildren().add(commandButtonsPanel);
        setVgrow(commandButtonsPanel, Priority.ALWAYS);

    }
}
