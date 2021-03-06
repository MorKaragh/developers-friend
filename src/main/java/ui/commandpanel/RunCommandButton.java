package ui.commandpanel;

import javafx.scene.control.Button;
import engine.model.Command;
import ui.utils.Styles;

class RunCommandButton extends Button {

    private final Command command;

    public RunCommandButton(String text, Command command) {
        super(text);
        this.command = command;
        setMinWidth(150D);
        setStyle(Styles.buttonColor(Styles.DEFAULT_COLOR));
    }

}
