package com.magento.task.framework.config.enums;

import java.util.HashMap;
import java.util.Map;

public enum Environment {
    MAIN("main");
    private final String value;
    public String getValue() {
        return value;
    }

    Environment(String value) {
        this.value = value;
    }

    private static final Map<String, Environment> ENV_NAMES = new HashMap<>();

    static {
        for (Environment e: values()) {
            ENV_NAMES.put(e.value, e);
        }
    }
    public static Environment getEnvironment(String value) {
        return ENV_NAMES.get(value);
    }
}
