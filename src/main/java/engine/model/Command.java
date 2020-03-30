package engine.model;

import java.io.Serializable;
import java.util.Objects;

public class Command implements Serializable {
    private String name;
    private String host;
    private String user;
    private String commandText;

    public boolean isForEveryHost(){
        return host == null;
    }

    public String getCommandText() {
        return commandText;
    }

    public Command setCommandText(String commandText) {
        this.commandText = commandText;
        return this;
    }

    public String getHost() {
        return host;
    }

    public Command setHost(String host) {
        this.host = host;
        return this;
    }

    public String getName() {
        return name;
    }

    public Command setName(String name) {
        this.name = name;
        return this;
    }

    public String getUser() {
        return user;
    }

    public Command setUser(String user) {
        this.user = user;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(name, command.name) &&
                Objects.equals(host, command.host) &&
                Objects.equals(user, command.user) &&
                Objects.equals(commandText, command.commandText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, host, user, commandText);
    }
}
