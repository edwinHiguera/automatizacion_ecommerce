package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try {
            // Ajusta la ruta si es necesario
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo cargar el archivo config.properties");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
