package com.magento.task.framework.enums;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebElementCondition;
import lombok.Getter;

@Getter
public enum ElementState {
    DISPLAYED(Condition.visible, "displayed"),
    NOT_DISPLAYED(Condition.hidden, "not displayed"),
    SELECTED(Condition.selected, "selected"),
    NOT_SELECTED(Condition.not(Condition.selected), "not selected"),
    EXPANDED(Condition.visible, "expanded"),
    NOT_EXPANDED(Condition.visible, "not expanded");

    private final WebElementCondition condition;
    private final String name;

    ElementState(WebElementCondition condition, String name) {
        this.condition = condition;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
