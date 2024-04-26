package com.magento.task.objects.ui.controls;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;
import lombok.NonNull;
import com.magento.task.framework.utils.FluentWaitUtil;
import com.magento.task.objects.ui.pages.homepage.items.MenuItemEnum;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.magento.task.framework.config.ConfigurationManager.configuration;

@Data
public class InputWithDropdown {
    @NonNull
    private SelenideElement inputElement;
    @NotNull
    private ElementsCollection listItems;

    public InputWithDropdown(SelenideElement inputElement, ElementsCollection listItems) {
        this.inputElement = inputElement;
        this.listItems = listItems;
    }

    public InputWithDropdown shouldBe(WebElementCondition var1, Duration var2) {
        getInputElement().shouldBe(var1, var2);
        return this;
    }

    public InputWithDropdown(SelenideElement containerElement) {
        this.inputElement = containerElement.$x(".//input");
        this.listItems = containerElement.$$x(".//*[contains(@class, 'option')]");
    }

    private InputWithDropdown openList() {
        this.getInputElement()
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

    public void selectItemByTypeText(MenuItemEnum menuItem) {
        selectItemByTypeText(menuItem.getItem());
    }

    public InputWithDropdown sendKeys(CharSequence... keysToSend) {
        getInputElement().sendKeys(keysToSend);
        return this;
    }

    public SelenideElement typeItemText(String menuItemText) {
        return typeItemTextAndSelectValue(menuItemText, menuItemText);
    }

    public SelenideElement typeItemTextAndSelectValue(String itemText, String selectValue) {
        return openList()
                .sendKeys(itemText)
                .getListItems()
                .filterBy(exactText(selectValue))
                .first()
                .shouldBe(visible, enabled)
                .should(interactable, Duration.ofSeconds(5));
    }

    public void selectItemByTypeText(String menuItemText) {
        typeItemText(menuItemText).shouldBe(interactable, Duration.ofSeconds(10)).click();
    }

    public void selectItemByTypeText(String menuItemText, String itemValue) {
        typeItemTextAndSelectValue(menuItemText, itemValue).shouldBe(interactable, Duration.ofSeconds(10)).click();
    }

    public boolean isDisplayed() {
        return getInputElement().isDisplayed();
    }
}