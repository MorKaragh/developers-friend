package ui.tree;

import dao.RemotesDao;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainTreeView extends VBox {

    public MainTreeView() {
        TreeComponent treeComponent = new TreeComponent(new RemotesDao().loadHostsConfig());
        Button addButton = new Button("add");
        addButton.setPrefWidth(100);
        getChildren().addAll(treeComponent, addButton);


        setSpacing(10);

    }
}
