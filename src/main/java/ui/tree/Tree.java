package ui.tree;

import engine.state.HostStorage;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import engine.model.Host;
import engine.model.UserOnHost;
import ui.model.UserHostPair;

class Tree extends TreeView<Object> {

    private Listener listener;

    public Tree() {
        buildTree();
        initListeners();
    }

    public UserHostPair getValue() {
        TreeItem<Object> selectedItem = getSelectionModel().getSelectedItem();
        return selectedItem != null ? extractSelected(selectedItem) : new UserHostPair();
    }

    private void initListeners() {
        setTreeLitener((observableValue, mainTreeItemTreeItem, treeItem) -> {
            if (treeItem == null || treeItem == getRoot()) {
                return;
            }
            UserHostPair selected = extractSelected(treeItem);
            if (listener != null) {
                listener.onSelect(selected);
            }
        });
    }

    private UserHostPair extractSelected(TreeItem<Object> treeItem) {
        UserHostPair selected = new UserHostPair();
        if (treeItem.getValue() instanceof UserOnHost) {
            selected.setUserOnHost((UserOnHost) treeItem.getValue());
            selected.setHost((Host) treeItem.getParent().getValue());
        } else if (treeItem.getValue() instanceof Host){
            selected.setHost((Host) treeItem.getValue());
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
        void onSelect(UserHostPair selected);
    }

}
