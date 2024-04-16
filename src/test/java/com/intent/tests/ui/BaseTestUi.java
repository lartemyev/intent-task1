package com.intent.tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.intent.task.framework.utils.BrowserUtil;
import com.intent.task.steps.ui.BaseStepsUi;
import com.intent.task.steps.ui.StepsUi;
import com.intent.task.steps.ui.proxy.ProxySteps;
import io.qameta.allure.model.TestResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import com.intent.tests.BaseTest;

import static com.intent.task.framework.config.ConfigurationManager.*;

public class BaseTestUi extends BaseTest implements StepsUi {

    @BeforeAll
    public static void before() {

    }

    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void afterEach() {
        Selenide.closeWebDriver();
    }
}
