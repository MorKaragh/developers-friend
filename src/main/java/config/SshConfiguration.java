package config;

import engine.dao.SshPropertiesDao;
import model.SshProperties;

public class SshConfiguration {

    private static volatile SshConfiguration instance;
    private static SshPropertiesDao dao;
    private SshProperties properties;

    private SshConfiguration(SshPropertiesDao sshProperties) {
        dao = sshProperties;
        this.properties = dao.loadProperties();
    }

    public static SshConfiguration getCurrent() {
        if (instance == null) {
            synchronized (HostsConfiguration.class) {
                instance = new SshConfiguration(new SshPropertiesDao());
            }
        }
        return instance;
    }

    public SshProperties getProperties() {
        return properties;
    }

    public SshConfiguration save() {
        dao.saveSshProperties(properties);
        return this;
    }

}
