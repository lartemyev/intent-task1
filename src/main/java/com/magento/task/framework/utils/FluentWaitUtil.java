package com.magento.task.framework.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class FluentWaitUtil {

    private final SelenideElement element;
    private final WebDriverWait wait;

    private FluentWaitUtil(SelenideElement element, Duration timeout) {
        this.element = element;
        this.wait = new WebDriverWait(Selenide.webdriver().driver().getWebDriver(), timeout);
    }

    public static FluentWaitUtil forElement(SelenideElement element, Duration timeout) {
        return new FluentWaitUtil(element, timeout);
    }

    public static FluentWaitUtil withoutElement(Duration timeout) {
        return new FluentWaitUtil(null, timeout);
    }

    public FluentWaitUtil withMessage(String message) {
        wait.withMessage(message);
        return this;
    }

    public FluentWaitUtil withPollingInterval(Duration pollingInterval) {
        wait.pollingEvery(pollingInterval);
        return this;
    }

    public FluentWaitUtil until(Function<SelenideElement, ?> condition) {
        if (element != null) {
            wait.until(driver -> condition.apply(element));
        }
        return this;
    }

    public FluentWaitUtil untilWithoutElement(Function<WebDriver, ?> condition) {
        if (element != null) {
            throw new IllegalStateException("This method is intended for conditions without a specific element.");
        }
        wait.until(driver -> condition.apply(driver));
        return this;
    }
}

