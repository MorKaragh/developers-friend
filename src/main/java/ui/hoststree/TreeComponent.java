package ui.hoststree;

import model.HostStorage;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.MainTreeItem;
import model.Host;
import model.UserOnHost;

public class TreeComponent extends TreeView<MainTreeItem> {

    private Listener listener;

    public TreeComponent(HostStorage hostStorage) {
        buildTree(hostStorage);
        initListeners();
    }

    public Selected getSelected() {
        TreeItem<MainTreeItem> selectedItem = getSelectionModel().getSelectedItem();
        return selectedItem != null ? extractSelected(selectedItem) : new Selected();
    }

    private void initListeners() {
        setTreeLitener((observableValue, mainTreeItemTreeItem, treeItem) -> {
            if (treeItem == null || treeItem == getRoot()) {
                return;
            }
            Selected selected = extractSelected(treeItem);
            if (listener != null) {
                listener.onSelect(selected);
            }
        });
    }

    private Selected extractSelected(TreeItem<MainTreeItem> treeItem) {
        Selected selected = new Selected();
        if (treeItem.getValue() instanceof UserOnHost) {
            selected.userOnHost = (UserOnHost) treeItem.getValue();
            selected.host = (Host) treeItem.getParent().getValue();
        } else if (treeItem.getValue() instanceof Host){
            selected.host = (Host) treeItem.getValue();
        }
        return selected;
    }

    private void buildTree(HostStorage hostStorage) {
        fillTree(hostStorage);
        getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    void fillTree(HostStorage hostStorage) {
        TreeItem<MainTreeItem> rootItem = getItem(new Host());
        for (Host host : hostStorage.getHosts()) {
            TreeItem<MainTreeItem> hostTreeItem = getItem(host);
            rootItem.getChildren().add(hostTreeItem);
            for (UserOnHost user : host.getUserOnHosts()) {
                TreeItem<MainTreeItem> userItem = getItem(user);
                hostTreeItem.getChildren().add(userItem);
            }
        }
        setRoot(rootItem);
    }

    private TreeItem<MainTreeItem> getItem(MainTreeItem item) {
        return new TreeItem<>(item);
    }

    private TreeComponent setTreeLitener(ChangeListener<TreeItem<MainTreeItem>> listener) {
        getSelectionModel().selectedItemProperty().addListener(listener);
        return this;
    }

    public TreeComponent setListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public interface Listener {
        void onSelect(Selected selected);
    }

    static class Selected {
        private Host host;
        private UserOnHost userOnHost;

        public Host getHost() {
            return host;
        }

        public Selected setHost(Host host) {
            this.host = host;
            return this;
        }

        public UserOnHost getUserOnHost() {
            return userOnHost;
        }

        public Selected setUserOnHost(UserOnHost userOnHost) {
            this.userOnHost = userOnHost;
            return this;
        }
    }

}