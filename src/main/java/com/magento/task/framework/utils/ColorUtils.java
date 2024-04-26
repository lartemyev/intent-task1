package com.magento.task.framework.utils;

import com.codeborne.selenide.SelenideElement;

import java.awt.*;

public class ColorUtils {

    public static String getColor(SelenideElement element) {
        return element.getCssValue("color");
    }

    public static Color getRGB(String rgbaString) {
        String values;
        if (rgbaString.contains("rgba")) {
            values = rgbaString.replace("rgba(", "").replace(")", "");
        } else {
            values = rgbaString.replace("rgb(", "").replace(")", "");
        }
        String[] rgba = values.split(", ");
        int r = Integer.parseInt(rgba[0]);
        int g = Integer.parseInt(rgba[1]);
        int b = Integer.parseInt(rgba[2]);
        return new Color(r, g, b);
    }

    public static Boolean colorIsRed(String colorValueFromCss) {
        Color rgb = getRGB(colorValueFromCss);
        return rgb.getRed() == 245 &&
                rgb.getGreen() == 91 &&
                rgb.getBlue() == 56;
    }

    public static Boolean colorIsRed(SelenideElement element) {
        return colorIsRed(getColor(element));
    }
}
