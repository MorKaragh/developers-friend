package config;

import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;

public class ApplicationProperties {

    static ApplicationProperties instance;
    private final Properties prop;

    private ApplicationProperties() {
        System.out.println();
        prop = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResource("application.properties")).getFile())){
            prop.load(fileInputStream);
        } catch (Exception e) {
            throw new InitializationException("unable to load .properties file");
        }
    }

    synchronized public static ApplicationProperties getInstance(){
        if (instance == null) {
            instance = new ApplicationProperties();
        }
        return instance;
    }

    public String get(String s) {
        return prop.getProperty(s);
    }
}