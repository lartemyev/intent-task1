package com.magento.tests;

import io.qameta.allure.model.TestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);



    protected static int stepNumber;

    public static void TestSTEP(String stepText) {
        String text = "[STEP " + stepNumber++ + "]: " + stepText;
        log.info("");
        log.info(text);
    }

    public static void TestSTART_Info(TestResult result) {

    }

    public static void TestSTEP(int fromStep, int toStep, String stepText) {
        String text = "[STEP " + fromStep + "-" + toStep + "]: " + stepText;
        stepNumber = ++toStep;
        log.info("");
        log.info(text);
    }

    protected static void ResetSTEPs() {
        stepNumber = 1;
    }

    public static void LogInfo(String text) {
        log.info(text);
    }

}
