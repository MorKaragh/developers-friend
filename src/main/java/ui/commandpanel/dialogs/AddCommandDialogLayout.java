package ui.commandpanel.dialogs;

import engine.model.Command;
import ui.abstractions.savedialog.AbstractSavingDialogLayout;

public class AddCommandDialogLayout extends AbstractSavingDialogLayout<Command> {

    @Override
    public Command gatherData() {
        return null;
    }

    @Override
    public void mountData(Command data) {

    }

    @Override
    public boolean validate() {
        return false;
    }
}
