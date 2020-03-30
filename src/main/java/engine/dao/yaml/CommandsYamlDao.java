package engine.dao.yaml;

import config.ApplicationProperties;
import config.InitializationException;
import engine.dao.CommandsDao;
import engine.dao.DaoException;
import engine.model.Command;
import engine.state.CommandStorage;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.io.*;

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
        Yaml yaml = new Yaml();
        try (FileWriter fileWriter = new FileWriter(ApplicationProperties.get("config.commands.file"))){
            yaml.setBeanAccess(BeanAccess.FIELD);
            String dump = yaml.dumpAsMap(storage);
            fileWriter.write(dump);
        } catch (IOException e) {
            throw new DaoException(e);
        }
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
