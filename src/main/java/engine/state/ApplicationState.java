package engine.state;

import engine.model.Command;
import engine.model.Host;

import java.util.List;

public class ApplicationState {

    private HostStorage hostStorage;
    private CommandStorage commandStorage;
    private Host selectedHost;

    public HostStorage getHostStorage() {
        return hostStorage;
    }

    public ApplicationState setHostStorage(HostStorage hostStorage) {
        this.hostStorage = hostStorage;
        return this;
    }

    public CommandStorage getCommandStorage() {
        return commandStorage;
    }

    public ApplicationState setCommandStorage(CommandStorage commandStorage) {
        this.commandStorage = commandStorage;
        return this;
    }

    public Host getSelectedHost() {
        return selectedHost;
    }

    public ApplicationState setSelectedHost(Host selectedHost) {
        this.selectedHost = selectedHost;
        return this;
    }

}
