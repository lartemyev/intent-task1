package com.magento.task.objects.ui.pages.homepage.items;

import java.util.HashMap;
import java.util.Map;

public interface MenuItemEnum {
    String getItem();

    Map<String, MenuItemEnum> ITEMS = new HashMap<>();

    static MenuItemEnum getItemByString(String value) {
        return ITEMS.get(value);
    }
}
