package ui.tree;

import config.ApplicationProperties;
import config.HostsConfiguration;
import engine.dao.HostsYamlDao;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Host;
import model.UserOnHost;
import ui.dialogs.AddHostDialog;

public class MainTreeView extends VBox {

    private final HBox buttonLayout;
    private final TreeComponent treeComponent;
    private Listener listener;

    public MainTreeView() {
        treeComponent = createTree();
        buttonLayout = createButtonLayout();
        buildLayout();
    }

    private TreeComponent createTree() {
        TreeComponent tree = new TreeComponent(new HostsYamlDao().loadHostsConfig());
        tree.setListener((observableValue, mainTreeItemTreeItem, treeItem) -> {
            if (treeItem == mainTreeItemTreeItem) {
                return;
            }
            UserOnHost selectedUser = null;
            Host selectedHost = null;
            if (treeItem.getValue() instanceof UserOnHost) {
                selectedUser = (UserOnHost) treeItem.getValue();
                selectedHost = (Host) treeItem.getParent().getValue();
            } else if (treeItem.getValue() instanceof Host){
                selectedHost = (Host) treeItem.getValue();
            }
            if (listener != null) {
                listener.onSelect(selectedHost, selectedUser);
            }
        });
        return tree;
    }

    private void buildLayout() {
        getChildren().addAll(treeComponent, buttonLayout);
        setVgrow(treeComponent, Priority.ALWAYS);
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
        addButton.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            AddHostDialog dialog = new AddHostDialog();
            dialog.setListener(new AddHostDialog.Listener() {
                @Override
                public void onSave(Host host) {
                    if (listener != null) {
                        listener.saveHost(host);
                        stage.close();
                    }
                }

                @Override
                public void onCLose() {
                    stage.close();
                }
            });
            Scene scene = new Scene(dialog);
            stage.setScene(scene);
            stage.show();
        });

        return addButton;
    }

    public MainTreeView setListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public void refresh() {
        treeComponent.fillTree(HostsConfiguration.getCurrent().getHosts());
    }

    public interface Listener {
        void saveHost(Host host);
        void onSelect(Host host, UserOnHost userOnHost);
    }
}
