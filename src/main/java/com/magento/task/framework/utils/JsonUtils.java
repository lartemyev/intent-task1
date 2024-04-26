package com.magento.task.framework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonUtils {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    /**
     * Create Object from JSON String
     * @param classOfT
     * @param jsonString
     * @param <T>
     * @return
     */
    public static <T> T toObjectFromJson(Class<T> classOfT, String jsonString) {
        return gson.fromJson(jsonString, classOfT);
    }

    public static <T> ArrayList<T> toObjectArrayFromJson(Type typeOf, String jsonString) {
        return gson.fromJson(jsonString, typeOf);
    }
}