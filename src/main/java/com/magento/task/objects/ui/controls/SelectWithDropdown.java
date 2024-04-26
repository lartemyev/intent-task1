package com.magento.task.objects.ui.controls;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;
import lombok.NonNull;
import com.magento.task.framework.utils.FluentWaitUtil;
import com.magento.task.framework.utils.WebElementUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.magento.task.framework.config.ConfigurationManager.configuration;

@Data
public class SelectWithDropdown {
    @NonNull
    private SelenideElement selectElement;
    @NotNull
    private ElementsCollection listItems;

    public SelectWithDropdown(SelenideElement selectElement, ElementsCollection listItems) {
        this.selectElement = selectElement;
        this.listItems = listItems;
    }

    public SelectWithDropdown shouldBe(WebElementCondition var1, Duration var2) {
        getSelectElement().shouldBe(var1, var2);
        return this;
    }

    public SelectWithDropdown(SelenideElement containerElement) {
        this.selectElement = containerElement;
        this.listItems = containerElement.$$x(".//*[contains(local-name(), 'option')]");
    }

    private SelectWithDropdown openList() {
        this.getSelectElement()
                .shouldBe(enabled, Duration.ofSeconds(10)).click();
        if (!configuration().useNativeSeleniumWaits()) {
            Selenide.Wait()
                    .withMessage("Loading menu items ...")
                    .withTimeout(Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofSeconds(1))
                    .until(d -> !getListItems().isEmpty());
        } else {
            FluentWaitUtil
                    .withoutElement(Duration.ofSeconds(15))
                    .withMessage("Loading menu items ...")
                    .withPollingInterval(Duration.ofSeconds(1))
                    .untilWithoutElement(d -> !getListItems().isEmpty());
        }
        return this;
    }

    public SelenideElement selectItemText(String menuItemText) {
        return openList()
                .getListItems()
                .filterBy(matchText(menuItemText))
                .first()
                .shouldBe(visible, enabled)
                .should(interactable, Duration.ofSeconds(2));
    }

    public void selectItemByText(String menuItemText) {
        selectItemText(menuItemText).click();
    }

    public void selectItemByTypeText(String menuItemText) {
        getSelectElement().scrollTo()
                .shouldBe(visible, enabled)
                .should(interactable, Duration.ofSeconds(2))
                .sendKeys(menuItemText);
    }

    public boolean isDisplayed() {
        return getSelectElement().isDisplayed();
    }

    public SelectWithDropdown selectRandomItem() {
        WebElementUtils
                .getRandomElement(openList()
                        .getListItems())
                .shouldBe(visible, enabled)
                .should(interactable, Duration.ofSeconds(2))
                .click();
        return this;
    }
}