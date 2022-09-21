package it.unibo.trentalode.bot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProvider {
    private static ConfigProvider instance;

    private Properties properties;

    protected ConfigProvider() {
        try {
            FileInputStream propsInput = new FileInputStream("config.properties");
            Properties prop = new Properties();
            prop.load(propsInput);
            this.properties = prop;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigProvider getInstance() {
        if (ConfigProvider.instance == null)
            ConfigProvider.instance = new ConfigProvider();
        return ConfigProvider.instance;
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
}
