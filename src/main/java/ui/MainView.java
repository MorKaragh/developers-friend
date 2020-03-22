package ui;

import dao.RemotesDao;
import javafx.scene.layout.BorderPane;
import ui.tree.MainTreeView;
import ui.tree.TreeComponent;

public class MainView extends BorderPane {

    public MainView() {
        MainTreeView value = new MainTreeView();
        setLeft(value);
    }
}
