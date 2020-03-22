package ui.tree;

import config.ApplicationProperties;
import dao.HostsYamlDao;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.MainView;
import ui.dialogs.AddHostDialog;

public class MainTreeView extends AnchorPane {

    private final HBox buttonLayout;
    private final TreeComponent treeComponent;

    public MainTreeView() {
        treeComponent = new TreeComponent(new HostsYamlDao().loadHostsConfig());
        buttonLayout = createButtonLayout();
        getChildren().addAll(treeComponent, buttonLayout);
        setBottomAnchor(buttonLayout,0D);
        setTopAnchor(treeComponent, 0D);
        setSizes();
    }

    private void setSizes() {
        buttonLayout.prefWidthProperty().bind(treeComponent.widthProperty());
        treeComponent.prefHeightProperty().bind(new SimpleDoubleProperty(){
            @Override
            public double get() {
                return ApplicationProperties.getDouble("screen.height") - buttonLayout.getHeight();
            }
        });
    }

    private HBox createButtonLayout() {
        HBox buttonLayout = new HBox();
        buttonLayout.setPadding(new Insets(15, 12, 15, 12));
        buttonLayout.setStyle("-fx-background-color: #336699;");
        Button addButton = createAddButton();
        buttonLayout.getChildren().addAll(addButton);
        return buttonLayout;
    }

    private Button createAddButton() {
        Button addButton = new Button("add");
        addButton.setTextFill(Color.BLUE);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = new Stage();
                AddHostDialog root = new AddHostDialog();
                Scene scene = new Scene(root, ApplicationProperties.getDouble("screen.width")/2, ApplicationProperties.getDouble("screen.height")/2);
                stage.setScene(scene);
                stage.show();
            }
        });

        return addButton;
    }
}
