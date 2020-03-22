package dao;

import config.ApplicationProperties;
import config.InitializationException;
import config.local.LocalHostsConfig;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import model.RemoteHost;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RemotesDao {


    public List<RemoteHost> loadStoredHosts(){
        return new ArrayList<>();
    }

    public LocalHostsConfig loadHostsConfig() {
        Constructor constructor = new Constructor(LocalHostsConfig.class);
        TypeDescription definition = new TypeDescription(LocalHostsConfig.class);
        definition.addPropertyParameters("hosts",RemoteHost.class);
        constructor.addTypeDescription(definition);
        Yaml yaml = new Yaml(constructor);
        yaml.setBeanAccess(BeanAccess.FIELD);
        try {
            LocalHostsConfig load = yaml.load(new FileReader(ApplicationProperties.get("config.hosts.file")));
            return load != null ? load : new LocalHostsConfig();
        } catch (FileNotFoundException e) {
            initStorageFile();
            return new LocalHostsConfig();
        }
    }

    public void saveLocalHostConfig(LocalHostsConfig localHostsConfig) {
        Yaml yaml = new Yaml();
        try (FileWriter fileWriter = new FileWriter(ApplicationProperties.get("config.hosts.file"))){
            yaml.setBeanAccess(BeanAccess.FIELD);
            String dump = yaml.dumpAsMap(localHostsConfig);
            fileWriter.write(dump);
        } catch (IOException e) {
            throw new DaoFailed(e);
        }
    }

    private void initStorageFile(){
        try {
            new File(ApplicationProperties.get("config.hosts.file")).createNewFile();
        } catch (IOException e) {
            throw new InitializationException("cannot create hosts.yaml", e);
        }
    }

}
