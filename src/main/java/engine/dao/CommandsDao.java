package engine.dao;

import engine.state.CommandStorage;

public interface CommandsDao {
    CommandStorage loadCommands();

    void saveCommands(CommandStorage storage);
}
