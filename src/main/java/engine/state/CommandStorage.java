package engine.state;

import engine.model.Command;
import engine.model.Host;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandStorage {

    private List<Command> commands = new ArrayList<>();

    public List<Command> getCommands() {
        if (commands == null) {
            commands = new ArrayList<>();
        }
        return commands;
    }

    public void add(Command command) {
        for (Command c : getCommands()) {
            if (c.equals(command)) {
                return;
            }
        }
        getCommands().add(command);
    }

    public List<Command> getCommandsByHost(Host host) {
        return getCommands().stream()
                .filter(command -> StringUtils.equals(command.getHost(), host.getHostname()))
                .filter(command -> StringUtils.isBlank(command.getUser())
                        || (host.getUserOnHosts().isEmpty() && StringUtils.isBlank(command.getUser()))
                        || (!host.getUserOnHosts().isEmpty()
                        && StringUtils.equals(host.getUserOnHosts().get(0).getUsername(), command.getUser())
                )).collect(Collectors.toList());
    }

}
