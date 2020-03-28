package ui.hoststree;

import config.ApplicationProperties;
import engine.dao.yaml.HostsYamlDao;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import engine.model.Host;
import engine.state.HostStorage;
import engine.model.UserOnHost;
import ui.dialogs.AddHostDialog;
import ui.utils.Styles;

public class HostsTreeView extends VBox {

    private final HBox buttonLayout;
    private final TreeComponent treeComponent;
    private Listener listener;

    public HostsTreeView() {
        treeComponent = createTree();
        buttonLayout = createButtonLayout();
        buildLayout();
    }

    private TreeComponent createTree() {
        TreeComponent tree = new TreeComponent(new HostsYamlDao().load());
        tree.setListener(selected -> {
            if (listener != null) {
                listener.onSelect(selected.getHost(), selected.getUserOnHost());
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
        buttonLayout.setStyle(Styles.backgroundColor(Styles.DEFAULT_COLOR));
        Button addButton = createAddButton();
        buttonLayout.getChildren().addAll(addButton);
        return buttonLayout;
    }

    private Button createAddButton() {
        Button addButton = new Button("add host/user");
        addButton.setTextFill(Color.BLUE);
        addButton.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            AddHostDialog dialog = new AddHostDialog();
            dialog.mountData(treeComponent.getSelected().getHost(), treeComponent.getSelected().getUserOnHost());
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

    public HostsTreeView setListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public void refresh(HostStorage hosts) {
        treeComponent.fillTree(hosts);
    }

    public interface Listener {
        void saveHost(Host host);
        void onSelect(Host host, UserOnHost userOnHost);
    }
}
