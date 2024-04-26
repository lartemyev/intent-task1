package com.magento.task.framework.config;

import com.codeborne.selenide.Selenide;
import com.magento.task.framework.config.enums.Browser;
import com.magento.task.framework.utils.BrowserUtil;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.magento.task.framework.config.ConfigurationManager.*;

public class ConfigurationBrowser {
    public ConfigurationBrowser() {
    }

    public ConfigurationBrowser configure(Browser browser) {
        Configuration.timeout = getSelenideTimeout();
        Configuration.headless = getBrowserHeadless();

        //ProxySteps.enableAndSetProxy();

        switch (browser) {
            case CHROME -> {
                Configuration.browser = browser.getBrowserName();
                Configuration.baseUrl = getEnvironmentURL();
            }

            case FIREFOX_CUSTOM_AGENT -> {
                String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 16_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.3 Mobile/15E148 Safari/604.1";

                // Configure Selenide to use Firefox
                Configuration.browser = browser.getBrowserName();
                Configuration.baseUrl = getEnvironmentURL2();

                // Configure Firefox options
                Configuration.browserCapabilities.setCapability("userAgent", userAgent);
            }

            case CHROME_NO_COOKIES -> {
                Configuration.browser = browser.getBrowserName();
                ChromeOptions chromeOptions = new ChromeOptions();
                // Add option to reject third-party cookies
                chromeOptions.addArguments("--disable-third-party-cookies");
                // Set Chrome options
                Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            }
            default -> throw new RuntimeException("Unknown test execution type");
        }
        return this;
    }

    public void openBaseUrl() {
        Selenide.open(Configuration.baseUrl);
        BrowserUtil.maximiseBrowserWindow();
    }

    public void open(String url) {
        Selenide.open(url);
        BrowserUtil.maximiseBrowserWindow();
    }
}
