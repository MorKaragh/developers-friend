package engine.model;

public class Command {
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

    public void setName(String name) {
        this.name = name;
    }
}
