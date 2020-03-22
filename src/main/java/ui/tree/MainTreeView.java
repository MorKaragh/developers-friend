package ui.tree;

import config.ApplicationProperties;
import dao.RemotesDao;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainTreeView extends AnchorPane {

    private final HBox buttonLayout;
    private final TreeComponent treeComponent;

    public MainTreeView() {
        treeComponent = new TreeComponent(new RemotesDao().loadHostsConfig());

        buttonLayout = new HBox();
        buttonLayout.setPadding(new Insets(15, 12, 15, 12));
        buttonLayout.setStyle("-fx-background-color: #336699;");

        Button addButton = new Button("add");
        buttonLayout.getChildren().addAll(addButton);

        addButton.setTextFill(Color.BLUE);
        getChildren().addAll(treeComponent, buttonLayout);


        setBottomAnchor(buttonLayout,0D);
        setTopAnchor(treeComponent, 0D);

        buttonLayout.prefWidthProperty().bind(treeComponent.widthProperty());
        treeComponent.prefHeightProperty().bind(new SimpleDoubleProperty(){
            @Override
            public double get() {
                return ApplicationProperties.getDouble("screen.height") - buttonLayout.getHeight();
            }
        });

    }
}
