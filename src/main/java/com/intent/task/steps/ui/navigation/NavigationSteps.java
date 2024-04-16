package com.intent.task.steps.ui.navigation;

import com.intent.task.steps.ui.BaseStepsUi;
import com.intent.task.steps.ui.bagspage.BagsSteps;
import com.intent.task.steps.ui.homepage.HomeSteps;
import io.qameta.allure.Step;

import static com.intent.task.framework.utils.BrowserUtil.openPage;
import static com.intent.task.objects.ui.pages.PagePath.*;

public class NavigationSteps extends BaseStepsUi {

    @Step("Navigate to Login page")
    public HomeSteps navigateToHomePage() {
        openPage(HOME);
        return homeSteps.waitForPageToBeOpened();
    }

    @Step("Navigate to Login page")
    public BagsSteps navigateToBagsPage() {
        openPage(BAGSPAGE);
        return bagsSteps.waitForPageToBeOpened();
    }

}