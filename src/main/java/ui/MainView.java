package ui;

import engine.ApplicationEngine;
import javafx.scene.layout.BorderPane;
import model.Host;
import model.UserOnHost;
import ui.commandspanel.CommandsView;
import ui.hoststree.HostsTreeView;

public class MainView extends BorderPane {

    public MainView(ApplicationEngine engine) {
        HostsTreeView tree = new HostsTreeView();
        CommandsView commandsView = new CommandsView();
        tree.setListener(new HostsTreeView.Listener() {
            @Override
            public void saveHost(Host host) {
                engine.saveHost(host);
                tree.refresh();
            }

            @Override
            public void onSelect(Host host, UserOnHost userOnHost) {
                if (host != null) {System.out.println(host.toString());}
                if (userOnHost != null) {System.out.println(userOnHost.toString());}
            }
        });
        setLeft(tree);
        setRight(commandsView);
    }
}
