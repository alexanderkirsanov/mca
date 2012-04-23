package ru.kirsanov.mca.other.dao;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationManager {
    public ConfigurationManager() {
        configurationFile = new File(configurationFileName);
    }

    public Properties loadProperties() {
        Properties properties = new Properties();
        if (configurationFile.exists()) {
            if (configurationFile.canRead()) {
                try {
                    FileInputStream configurationFileInputStream = new FileInputStream(
                            configurationFile);
                    try {
                        properties.load(configurationFileInputStream);
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, e.toString());
                    } finally {
                        configurationFileInputStream.close();
                    }
                } catch (Exception e) {
                    logger.log(Level.SEVERE, e.toString());
                }
            }
        }
        return properties;
    }

    public boolean saveProperties(Properties properties) {
        try {
            FileOutputStream configurationFileOutputStream = new FileOutputStream(
                    configurationFile);
            try {
                properties.store(configurationFileOutputStream, null);
                return true;
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.toString());
            } finally {
                configurationFileOutputStream.close();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return false;
    }

    private static final String configurationFileName = "config";
    private Logger logger = Logger
            .getLogger("ru.kirsanov.youngusurer.src.configuration");
    private File configurationFile;

}
