package ui.tree;

import model.HostsList;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.MainTreeItem;
import model.Host;
import model.UserOnHost;

public class TreeComponent extends TreeView<MainTreeItem> {

    public TreeComponent(HostsList hostsList) {
        buildTree(hostsList);
    }

    private void buildTree(HostsList hostsList) {
        fillTree(hostsList);
        getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    void fillTree(HostsList hostsList) {
        TreeItem<MainTreeItem> rootItem = getItem(new Host());
        for (Host host : hostsList.getHosts()) {
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

    public TreeComponent setListener(ChangeListener<TreeItem<MainTreeItem>> listener) {
        getSelectionModel().selectedItemProperty().addListener(listener);
        return this;
    }

}
