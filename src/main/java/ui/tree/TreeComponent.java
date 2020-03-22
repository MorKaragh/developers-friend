package ui.tree;

import config.local.LocalHostsConfig;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.MainTreeItem;
import model.RemoteHost;
import model.RemoteUser;

public class TreeComponent extends TreeView<MainTreeItem> {

    public TreeComponent(LocalHostsConfig localHostsConfig) {
        buildTree(localHostsConfig);
    }

    private void buildTree(LocalHostsConfig localHostsConfig) {
        TreeItem<MainTreeItem> rootItem = getItem(new RemoteHost());
        for (RemoteHost host : localHostsConfig.getHosts()) {
            TreeItem<MainTreeItem> hostTreeItem = getItem(host);
            rootItem.getChildren().add(hostTreeItem);
            for (RemoteUser user : host.getRemoteUsers()) {
                TreeItem<MainTreeItem> userItem = getItem(user);
                hostTreeItem.getChildren().add(userItem);
            }
        }
        setRoot(rootItem);
        getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private TreeItem<MainTreeItem> getItem(MainTreeItem item) {
        return new TreeItem<>(item);
    }

    public TreeComponent setListener(ChangeListener<TreeItem<MainTreeItem>> listener) {
        getSelectionModel().selectedItemProperty().addListener(listener);
        return this;
    }

}
