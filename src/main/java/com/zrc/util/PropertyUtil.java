package com.zrc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(Properties.class);

    public static Properties loadProps(String fileName) {
        InputStream in = null;
        Properties props = null;
        try {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (in == null) {
                throw new FileNotFoundException(fileName + " file is not exist");
            }
            props = new Properties();
            props.load(in);
        } catch (Exception ex) {
            LOGGER.error("load properties failed", ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    LOGGER.error("close input stream faled", e);
                }
            }
        }
        return props;
    }

    public static String getValue(Properties props, String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static String getValue(Properties props, String key) {
        return props.getProperty(key);
    }
}
