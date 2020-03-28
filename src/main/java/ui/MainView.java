package ui;

import engine.ApplicationEngine;
import javafx.scene.layout.BorderPane;
import model.Host;
import model.UserOnHost;
import ui.commandspanel.CommandsView;
import ui.hoststree.HostsTreeView;
import ui.terminal.TerminalView;

public class MainView extends BorderPane {

    private final HostsTreeView tree;
    private final CommandsView commandsView;
    private final TerminalView terminalView;

    public MainView(ApplicationEngine engine) {
        tree = new HostsTreeView();
        commandsView = new CommandsView();
        terminalView = new TerminalView();

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
        setCenter(terminalView);
    }
}
