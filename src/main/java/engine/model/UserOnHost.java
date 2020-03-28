package engine.model;

import java.io.Serializable;

public class UserOnHost implements Serializable {

    private String username;

    public String getUsername() {
        return username;
    }

    public UserOnHost setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String toString() {
        if (username != null) {
            return username;
        }
        return "- not specified -";
    }

    public String getPassword() {
        return null;
    }

}
