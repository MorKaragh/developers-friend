package ui.commandpanel;

import engine.ApplicationEngine;
import engine.model.Command;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ui.dialogs.commandsave.AddCommandDialog;
import ui.dialogs.savedialog.AbstractSavingDialog;

public class CommandPanelView extends VBox {

    private final ApplicationEngine engine;
    CommandPanel commandPanel = new CommandPanel();
    BottomButtonPanel bottomButtonPanel = new BottomButtonPanel();

    public CommandPanelView(ApplicationEngine engine) {
        this.engine = engine;
        getChildren().addAll(commandPanel, bottomButtonPanel);
        setVgrow(commandPanel, Priority.ALWAYS);

        bottomButtonPanel.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new AddCommandDialog(engine.getSelectedHost(), new AbstractSavingDialog.OnSaveAction<Command>() {
                    @Override
                    public void onSave(Command host) {

                    }
                }).openDialog(new Command());
            }
        });
    }



}
