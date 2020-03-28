package ui.tree;

import config.ApplicationProperties;
import engine.model.Host;
import engine.state.HostStorage;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ui.model.UserHostPair;
import ui.tree.dialogs.HostSaveDialog;

public class HostsTreeView extends VBox {

    private final BottomButtonPanel buttonLayout;
    private final Tree tree;
    private Listener listener;

    public HostsTreeView() {
        tree = new Tree();
        buttonLayout = new BottomButtonPanel();
        buildLayout();
        initListeners();
    }

    private void initListeners() {
        buttonLayout.getAddButton().setOnAction(actionEvent -> {
            new HostSaveDialog(hostPair -> {
                if (listener != null) {
                    listener.saveHost(hostPair.asHost());
                }
            }).openDialog(tree.getValue());
        });

        tree.setListener(selected -> {
            if (listener != null) {
                listener.onSelect(selected);
            }
        });
    }

    private void buildLayout() {
        getChildren().addAll(tree, buttonLayout);
        setVgrow(tree, Priority.ALWAYS);
        setSizes();
    }

    private void setSizes() {
        buttonLayout.prefWidthProperty().bind(tree.widthProperty());
        tree.prefHeightProperty().bind(new SimpleDoubleProperty(){
            @Override
            public double get() {
                return ApplicationProperties.getDouble("screen.height") - buttonLayout.getHeight();
            }
        });
    }

    public HostsTreeView setListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public void refresh(HostStorage hosts) {
        tree.fillTree(hosts);
    }

    public interface Listener {
        void saveHost(Host host);
        void onSelect(UserHostPair userHostPair);
    }
}
