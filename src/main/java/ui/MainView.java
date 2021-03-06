package ui;

import engine.ApplicationEngine;
import engine.InterfaceConnector;
import engine.model.Command;
import engine.model.Host;
import engine.state.CommandStorage;
import engine.state.HostStorage;
import javafx.scene.layout.BorderPane;
import ui.commandpanel.CommandPanelView;
import ui.terminal.TerminalView;
import ui.tree.HostsTreeView;

import java.util.List;

public class MainView extends BorderPane implements InterfaceConnector {

    private final HostsTreeView tree;
    private final CommandPanelView commandPanelView;
    private final TerminalView terminalView;

    public MainView(ApplicationEngine engine) {
        tree = new HostsTreeView();
        commandPanelView = new CommandPanelView(engine);
        terminalView = new TerminalView();

        tree.setListener(new HostsTreeView.Listener() {
            @Override
            public void saveHost(Host host) {
                engine.saveHost(host);
            }

            @Override
            public void onSelect(Host host) {
                engine.setSelectedHostAndUser(host);
            }
        });
        setLeft(tree);
        setRight(commandPanelView);
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

    @Override
    public void setAvailableCommands(List<Command> commandsByHost) {
        commandPanelView.mountCommands(commandsByHost);
    }

}
