package engine;

import engine.dao.HostsDao;
import engine.dao.yaml.CommandsYamlDao;
import engine.dao.yaml.HostsYamlDao;
import engine.model.Command;
import engine.model.Host;
import engine.ssh.Client;
import engine.state.ApplicationState;

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

    public void setSelectedHostAndUser(Host host) {
        state.setSelectedHost(host);
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

    public Host getSelectedHost() {
        return state.getSelectedHost();
    }

    public void saveCommand(Command command) {
        state.getCommandStorage().add(command);
        commandDao.saveCommands(state.getCommandStorage());
        refreshAvailableCommands();
    }
}
