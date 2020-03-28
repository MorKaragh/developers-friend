package engine.state;

import engine.model.Host;
import engine.model.UserOnHost;

public class ApplicationState {

    private HostStorage hostStorage;
    private CommandStorage commandStorage;
    private Host selectedHost;
    private UserOnHost selectedUserOnHost;

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

    public void setSelectedUserOnHost(UserOnHost selectedUserOnHost) {
        this.selectedUserOnHost = selectedUserOnHost;
    }

    public UserOnHost getSelectedUserOnHost() {
        return selectedUserOnHost;
    }
}
