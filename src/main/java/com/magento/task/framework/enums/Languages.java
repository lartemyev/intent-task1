package com.magento.task.framework.enums;

import lombok.Getter;
import org.apache.commons.lang3.LocaleUtils;

import java.util.*;

@Getter
public enum Languages {
    EN("en", "en_US", "English"),
    UA("uk", "uk_UA", "Ukrainian");


    private final String code;
    private final String localeName;
    private final String value;

    Languages(String code, String localeName, String value) {
        this.code = code;
        this.localeName = localeName;
        this.value = value;
    }

    private static final Map<String, Languages> ITEMS_BY_CODE = new HashMap<>();

    static {
        supportedLanguages().forEach( l -> ITEMS_BY_CODE.put(l.code, l));
    }

    public static List<Languages> supportedLanguages() {
        return Arrays.stream(values()).toList();
    }

    public static Languages getItemByCodeString(String value) {
        return ITEMS_BY_CODE.get(value);
    }

    public Locale getLocale() {
        return LocaleUtils.toLocale(this.getLocaleName());
    }
}
