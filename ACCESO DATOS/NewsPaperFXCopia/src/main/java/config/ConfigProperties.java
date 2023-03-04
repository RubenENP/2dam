package config;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Log4j2
public class ConfigProperties {
    private static ConfigProperties instance = null;
    private Properties p;

    public ConfigProperties(){
        try {
            p = new Properties();
            p.load(ConfigProperties.class.getClassLoader().getResourceAsStream("properties.txt"));
        }catch (IOException e){
            log.error(e.getMessage());
        }
    }

    public static ConfigProperties getInstance(){
        if (instance == null){
            instance = new ConfigProperties();
        }
        return instance;
    }

    public String getProperty(String key){return p.getProperty(key);}
}
