package com.magento.task.steps.ui.homepage;

import com.magento.task.objects.ui.pages.homepage.HomePage;
import com.magento.task.steps.ui.BaseStepsUi;
import com.magento.task.steps.ui.bagspage.BagsSteps;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@EqualsAndHashCode(callSuper = true)
@Data
public class HomeSteps extends BaseStepsUi {
    private final HomePage page = new HomePage();
    @Step(HomePage.NAME + ": Wait for page to be opened ...")
    public HomeSteps waitForPageToBeOpened() {
        page.getPageId().shouldBe(visible, Duration.of(10, ChronoUnit.SECONDS));
        return this;
    }
    @Step(HomePage.NAME + ": Navigate to Bags page")
    public BagsSteps navigateToBagsPage() {
        page.getGearMenu().selectMenuItemByText(page.GEAR_MENU_BAGS_MENU_ITEM.get());
        return bagsSteps.waitForPageToBeOpened();
    }
}