package ui;

import javafx.scene.layout.BorderPane;
import ui.tree.MainTreeView;

public class MainView extends BorderPane {

    public MainView() {
        MainTreeView value = new MainTreeView();
        setLeft(value);
    }
}
