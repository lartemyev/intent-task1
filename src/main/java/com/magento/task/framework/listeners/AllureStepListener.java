package com.magento.task.framework.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class AllureStepListener implements StepLifecycleListener {

    private static final Logger log = LoggerFactory.getLogger(AllureStepListener.class);
    @Override
    public void beforeStepStart(StepResult stepResult){
        Allure.step("Test STEP: " + stepResult.getName());
    }
}
