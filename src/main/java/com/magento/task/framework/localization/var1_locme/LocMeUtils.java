package com.magento.task.framework.localization.var1_locme;

import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

import static com.magento.task.framework.enums.Languages.*;
import static com.magento.task.framework.localization.var1_locme.LocMe.LOCALIZATION_QAA_STRINGS_FILE_NAME;
import static com.magento.task.framework.utils.ClassUtils.getNonFinalFields;


public class LocMeUtils {
    public static void localizeLocMeDialogFields(Object dialogInstance) {
        Class<?> dialogClass = dialogInstance.getClass();
        var fields = getNonFinalFields(dialogClass, LocMe.class);
        for (Field field : fields) {
            LocMe fieldLocMe = getLocMe(dialogClass, field.getName());
            try {
                field.set(dialogInstance, fieldLocMe);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static LocMe getLocMe(Class<?> jsonNodeClass, String jsonFieldName) {
        String enValue = getLnString(jsonNodeClass, jsonFieldName, EN.getLocale());
        String uaValue = getLnString(jsonNodeClass, jsonFieldName, UA.getLocale());
        return new LocMe(enValue, uaValue);
    }

    private static String getLnString(Class<?> dialogClass, String fieldName, Locale locale) {
        String fileName = LOCALIZATION_QAA_STRINGS_FILE_NAME + "_" + locale.toString() + ".json";
        String dialogClassName = dialogClass.getName();

        String lnString = "not localized";

        try (InputStream inputStream = LocMeUtils.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(inputStream);
                JsonNode dialogNode = rootNode.get(dialogClassName);

                if (dialogNode != null) {
                    JsonNode valueNode = dialogNode.at("/" + fieldName);
                    if (!valueNode.isMissingNode()) {
                        lnString = valueNode.asText();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return lnString;
    }
}
