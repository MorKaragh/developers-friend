package config;

import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;

public class ApplicationProperties {

    static ApplicationProperties instance;
    private final Properties prop;

    private ApplicationProperties() {
        prop = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(
                Objects.requireNonNull(getClass().getClassLoader()
                        .getResource("application.properties")).getFile())){
            prop.load(fileInputStream);
        } catch (Exception e) {
            throw new InitializationException("unable to load .properties file", e);
        }
    }

    synchronized public static ApplicationProperties getInstance(){
        if (instance == null) {
            instance = new ApplicationProperties();
        }
        return instance;
    }

    public static double getDouble(String s) {
        return Double.parseDouble(get(s));
    }

    public static String get(String s) {
        return getInstance().prop.getProperty(s);
    }

}
