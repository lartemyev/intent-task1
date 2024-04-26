package com.magento.task.framework.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class NumberUtils {
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
    public static boolean isEqual(BigDecimal firstPrice, BigDecimal secondPrice) {
        return firstPrice.compareTo(secondPrice) == 0;
    }

    public static boolean isLess(BigDecimal firstNumber, BigDecimal secondNumber) {
        return firstNumber.compareTo(secondNumber) < 0;
    }
    public static boolean isGreater(BigDecimal firstNumber, BigDecimal secondNumber) {
        return firstNumber.compareTo(secondNumber) > 0;
    }

}