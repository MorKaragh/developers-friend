package model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class HostStorage {

    private List<Host> hosts;

    public List<Host> getHosts() {
        if (hosts == null) {
            hosts = new ArrayList<>();
        }
        return hosts;
    }

    public void add(Host host) {
        if (host == null) {
            return;
        }
        Host newHost = host.clone();
        Host hostToChange = null;
        for (Host existingHost : getHosts()) {
            if (existingHost != null && StringUtils.equals(existingHost.getHostname(), newHost.getHostname())) {
                hostToChange = existingHost;
                break;
            }
        }
        if (hostToChange != null) {
            for (UserOnHost newUser : newHost.getUserOnHosts()) {
                boolean userFound = false;
                for (UserOnHost existingUser : hostToChange.getUserOnHosts()) {
                    if (StringUtils.equals(newUser.getUsername(), existingUser.getUsername())) {
                        userFound = true;
                        break;
                    }
                }
                if (!userFound) {
                    hostToChange.getUserOnHosts().add(new UserOnHost().setUsername(newUser.getUsername()));
                }
            }
        } else {
            hosts.add(newHost);
        }
    }

}
