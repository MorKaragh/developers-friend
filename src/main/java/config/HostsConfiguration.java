package config;

import engine.dao.HostsYamlDao;
import model.HostsList;

public class HostsConfiguration {

    private static volatile HostsConfiguration instance;
    private HostsYamlDao dao;
    private HostsList hosts;

    private HostsConfiguration(HostsYamlDao dao) {
        this.dao = dao;
        this.hosts = dao.loadHostsConfig();
    }

    public static HostsConfiguration getCurrent() {
        if (instance == null) {
            synchronized (HostsConfiguration.class) {
                instance = new HostsConfiguration(new HostsYamlDao());
            }
        }
        return instance;
    }

    public HostsList getHosts() {
        return hosts;
    }

    public HostsConfiguration save() {
        dao.saveLocalHostConfig(hosts);
        return this;
    }
}
