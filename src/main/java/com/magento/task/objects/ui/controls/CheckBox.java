package com.magento.task.objects.ui.controls;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import com.magento.task.framework.utils.WebElementUtils;

import java.util.Objects;

import static com.codeborne.selenide.Condition.enabled;
import static org.assertj.core.api.Assertions.assertThat;

@Data
@SuperBuilder
public class CheckBox {
    private SelenideElement checkBox;
    private String name;

    public CheckBox(SelenideElement inputField) {
        this.checkBox = inputField;
    }

    public CheckBox(SelenideElement inputField, String name) {
        this(inputField);
        this.name = name;
    }

    public SelenideElement getBox() {
        return this.checkBox; //.$x("./span");
    }

    public String getText() {
        return this.getCheckBox().getText();
    }

    public CheckBox scrollTo() {
        getBox().scrollTo();
        return this;
    }

    public boolean isChecked() {
        return WebElementUtils.isElementSelected(getBox());
    }

    public boolean isSelected() {
        return getBox().isSelected();
    }

    public boolean isCheckedByClass(){
        return !Objects.requireNonNull(getBox().getAttribute("class")).contains("not_checked");
    }

    public CheckBox check(Boolean checked) {
        Boolean currentState = isChecked();
        if (!checked.equals(currentState)) {
            this.checkBox.shouldBe(enabled).click();
        }
        assertThat(isChecked())
                .as("CheckBox state should be checked: " + checked)
                .isEqualTo(checked);
        return this;
    }

    public CheckBox checkByClass(Boolean checked) {
        Boolean currentState = isCheckedByClass();
        if (!checked.equals(currentState)) {
            this.checkBox.shouldBe(enabled).click();
        }
        assertThat(isCheckedByClass())
                .as("CheckBox state should be checked: " + checked)
                .isEqualTo(checked);
        return this;
    }
    public CheckBox checkBySelected(Boolean checked) {
        Boolean currentState = isSelected();
        if (!checked.equals(currentState)) {
            this.checkBox.shouldBe(enabled).click();
        }
        assertThat(isSelected())
                .as("CheckBox state should be checked: " + checked)
                .isEqualTo(checked);
        return this;
    }
}
