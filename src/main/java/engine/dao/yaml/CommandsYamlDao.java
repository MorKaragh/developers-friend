package engine.dao.yaml;

import config.ApplicationProperties;
import config.InitializationException;
import engine.dao.CommandsDao;
import engine.model.Command;
import engine.state.CommandStorage;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CommandsYamlDao implements CommandsDao {

    @Override
    public CommandStorage loadCommands() {
        Constructor constructor = new Constructor(CommandStorage.class);
        TypeDescription definition = new TypeDescription(CommandStorage.class);
        definition.addPropertyParameters("commands", Command.class);
        constructor.addTypeDescription(definition);
        Yaml yaml = new Yaml(constructor);
        yaml.setBeanAccess(BeanAccess.FIELD);
        try {
            CommandStorage load = yaml.load(new FileReader(ApplicationProperties.get("config.commands.file")));
            return load != null ? load : new CommandStorage();
        } catch (FileNotFoundException e) {
            initStorageFile();
            return new CommandStorage();
        }
    }

    @Override
    public void saveCommands(CommandStorage storage) {

    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void initStorageFile(){
        try {
            new File(ApplicationProperties.get("config.commands.file")).createNewFile();
        } catch (IOException e) {
            throw new InitializationException("cannot create hosts.yaml", e);
        }
    }

}
