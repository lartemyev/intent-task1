package com.magento.task.steps.ui.navigation;

import com.magento.task.steps.ui.BaseStepsUi;
import com.magento.task.steps.ui.bagspage.BagsSteps;
import com.magento.task.steps.ui.homepage.HomeSteps;
import io.qameta.allure.Step;

import static com.magento.task.framework.utils.BrowserUtil.openPage;
import static com.magento.task.objects.ui.pages.PagePath.*;

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