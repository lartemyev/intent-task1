package com.magento.task.framework.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClassUtils {
    public static List<Field> getNonFinalFields(Class<?> clazz, Class<?> targetVariableClazz) {
        List<Field> nonFinalStringFields = new ArrayList<>();

        // Get all fields declared in the class (including private ones)
        Field[] allFields = clazz.getDeclaredFields();

        // Filter non-final String fields
        for (Field field : allFields) {
            if (!java.lang.reflect.Modifier.isFinal(field.getModifiers()) &&
                    field.getType().equals(targetVariableClazz)) {
                nonFinalStringFields.add(field);
            }
        }

        return nonFinalStringFields;
    }
}
