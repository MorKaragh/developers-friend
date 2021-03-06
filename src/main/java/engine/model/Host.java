package engine.model;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Host implements Serializable {

    private String hostname;
    private List<UserOnHost> userOnHosts;
    private boolean known;


    public String getHostname() {
        return hostname;
    }

    public Host setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public List<UserOnHost> getUserOnHosts() {
        if (userOnHosts == null) {
            userOnHosts = new ArrayList<>();
        }
        return userOnHosts;
    }

    public Host setUserOnHosts(List<UserOnHost> userOnHosts) {
        this.userOnHosts = userOnHosts;
        return this;
    }

    public boolean isKnown() {
        return known;
    }

    public Host setKnown(boolean known) {
        this.known = known;
        return this;
    }

    @Override
    public String toString() {
        if (hostname != null) {
            String result = hostname;
            if (hasOnlyOneUser()){
                result += "@" + userOnHosts.get(0).toString();
            }
            return result;
        }
        return "hosts";
    }

    public boolean hasOnlyOneUser() {
        return userOnHosts != null && userOnHosts.size() == 1;
    }

    public Host clone() {
        return SerializationUtils.clone(this);
    }
}
