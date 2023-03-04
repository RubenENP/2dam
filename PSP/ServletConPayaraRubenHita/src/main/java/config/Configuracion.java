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
public class Configuracion {
    private String ruta;
    private String user;
    private String password;
    private String driver;

    void cargar(InputStream file) {
        try {
            Yaml yaml = new Yaml();

            Iterable<Object> it;

            it = yaml.loadAll(file);

            Map<String, String> m = (Map) it.iterator().next();
            this.ruta = m.get("ruta");
            this.password = m.get("password");
            this.user = m.get("user");
            this.driver = m.get("driver");
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
