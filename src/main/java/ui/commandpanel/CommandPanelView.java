package ui.commandpanel;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CommandPanelView extends VBox {

    CommandButtonsPanel commandButtonsPanel = new CommandButtonsPanel();
    BottomButtonPanel bottomButtonPanel = new BottomButtonPanel();

    public CommandPanelView() {
        getChildren().addAll(commandButtonsPanel, bottomButtonPanel);
        setVgrow(commandButtonsPanel, Priority.ALWAYS);
    }



}
