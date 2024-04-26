package com.magento.task.framework.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.ConfigFactory;
import com.magento.task.framework.config.enums.TestExecutionType;
import com.magento.task.framework.enums.Languages;

import static com.magento.task.framework.config.Configuration.TEST_PRODUCT_LANGUAGE_SETTING;

public class ConfigurationManager {
    private ConfigurationManager() {
    }

    public static Configuration configuration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }

    public static boolean getBrowserHeadless() {
        return !configuration().testExecutionType().equals(TestExecutionType.LOCAL);
    }

    public static Configuration configurationRefresh() {
        ConfigCache.clear();
        ConfigFactory.clearProperty(TEST_PRODUCT_LANGUAGE_SETTING);
        return configuration();
    }

    public static Languages getTestProductLanguage() {
        Languages lang = configuration().testProductLanguage();
        String setting = ConfigFactory.getProperty(TEST_PRODUCT_LANGUAGE_SETTING);
        return setting == null ? lang : Languages.valueOf(setting);
    }

    public static void setTestProductLanguage(Languages language) {
        configurationSetProperty(TEST_PRODUCT_LANGUAGE_SETTING, language.name());
    }

    public static String getEnvironmentURL() {
        return configuration().envUrl();
    }
    public static String getEnvironmentURL2() {
        return configuration().envUrl2();
    }

    public static long getSelenideTimeout() {
        return configuration().testSelenideTimeout();
    }

    static void configurationSetProperty(String key, String value) {
        ConfigFactory.setProperty(key, value);
    }
}
