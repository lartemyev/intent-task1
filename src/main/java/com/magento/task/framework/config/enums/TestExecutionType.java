package com.magento.task.framework.config.enums;

import java.util.HashMap;
import java.util.Map;

public enum TestExecutionType {
    LOCAL ("LOCAL"),
    PIPELINE ("PIPELINE");

    private final String value;
    public String getValue() {
        return value;
    }

    TestExecutionType(String value) {
        this.value = value;
    }

    private static final Map<String, TestExecutionType> values = new HashMap<>();

    static {
        for (TestExecutionType e: values()) {
            values.put(e.value, e);
        }
    }
    public static TestExecutionType getTestExecutionType(String value) {
        return values.get(value);
    }
}