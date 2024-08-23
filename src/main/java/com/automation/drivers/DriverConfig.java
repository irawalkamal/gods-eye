package com.automation.drivers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DriverConfig {

    private static final String CONFIG_FILE = "driver-config.properties";

    public static Properties loadConfig() {
        Properties properties = new Properties();
        try (InputStream input = DriverConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Unable to find " + CONFIG_FILE);
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
}
