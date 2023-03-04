package config;

import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

@Getter
@Log4j2
@Singleton
public class Configuration {

    private String ruta;
    private String user;
    private String password;
    private String driver;

    private Configuration(){
        try {
            Yaml yaml = new Yaml();

            Iterable<Object> it;

            it = yaml.loadAll(getClass().getResourceAsStream(ConfigConstantes.DATA_CONFIG_YAML));

            Map<String, String> m = (Map) it.iterator().next();
            this.ruta = m.get(ConfigConstantes.RUTA);
            this.password = m.get(ConfigConstantes.PASSWORD);
            this.user = m.get(ConfigConstantes.USER);
            this.driver = m.get(ConfigConstantes.DRIVER);


        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
