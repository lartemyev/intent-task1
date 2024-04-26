package com.magento.task.objects.ui.controls;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DropdownWithNameAttribute {

    private final String name;

    public DropdownWithNameAttribute(String name) {
        this.name = name;
    }

    public void selectOption(String option) {
        SelenideElement element = $(String.format("[name=%s]", name)).parent().parent();
        element.click();
        element.$$(".selector-options__option")
                .asDynamicIterable()
                .stream().filter(el -> el.getText().equals(option))
                .findFirst().orElseThrow()
                .click();
    }

}