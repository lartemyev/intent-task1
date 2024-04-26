package com.magento.task.framework.listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Label;
import io.qameta.allure.model.TestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
public class AllureTestListener implements TestLifecycleListener {

    private static final Logger log = LoggerFactory.getLogger(AllureTestListener.class);
    private static final String REPORT_DELIMITER = "*".repeat(70);
    private long elapsedTime;

    @Override
    public void beforeTestStart(TestResult result) {
        log.info(REPORT_DELIMITER);
        log.info("[TEST STARTED]: " + result.getTestCaseName());
        if (!result.getParameters().isEmpty()) {
            log.info("[PARAMETERS]: " + result.getParameters());
        }
        if (!getAllureFeature(result.getLabels()).isEmpty()) {
            log.info("[FEATURE]: " + getAllureFeature(result.getLabels()));
        }
        log.info(REPORT_DELIMITER);
        elapsedTime = System.currentTimeMillis();
    }

    public void afterTestStop(TestResult result) {
        log.info(REPORT_DELIMITER);
        log.info("[TEST " + result.getStatus() + "]: " + result.getTestCaseName());
        log.info(REPORT_DELIMITER);
        elapsedTime = System.currentTimeMillis();
    }

    public static String getAllureFeature(List<Label> allureLabels) {
        Optional<Label> optionalLabel = allureLabels.stream()
                .filter(label -> label.getName().equals("feature"))
                .findFirst();
        if (optionalLabel.isPresent()) {
            return optionalLabel.get().getValue();
        }
        return "";
    }
}
