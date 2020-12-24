package Test.Automation.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    Properties properties = new Properties();
    InputStream inputStream = null;

    public PropertyReader() {
        loadProperties();
        loadPropertiesConfigEmail();
    }

    private void loadProperties() {
        try {
            inputStream = new FileInputStream("src/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadPropertiesConfigEmail() {
        try {
            inputStream = new FileInputStream("src/configEmailSetup.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String readProperty(String key) {
        return properties.getProperty(key);
    }
    
    public String readPropertyConfigEmail(String key) {
        return properties.getProperty(key);
    }
}