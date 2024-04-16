package com.intent.task.framework.utils;

import org.apache.commons.lang3.LocaleUtils;

import java.util.Locale;

import static com.intent.task.framework.config.ConfigurationManager.getTestProductLanguage;

public class DateTimeUtils {
    public static final Locale ENVIRONMENT_LOCALE = LocaleUtils.toLocale(getTestProductLanguage().getLocaleName());

}
