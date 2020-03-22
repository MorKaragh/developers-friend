package config.local;

import model.RemoteHost;

import java.util.ArrayList;
import java.util.List;

public class LocalHostsConfig {

    private List<RemoteHost> hosts;

    public List<RemoteHost> getHosts() {
        if (hosts == null) {
            hosts = new ArrayList<>();
        }
        return hosts;
    }

    public LocalHostsConfig setHosts(List<RemoteHost> hosts) {
        this.hosts = hosts;
        return this;
    }
}
