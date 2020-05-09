package engine;

import engine.model.Command;
import engine.state.CommandStorage;
import engine.model.Host;
import engine.state.HostStorage;

import java.util.List;

public interface InterfaceConnector {

    void refreshAvailableHosts(HostStorage hostStorage);
    void displayCommandResult(String result);
    void refreshAvailableCommands(CommandStorage commandStorage);
    void setSelectedHost(Host host);
    void setAvailableCommands(List<Command> commandsByHost);
}
