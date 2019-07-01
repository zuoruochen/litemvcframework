package com.zrc.configuration;

import com.zrc.util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ConfigureParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigureParser.class);
    private static final Properties CONFIG_PROPS = PropertyUtil.loadProps(ConfigureConstant.LITE_PROPERTIES);

    public static String getJdbcDriver() {
        return PropertyUtil.getValue(CONFIG_PROPS, ConfigureConstant.JDBC_DRIVER);
    }

    public static String getJdbcUrl() {
        return PropertyUtil.getValue(CONFIG_PROPS, ConfigureConstant.JDBC_URL);
    }

    public static String getJdbcUsername() {
        return PropertyUtil.getValue(CONFIG_PROPS, ConfigureConstant.JDBC_USERNAME);
    }

    public static String getJdbcPassword() {
        return PropertyUtil.getValue(CONFIG_PROPS, ConfigureConstant.JDBC_PASSWORD);
    }

    public static String getAppBasePackage() {
        return PropertyUtil.getValue(CONFIG_PROPS, ConfigureConstant.APP_BASE_PACKAGE);
    }

    public static String getAppAsset() {
        return PropertyUtil.getValue(CONFIG_PROPS, ConfigureConstant.APP_ASSET_PATH, "/asset/");
    }
}
