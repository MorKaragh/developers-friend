package engine;

import engine.dao.yaml.CommandsYamlDao;
import engine.dao.HostsDao;
import engine.dao.yaml.HostsYamlDao;
import engine.ssh.Client;
import engine.state.ApplicationState;
import engine.model.Command;
import engine.model.Host;
import engine.model.UserOnHost;

public class ApplicationEngine {

    private ApplicationState state;
    private InterfaceConnector interfaceConnector;
    private HostsDao hostDao;
    private CommandsYamlDao commandDao;
    private Client sshClient;

    public ApplicationEngine() {
        this.hostDao = new HostsYamlDao();
        this.commandDao = new CommandsYamlDao();
        this.sshClient = new Client();
    }

    public void initState() {
        state = new ApplicationState();
        state.setHostStorage(hostDao.load());
        state.setCommandStorage(commandDao.loadCommands());
        refreshAvailableHosts();
        refreshAvailableCommands();
    }

    public void saveHost(Host host) {
        state.getHostStorage().add(host);
        hostDao.save(state.getHostStorage());
        refreshAvailableHosts();
    }

    public void setSelectedHostAndUser(Host host, UserOnHost userOnHost) {
        state.setSelectedHost(host);
        state.setSelectedUserOnHost(userOnHost);
        if (interfaceConnector != null) {
            interfaceConnector.setSelectedHost(host);
        }
    }

    public void registerInterface(InterfaceConnector connector) {
        this.interfaceConnector = connector;
    }

    private void refreshAvailableHosts(){
        if (interfaceConnector != null) {
            interfaceConnector.refreshAvailableHosts(state.getHostStorage());
        }
    }

    private void refreshAvailableCommands(){
        if (interfaceConnector != null) {
            interfaceConnector.refreshAvailableCommands(state.getCommandStorage());
        }
    }

    public void runCommand(Command command) {

    }

}
