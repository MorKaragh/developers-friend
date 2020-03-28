package model;

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {

    List<Command> commands = new ArrayList<>();

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

}
