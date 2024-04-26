package com.magento.tests.ui;

import com.codeborne.selenide.Selenide;
import com.magento.task.steps.ui.StepsUi;
import com.magento.tests.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

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
