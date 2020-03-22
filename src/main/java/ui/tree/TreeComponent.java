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
        getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<MainTreeItem>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<MainTreeItem>> observableValue, TreeItem<MainTreeItem> mainTreeItemTreeItem, TreeItem<MainTreeItem> t1) {
                System.out.println(t1.getValue());
                System.out.println(t1.getValue() instanceof RemoteHost);
                System.out.println(observableValue);
                System.out.println("select!");
            }
        });


    }

    private TreeItem<MainTreeItem> getItem(MainTreeItem item) {
        return new TreeItem<>(item);
    }

}
