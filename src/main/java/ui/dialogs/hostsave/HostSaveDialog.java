package ui.dialogs.hostsave;

import engine.model.Host;
import ui.dialogs.savedialog.AbstractSavingDialog;

public class HostSaveDialog extends AbstractSavingDialog<Host> {
    public HostSaveDialog(OnSaveAction<Host> onSaveListener) {
        super(new HostSaveDialogLayout(), "New hostname and/or username", onSaveListener);
    }
}
