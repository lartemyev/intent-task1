package com.magento.task.framework.localization.var1_locme;

import lombok.Getter;
import com.magento.task.framework.enums.Languages;

import java.util.HashMap;

import static com.magento.task.framework.config.ConfigurationManager.getTestProductLanguage;
import static com.magento.task.framework.enums.Languages.*;

@Getter
public class LocMe {
    public static final String LOCALIZATION_QAA_STRINGS_FILE_NAME = "qaa_strings";
    private final HashMap<Languages, String> map;
    public LocMe(String en, String ua) {
        this.map = this.getLocMap(en, ua);
    }

    public String get() {
        return get(getTestProductLanguage());
    }

    public String get(Languages language) {
        return this.map.get(language);
    }

    private HashMap<Languages, String> getLocMap(String en, String ua) {
        HashMap<Languages, String> locMap = new HashMap<>();
        locMap.put(EN, en);
        locMap.put(UA, ua);
        return locMap;
    }
}
