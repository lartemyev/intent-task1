package com.magento.task.objects.ui.pages;

import lombok.Getter;

public enum PagePath {
    HOME(""),
    BAGSPAGE("/gear/bags.html");

    @Getter
    private final String key;
    PagePath(String key) {
        this.key = key;
    }
}
