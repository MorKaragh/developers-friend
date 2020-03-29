package ui.commandpanel;

import engine.model.Command;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

class CommandPanel extends ScrollPane {

    public static final double MAX_WIDTH = 170D;
    public static final double MIN_WIDTH = 170D;
    private VBox mainLayout;
    private Listener listener;

    public CommandPanel() {
        mainLayout = new VBox();
        mainLayout.setSpacing(5D);
        mainLayout.setPadding(new Insets(5, 0, 5, 0));
        setMaxWidth(MAX_WIDTH);
        setMinWidth(MIN_WIDTH);
        setContent(mainLayout);
    }

    public void mountData(List<Command> commands) {
        commands.forEach(command -> {
            RunCommandButton button = new RunCommandButton(command.getName(), new Command());
            button.setOnAction(actionEvent -> {
                if (listener != null) {
                    listener.executeCommand(command);
                }
            });
            mainLayout.getChildren().add(button);
        });
    }

    interface Listener {
        void executeCommand(Command command);
    }

}
