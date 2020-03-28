package config;

import engine.dao.HostsYamlDao;
import model.HostStorage;

public class HostsConfiguration {

    private static volatile HostsConfiguration instance;
    private HostsYamlDao dao;
    private HostStorage hosts;

    private HostsConfiguration(HostsYamlDao dao) {
        this.dao = dao;
        this.hosts = dao.load();
    }

    public static HostsConfiguration getCurrent() {
        if (instance == null) {
            synchronized (HostsConfiguration.class) {
                instance = new HostsConfiguration(new HostsYamlDao());
            }
        }
        return instance;
    }

    public void reload() {
        synchronized (HostsConfiguration.class) {
            instance = new HostsConfiguration(new HostsYamlDao());
        }
    }

    public HostStorage getHosts() {
        return hosts;
    }

    public HostsConfiguration save() {
        dao.save(hosts);
        return this;
    }
}
