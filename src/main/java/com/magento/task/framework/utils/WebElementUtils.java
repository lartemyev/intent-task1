package com.magento.task.framework.utils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import com.magento.task.framework.enums.ElementState;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.assertj.core.api.Assertions.assertThat;
import static com.magento.task.framework.enums.ElementState.NOT_SELECTED;
import static com.magento.task.framework.enums.ElementState.SELECTED;

public class WebElementUtils {
    public static void clearByKeys(SelenideElement element) {
        element.doubleClick();//selects input content
        element.sendKeys(Keys.CONTROL, "a");//selects input content
        element.sendKeys(Keys.SHIFT, Keys.DELETE);
    }

    public static void clickByJS(SelenideElement element) {
        executeJavaScript("arguments[0].click();", element);
    }

    public static SelenideElement getRandomElement(ElementsCollection elements) {
        int randomIndex = NumberUtils.getRandomNumber(0, elements.size());
        Allure.step("Getting random element with index " + randomIndex + " from size " + elements.size());
        return elements.get(randomIndex);
    }

    public static SelenideElement waitForElementToBeLoaded(SelenideElement element, Duration timeout) {
        Selenide.Wait()
                .withMessage("Wait element to be loaded ...")
                .withTimeout(timeout)
                .pollingEvery(Duration.ofSeconds(1))
                .until(d -> element.isDisplayed());
        return element;
    }

    public static boolean waitForElementWithTextToBeLoaded(SelenideElement element, Duration timeout) {
        boolean elementsLoaded = false;
        try {
            Selenide.Wait()
                    .withMessage("Wait element with text to be loaded ...")
                    .withTimeout(timeout)
                    .pollingEvery(Duration.ofSeconds(1))
                    .until(d -> !element.getText().isEmpty());
            elementsLoaded = true;
        } catch (Exception ignored) {

        }
        return elementsLoaded;
    }

    public static void verifyElementAttributeValue(SelenideElement element, String attribute, String attributeValue) {
        assertThat(element.getAttribute(attribute))
                .isEqualTo(attributeValue);
    }

    public static void shouldBeSelected(SelenideElement element, ElementState elementState) {
        if (elementState != SELECTED && elementState != NOT_SELECTED) {
            throw new IllegalArgumentException("Invalid argument: " + elementState + ". Expected ElementState.SELECTED or ElementState.NOT_SELECTED");
        }
        element.shouldBe(visible);
        if (elementState == SELECTED) {
            element.shouldBe(
                    attribute("class", "checked"), Duration.ofMillis(1)
            );
        } else {
            element.should(
                    attributeMatching("class", "not_checked")
            );
        }
    }

    public static boolean isElementSelected(SelenideElement element) {
        try {
            shouldBeSelected(element, SELECTED);
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    public static void replaceFieldTextWithDoubleClick(SelenideElement field, SelenideElement emptyField, String newName) {
        field.doubleClick();
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        field.sendKeys(Keys.BACK_SPACE);
        emptyField.sendKeys(newName);
    }

    public static void verifyElementIsDisplayed(SelenideElement element, String text) {
        assertThat(element.isDisplayed())
                .as(element + ": " + text)
                .isTrue();
    }

    public static void verifyElementIsNotDisplayed(SelenideElement element, String text) {
        assertThat(element.isDisplayed())
                .as(element + ": " + text)
                .isTrue();
    }

    public static void outsideClick() {
        actions().moveByOffset(30, 30).click().perform();
    }

    public static Boolean isVisibleInViewport(SelenideElement element) {
        return executeJavaScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , element);
    }
}
