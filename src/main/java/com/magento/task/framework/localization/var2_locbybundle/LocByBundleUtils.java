package com.magento.task.framework.localization.var2_locbybundle;

import org.apache.commons.lang3.LocaleUtils;
import com.magento.task.framework.enums.Languages;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.magento.task.framework.config.ConfigurationManager.getTestProductLanguage;
import static com.magento.task.framework.localization.var2_locbybundle.BundleNames.HOW_TO_USE_EXAMPLE;

public class LocByBundleUtils {
    public static String getLocalizationByBundle(String localeCode, String bundleName, String key) {
        Locale locale = LocaleUtils.toLocale(localeCode);
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
        return bundle.getString(key);
    }

    public static String getLocalizationByBundle(Languages language, String bundleName, String key) {
        return getLocalizationByBundle(language.getLocaleName(), bundleName, key);
    }

    public void HOW_TO_USE_EXAMPLE() {
        System.out.println(getLocalizationByBundle(getTestProductLanguage(), HOW_TO_USE_EXAMPLE, "EXAMPLE"));

        Arrays.stream(Languages.values()).forEach(language -> {
            System.out.println(getLocalizationByBundle(language, HOW_TO_USE_EXAMPLE, "EXAMPLE"));
        });

    }
}
