package com.magento.task.framework.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import com.magento.task.objects.ui.pages.PagePath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.magento.task.framework.config.ConfigurationManager.configuration;

public class BrowserUtil {

    @Step("Open page {path.key}")
    public static void openPage(PagePath path) {
        open(path.getKey());
    }

    public static void waitForPageToBeLoadedJS() {
        if (!configuration().useNativeSeleniumWaits()) {
            Wait().withMessage("Waiting for document ready ...")
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(2))
                    .until(d ->
                            Objects.equals(executeJavaScript("return document.readyState"), "complete")
                    );
        } else {
            FluentWaitUtil
                    .withoutElement(Duration.ofSeconds(10))
                    .withMessage("Waiting for document ready ...")
                    .withPollingInterval(Duration.ofSeconds(2))
                    .untilWithoutElement(d -> Objects.equals(executeJavaScript("return document.readyState"), "complete"));
        }
    }
    public static void maximiseBrowserWindow() {
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
    public static void refreshPage() {
        refresh();
    }
}