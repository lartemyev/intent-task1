package com.magento.task.framework.config.enums;

public enum Browser {
    CHROME("chrome"),
    FIREFOX_CUSTOM_AGENT("firefox"),
    CHROME_NO_COOKIES("chrome");

    private final String browserName;

    Browser(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }
}
