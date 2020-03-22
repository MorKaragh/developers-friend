package model;

import java.util.ArrayList;
import java.util.List;

public class RemoteHost implements MainTreeItem{

    private String hostname;
    private List<RemoteUser> remoteUsers;
    private boolean known;


    public String getHostname() {
        return hostname;
    }

    public RemoteHost setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public List<RemoteUser> getRemoteUsers() {
        if (remoteUsers == null) {
            remoteUsers = new ArrayList<>();
        }
        return remoteUsers;
    }

    public RemoteHost setRemoteUsers(List<RemoteUser> remoteUsers) {
        this.remoteUsers = remoteUsers;
        return this;
    }

    public boolean isKnown() {
        return known;
    }

    public RemoteHost setKnown(boolean known) {
        this.known = known;
        return this;
    }

    @Override
    public String toString() {
        if (hostname != null) {
            return hostname;
        }
        return "hosts";
    }
}
