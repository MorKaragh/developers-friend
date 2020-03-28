package ui;

import engine.ApplicationEngine;
import engine.InterfaceConnector;
import javafx.scene.layout.BorderPane;
import engine.state.CommandStorage;
import engine.model.Host;
import engine.state.HostStorage;
import engine.model.UserOnHost;
import ui.commandspanel.CommandsView;
import ui.hoststree.HostsTreeView;
import ui.terminal.TerminalView;

public class MainView extends BorderPane implements InterfaceConnector {

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
            }
            @Override
            public void onSelect(Host host, UserOnHost userOnHost) {
                engine.setSelectedHostAndUser(host, userOnHost);
            }
        });
        setLeft(tree);
        setRight(commandsView);
        setCenter(terminalView);
        engine.registerInterface(this);
    }

    @Override
    public void refreshAvailableHosts(HostStorage storage) {
        tree.refresh(storage);
    }

    @Override
    public void displayCommandResult(String result) {

    }

    @Override
    public void refreshAvailableCommands(CommandStorage commandStorage) {

    }

    @Override
    public void setSelectedHost(Host host) {

    }

}
