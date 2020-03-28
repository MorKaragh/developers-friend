package ui.tree;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import ui.utils.Styles;

class BottomButtonPanel extends HBox {

    private final Button addButton;

    public BottomButtonPanel() {
        setPadding(new Insets(15, 12, 15, 12));
        setStyle(Styles.backgroundColor(Styles.DEFAULT_COLOR));
        addButton = createAddButton();
        getChildren().addAll(addButton);
    }

    private Button createAddButton() {
        Button addButton = new Button("add host/user");
        addButton.setTextFill(Color.BLUE);
        return addButton;
    }

    public Button getAddButton() {
        return addButton;
    }
}
