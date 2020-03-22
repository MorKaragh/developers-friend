package engine;

import config.HostsConfiguration;
import config.SshConfiguration;
import engine.dao.HostsYamlDao;
import engine.ssh.Client;
import model.Host;

public class ApplicationEngine {

    private HostsYamlDao dao;
    private Client sshClient;

    public ApplicationEngine() {
        this.dao = new HostsYamlDao();
        this.sshClient = new Client(SshConfiguration.getCurrent().getProperties());
    }

    public void saveHost(Host host) {
        HostsConfiguration.getCurrent().getHosts().add(host);
        HostsConfiguration.getCurrent().save();
    }
}
