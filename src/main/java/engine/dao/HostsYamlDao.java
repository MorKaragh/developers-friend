package engine.dao;

import config.ApplicationProperties;
import config.InitializationException;
import model.HostsList;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import model.Host;

import java.io.*;

public class HostsYamlDao {

    public HostsList loadHostsConfig() {
        Constructor constructor = new Constructor(HostsList.class);
        TypeDescription definition = new TypeDescription(HostsList.class);
        definition.addPropertyParameters("hosts", Host.class);
        constructor.addTypeDescription(definition);
        Yaml yaml = new Yaml(constructor);
        yaml.setBeanAccess(BeanAccess.FIELD);
        try {
            HostsList load = yaml.load(new FileReader(ApplicationProperties.get("config.hosts.file")));
            return load != null ? load : new HostsList();
        } catch (FileNotFoundException e) {
            initStorageFile();
            return new HostsList();
        }
    }

    public void saveLocalHostConfig(HostsList hostsList) {
        Yaml yaml = new Yaml();
        try (FileWriter fileWriter = new FileWriter(ApplicationProperties.get("config.hosts.file"))){
            yaml.setBeanAccess(BeanAccess.FIELD);
            String dump = yaml.dumpAsMap(hostsList);
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
