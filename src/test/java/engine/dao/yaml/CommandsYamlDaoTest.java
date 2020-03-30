package engine.dao.yaml;

import engine.model.Command;
import engine.state.CommandStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandsYamlDaoTest {

    @Test
    void saveLoadCommands() {

        CommandsYamlDao dao = new CommandsYamlDao();
        CommandStorage storage = new CommandStorage();
        Command command = new Command().setCommandText("text").setHost("host").setName("name").setUser("user");
        Command command2 = new Command().setCommandText("text2").setHost("host2").setName("name2").setUser("user2");
        storage.add(command);
        storage.add(command2);

        dao.saveCommands(storage);

        CommandStorage loadedStorage = dao.loadCommands();
        assertNotNull(loadedStorage);
        assertEquals(2, loadedStorage.getCommands().size());

        for (int i = 0; i < storage.getCommands().size(); i++){
            assertEquals(storage.getCommands().get(i), loadedStorage.getCommands().get(i));
        }

    }
}