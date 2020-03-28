package ui.tree.dialogs;

import ui.abstractions.savedialog.AbstractSavingDialog;
import ui.model.UserHostPair;

public class HostSaveDialog extends AbstractSavingDialog<UserHostPair> {
    public HostSaveDialog(OnSaveAction<UserHostPair> onSaveListener) {
        super(new HostSaveDialogLayout(), onSaveListener);
    }
}
