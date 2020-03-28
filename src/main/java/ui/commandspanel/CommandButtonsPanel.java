package ui.commandspanel;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import engine.model.Command;

public class CommandButtonsPanel extends ScrollPane {

    private VBox mainLayout;

    public CommandButtonsPanel() {
        mainLayout = new VBox();
        mainLayout.setSpacing(5D);
        mainLayout.setPadding(new Insets(5, 0, 5, 0));
        setMaxWidth(170D);
        setMinWidth(170D);
        for (int i = 0; i < 50; i++) {
            mainLayout.getChildren().add(new RunCommandButton("button " + i, new Command()));
        }
        setContent(mainLayout);
    }

}
