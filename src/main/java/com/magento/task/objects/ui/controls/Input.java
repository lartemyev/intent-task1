package com.magento.task.objects.ui.controls;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import com.magento.task.framework.utils.WebElementUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static org.assertj.core.api.Assertions.assertThat;
import static com.magento.task.framework.utils.ColorUtils.colorIsRed;

@Data
@SuperBuilder
public class Input {
    private SelenideElement inputField;
    private SelenideElement errorTextElement;
    private String ERROR_TEXT;
    private String name;

    public Input(SelenideElement inputField) {
        this.inputField = inputField;
        this.errorTextElement = inputField;
    }

    public Input(SelenideElement inputField, SelenideElement errorTextElement) {
        this(inputField);
        this.errorTextElement = errorTextElement;
    }

    public Input(SelenideElement inputField, String errorTextElementRelativeXPath) {
        this(inputField);
        this.errorTextElement = inputField.$x(errorTextElementRelativeXPath);
    }

    public Input sendKeys(CharSequence... keysToSend) {
        this.inputField.sendKeys(keysToSend);
        return this;
    }

    public String getErrorTextFromAttribute() {
        return this.errorTextElement.getAttribute("error");
    }

    public String getErrorText() {
        return this.errorTextElement.getText();
    }

    public Input sendKeysWithClear(CharSequence... keysToSend) {
        this.clearByKeys();
        return sendKeys(keysToSend);
    }

    public Input setTextWithClear(CharSequence... keysToSend) {
        this.sendKeysWithClear(keysToSend);
        assertThat(this.getInputField().getAttribute("value")).isEqualTo(keysToSend[0].toString());
        return this;
    }

    public Input setTextWithClearAccordingToMaxLengthValue(CharSequence... keysToSend) {
        this.sendKeysWithClear(keysToSend);

        String maxlengthAttribute = this.getInputField().getAttribute("maxlength");
        int maxLength = (maxlengthAttribute != null) ? Integer.parseInt(maxlengthAttribute) : Integer.MAX_VALUE;

        String expectedText = keysToSend[0].toString();

        if (maxLength < expectedText.length()) {
            this.getInputField().shouldHave(exactText(keysToSend[0].toString().substring(0, maxLength)));
        } else {
            this.getInputField().shouldHave(exactText(keysToSend[0].toString()));
        }
        return this;
    }

    public Input clearTextByKeys() {
        this.clearByKeys();
        this.shouldBe(empty);
        return this;
    }

    public Input clearByKeys() {
        WebElementUtils.clearByKeys(getInputField());
        return this;
    }

    public Input shouldBe(WebElementCondition... var1) {
        this.getInputField().shouldBe(var1);
        return this;
    }

    public Input shouldBe(WebElementCondition var1, Duration var2) {
        this.getInputField().shouldBe(var1, var2);
        return this;
    }

    public boolean isDisplayed() {
        return this.getInputField().isDisplayed();
    }

    public void verifyNoErrorValidationTextDisplayed() {
        assertThat(this.getErrorTextElement().isDisplayed())
                .as("Validation error is not displayed")
                .isFalse();
    }

    public void verifyErrorValidationText(String validationErrorText) {
        this.getErrorTextElement()
                .as("Validation error test is: " + validationErrorText)
                .shouldHave(text(validationErrorText));
        assertThat(colorIsRed(this.getErrorTextElement()))
                .isTrue();
    }

    public void verifyInputFieldText(String text) {
        this.getInputField()
                .as("Input field text is: " + text)
                .shouldHave(text(text));
    }

    public void replaceFieldTextWithDoubleClick(Input emptyField, String newName) {
        WebElementUtils.replaceFieldTextWithDoubleClick(this.getInputField(), emptyField.getInputField(), newName);
    }

    public String getTypeAttribute(){
        return getInputField().getAttribute("type");
    }

    public String getInputFieldValue(){
        return getInputField().getAttribute("value");
    }
}
