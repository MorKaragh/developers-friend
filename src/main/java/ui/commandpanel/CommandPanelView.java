package ui.commandpanel;

import engine.ApplicationEngine;
import engine.model.Command;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ui.dialogs.commandsave.AddCommandDialog;

import java.util.List;

public class CommandPanelView extends VBox {

    private final ApplicationEngine engine;
    CommandPanel commandPanel = new CommandPanel();
    BottomButtonPanel bottomButtonPanel = new BottomButtonPanel();

    public void mountCommands(List<Command> param) {
        commandPanel.mountData(param);
    }

    public CommandPanelView(ApplicationEngine engine) {
        this.engine = engine;
        getChildren().addAll(commandPanel, bottomButtonPanel);
        setVgrow(commandPanel, Priority.ALWAYS);

        bottomButtonPanel.getAddButton().setOnAction(actionEvent ->
                new AddCommandDialog(engine.getSelectedHost(), engine::saveCommand).openDialog(new Command()));
    }



}
