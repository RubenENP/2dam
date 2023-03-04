package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Getter
@Log4j2
public class ConfigYaml {
    private static ConfigYaml config;

    public static ConfigYaml getInstance(){
        if (config == null)
        {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();

            try {
                config = mapper.readValue(
                        ConfigYaml.class.getClassLoader().getResourceAsStream("admin.yaml"),
                        ConfigYaml.class);


            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
        return config;
    }

    private String user;
    private String passwd;
}
