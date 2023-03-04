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

    public static final String PRIMERA_LLAMADA_FECHA = "primeraLlamadaFecha";
    private String ruta;
    private String user;
    private String password;
    private String driver;
    private int tiempo;
    private int numeroLlamadas;

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
            this.tiempo = /*Integer.parseInt(m.get(ConfigConstantes.TIEMPO));*/ 120;
            this.numeroLlamadas = /*Integer.parseInt(m.get(ConfigConstantes.NUMERO_LLAMADAS));*/ 5;

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
