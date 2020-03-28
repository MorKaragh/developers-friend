package engine;

import engine.state.CommandStorage;
import engine.model.Host;
import engine.state.HostStorage;

public interface InterfaceConnector {

    void refreshAvailableHosts(HostStorage hostStorage);
    void displayCommandResult(String result);
    void refreshAvailableCommands(CommandStorage commandStorage);
    void setSelectedHost(Host host);

}
