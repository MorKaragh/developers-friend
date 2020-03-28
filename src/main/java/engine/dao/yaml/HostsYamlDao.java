package engine.dao.yaml;

import config.ApplicationProperties;
import config.InitializationException;
import engine.dao.DaoException;
import engine.dao.HostsDao;
import engine.state.HostStorage;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import engine.model.Host;

import java.io.*;

public class HostsYamlDao implements HostsDao {

    @Override
    public HostStorage load() {
        Constructor constructor = new Constructor(HostStorage.class);
        TypeDescription definition = new TypeDescription(HostStorage.class);
        definition.addPropertyParameters("hosts", Host.class);
        constructor.addTypeDescription(definition);
        Yaml yaml = new Yaml(constructor);
        yaml.setBeanAccess(BeanAccess.FIELD);
        try {
            HostStorage load = yaml.load(new FileReader(ApplicationProperties.get("config.hosts.file")));
            return load != null ? load : new HostStorage();
        } catch (FileNotFoundException e) {
            initStorageFile();
            return new HostStorage();
        }
    }

    @Override
    public void save(HostStorage hostStorage) {
        Yaml yaml = new Yaml();
        try (FileWriter fileWriter = new FileWriter(ApplicationProperties.get("config.hosts.file"))){
            yaml.setBeanAccess(BeanAccess.FIELD);
            String dump = yaml.dumpAsMap(hostStorage);
            fileWriter.write(dump);
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void initStorageFile(){
        try {
            new File(ApplicationProperties.get("config.hosts.file")).createNewFile();
        } catch (IOException e) {
            throw new InitializationException("cannot create hosts.yaml", e);
        }
    }

}
