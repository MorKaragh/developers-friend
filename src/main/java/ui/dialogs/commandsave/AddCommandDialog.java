package ui.dialogs.commandsave;

import engine.model.Command;
import engine.model.Host;
import ui.dialogs.savedialog.AbstractSavingDialog;

public class AddCommandDialog extends AbstractSavingDialog<Command> {
    public AddCommandDialog(Host selectedHost, OnSaveAction<Command> onSaveListener) {
        super(new AddCommandDialogLayout(selectedHost), "Add command", onSaveListener);
    }
}
