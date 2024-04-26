package com.magento.task.framework.config;

import org.aeonbits.owner.Config;
import com.magento.task.framework.config.enums.Environment;
import com.magento.task.framework.config.enums.TestExecutionType;
import com.magento.task.framework.enums.Languages;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "classpath:general.properties"})
public interface Configuration extends Config {
    String TEST_PRODUCT_LANGUAGE_SETTING = "test.product.language";

    @Key("env.url")
    String envUrl();

    @Key("env.url2")
    String envUrl2();

    @Key(TEST_PRODUCT_LANGUAGE_SETTING)
    Languages testProductLanguage();

    @Key("test.selenide.timeout")
    long testSelenideTimeout();

    @Key("test.execution.type")
    TestExecutionType testExecutionType();

    @Key("use.native.selenium.waits")
    boolean useNativeSeleniumWaits();
}