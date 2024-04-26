package com.magento.task.objects.ui.pages.homepage.items;

import com.magento.task.framework.enums.Languages;

import java.util.HashMap;

import static com.magento.task.framework.enums.Languages.*;

public enum SwitchLanguageMenuItems implements MenuItemEnum {
    ENGLISH("English"),
    UKRAINIAN("Українська");
    private final String item;

    public String getItem() {
        return item;
    }
    SwitchLanguageMenuItems(String item) {
        this.item = item;
    }

    static {
        for (SwitchLanguageMenuItems e: values()) {
            ITEMS.put(e.item, e);
        }
    }

    public static SwitchLanguageMenuItems getMenuItemFromLanguage(Languages language) {
        return localizationMap().get(language);
    }

    private static HashMap<Languages, SwitchLanguageMenuItems> localizationMap() {
        HashMap<Languages, SwitchLanguageMenuItems> map = new HashMap<>();
        map.put(EN, ENGLISH);
        map.put(UA, UKRAINIAN);
        return map;
    }

}
