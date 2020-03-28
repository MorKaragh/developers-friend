package ui.model;

import engine.model.Host;
import engine.model.UserOnHost;
import org.apache.commons.lang3.SerializationUtils;

public class UserHostPair {
    private Host host;
    private UserOnHost userOnHost;

    public Host getHost() {
        return host;
    }

    public UserHostPair setHost(Host host) {
        this.host = host;
        return this;
    }

    public UserOnHost getUserOnHost() {
        return userOnHost;
    }

    public UserHostPair setUserOnHost(UserOnHost userOnHost) {
        this.userOnHost = userOnHost;
        return this;
    }

    public Host asHost() {
        Host clone = SerializationUtils.clone(host);
        if (userOnHost != null) {
            clone.getUserOnHosts().add(userOnHost);
        }
        return clone;
    }
}
