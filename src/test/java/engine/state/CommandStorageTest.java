package engine.state;

import engine.model.Command;
import engine.model.Host;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandStorageTest {

    @Test
    void add() {
        CommandStorage commandStorage = new CommandStorage();
        Command command = new Command().setUser("user").setName("name").setHost("host").setCommandText("text");
        commandStorage.add(command);
        commandStorage.add(SerializationUtils.clone(command));
        assertEquals(1, commandStorage.getCommands().size());

        command = new Command().setUser("user2").setName("name").setHost("host").setCommandText("text");
        commandStorage.add(command);
        commandStorage.add(SerializationUtils.clone(command));
        assertEquals(2, commandStorage.getCommands().size());
    }

    @Test
    void getCommandsByHost() {
        CommandStorage commandStorage = new CommandStorage();
        commandStorage.add(new Command().setCommandText("text").setUser("user").setHost("host").setName("name"));
        commandStorage.add(new Command().setCommandText("text").setUser("user").setHost("host").setName(""));
        commandStorage.add(new Command().setCommandText("text").setUser("user").setHost("another-host").setName("name"));
        commandStorage.add(new Command().setCommandText("text").setUser("user").setHost("another-host").setName(""));

        assertEquals(4, commandStorage.getCommands().size());

        assertEquals(2, commandStorage.getCommandsByHost(new Host().setHostname("another-host")).size());
    }
}