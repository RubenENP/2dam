package config;

import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Singleton
@Getter
@Log4j2
public class Configuration {
    private String url;
    private String user;
    private String password;
    private String driver;

    public Configuration() {
        try {
            Properties p = new Properties();
            p.load(getClass().getResourceAsStream("/properties.yaml"));
            url = (String) p.get("urlDB");
            user = (String) p.get("user_name");
            password = (String) p.get("password");
            driver = (String) p.get("driver");
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }

}