package engine.model;

public class Command {
    private String commandText;
    private String host;

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
}
