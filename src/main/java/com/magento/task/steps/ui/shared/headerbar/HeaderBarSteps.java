package com.magento.task.steps.ui.shared.headerbar;

import com.codeborne.selenide.Selenide;
import com.magento.task.objects.ui.pages.shared.headerbar.HeaderBarPage;
import com.magento.task.steps.ui.BaseStepsUi;
import com.magento.task.steps.ui.checkout.Step1ShippingAddressSteps;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.magento.task.framework.utils.WebElementUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@EqualsAndHashCode(callSuper = true)
@Data
public class HeaderBarSteps extends BaseStepsUi {

    private final HeaderBarPage page = new HeaderBarPage();
    @Step(HeaderBarPage.NAME + ": Verify cart items is - '{cartItems}'")
    public HeaderBarSteps verifyCartItemsAmount(int cartItems) {
        assertThat(page
                .getCartItemsAmount())
                .as("Cart items amount: " + cartItems)
                .isEqualTo(cartItems);
        return this;
    }

    @Step(HeaderBarPage.NAME + ": Verify cart items amount icon")
    public HeaderBarSteps verifyCartItemsAmountIcon(int cartItems) {
        if (cartItems == 0) {
            verifyElementIsNotDisplayed(page.getCounterQuantityElement(), "Counter element is not displayed");
        } else {
            verifyElementIsDisplayed(page.getCounterQuantityElement(), "Counter element is displayed");
        }
        return this;
    }

    @Step(HeaderBarPage.NAME + ": Click cart icon and proceed to Checkout")
    public Step1ShippingAddressSteps clickCartIconAndProceedToCheckout() {
        page
                .getCounterQuantityElement()
                .scrollTo()
                .click();
        Selenide.sleep(1*1000); // page to be loaded
        waitForElementToBeLoaded(page
                .getProceedToCheckoutButton(), Duration.ofSeconds(5))
                .click();
        return step1ShippingAddressSteps;
    }

    @Step(HeaderBarPage.NAME + ": Wait for loading is completed")
    public HeaderBarSteps waitForLoadingIsCompleted() {
        int loadingTimeoutSeconds = 45;
        headerBarPage
                .getPageLoader()
                .shouldBe(not(visible), Duration.ofSeconds(loadingTimeoutSeconds));
        return this;
    }
}