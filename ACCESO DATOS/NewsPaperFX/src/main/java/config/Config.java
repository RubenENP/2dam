package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.ResourceBundle;

@Getter
@Log4j2
public class Config {
    private static Config config;

    public static Config getInstance(){
        if (config == null)
        {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();

            try {
                config = mapper.readValue(
                        Config.class.getClassLoader().getResourceAsStream("/data/users.yaml"),
                        Config.class);


            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
        return config;
    }

    private String user;
    private String passwd;
}
