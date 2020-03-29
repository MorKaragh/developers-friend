package ui.tree;

import engine.model.Host;
import engine.model.UserOnHost;
import engine.state.HostStorage;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.apache.commons.lang3.SerializationUtils;

class Tree extends TreeView<Object> {

    private Listener listener;

    public Tree() {
        buildTree();
        initListeners();
    }

    public Host getValue() {
        TreeItem<Object> selectedItem = getSelectionModel().getSelectedItem();
        return selectedItem != null ? extractSelected(selectedItem) : new Host();
    }

    private void initListeners() {
        setTreeLitener((observableValue, mainTreeItemTreeItem, treeItem) -> {
            if (treeItem == null || treeItem == getRoot()) {
                return;
            }
            Host selected = extractSelected(treeItem);
            if (listener != null) {
                listener.onSelect(selected);
            }
        });
    }

    private Host extractSelected(TreeItem<Object> treeItem) {
        Host selected = new Host();
        if (treeItem.getValue() instanceof UserOnHost) {
            selected = SerializationUtils.clone((Host) treeItem.getParent().getValue());
            selected.getUserOnHosts().clear();
            UserOnHost value = (UserOnHost) treeItem.getValue();
            selected.getUserOnHosts().add(value);
        } else if (treeItem.getValue() instanceof Host){
            selected = SerializationUtils.clone((Host) treeItem.getValue());
        }
        return selected;
    }

    private void buildTree() {
        getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    void fillTree(HostStorage hostStorage) {
        TreeItem<Object> rootItem = getItem(new Host());
        for (Host host : hostStorage.getHosts()) {
            TreeItem<Object> hostTreeItem = getItem(host);
            rootItem.getChildren().add(hostTreeItem);
            for (UserOnHost user : host.getUserOnHosts()) {
                TreeItem<Object> userItem = getItem(user);
                hostTreeItem.getChildren().add(userItem);
            }
        }
        setRoot(rootItem);
    }

    private TreeItem<Object> getItem(Object item) {
        return new TreeItem<>(item);
    }

    private Tree setTreeLitener(ChangeListener<TreeItem<Object>> listener) {
        getSelectionModel().selectedItemProperty().addListener(listener);
        return this;
    }

    public Tree setListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public interface Listener {
        void onSelect(Host selected);
    }

}
