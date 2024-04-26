package com.magento.task.steps.ui.checkout;

import com.codeborne.selenide.Selenide;
import com.magento.task.framework.utils.WebElementUtils;
import com.magento.task.models.UserModel;
import com.magento.task.objects.api.ShippingInformationRequest;
import com.magento.task.objects.api.items.AddressInfoItem;
import com.magento.task.objects.ui.pages.checkout.Step2PaymentMethodPage;
import com.magento.task.steps.ui.BaseStepsUi;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.magento.task.framework.utils.BrowserUtil.refreshPage;
import static com.magento.task.framework.utils.BrowserUtil.waitForPageToBeLoadedJS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@EqualsAndHashCode(callSuper = true)
@Data
public class Step2PaymentMethodSteps extends BaseStepsUi {
    private final Step2PaymentMethodPage page = new Step2PaymentMethodPage();
    @Step(Step2PaymentMethodPage.NAME + ": Wait for page to be opened ...")
    public Step2PaymentMethodSteps waitForPageToBeOpened() {
        page.getPageId()
                .shouldBe(visible, Duration.of(15, ChronoUnit.SECONDS));
        headerBarSteps
                .waitForLoadingIsCompleted();
        return this;
    }

    @Step(Step2PaymentMethodPage.NAME + ": Verify page title")
    public Step2PaymentMethodSteps verifyPageTitle() {
        page.getPageId()
                .shouldHave(text(page.PAGE_TITLE_TEXT.get()));
        return this;
    }
    @Step(Step2PaymentMethodPage.NAME + ": Click 'Place Order' button")
    public Step3ThankYouSteps clickPlaceOrderButton() {
        WebElementUtils.clickByJS(page
                .getPlaceOrderButton()
                .scrollTo());
        return step3ThankYouSteps;
    }

    @Step(Step2PaymentMethodPage.NAME + ": Click 'Place Order' button")
    public Step3ThankYouSteps clickPlaceOrderButtonAndWaitForNextPage() {
        try { // this page doesn't work as expected. This likely be addressed in normal dev process
            tryClickPlaceOrderButton();
        } catch (AssertionError e) {
            try {
                reTryClickPlaceOrderButton();
            } catch (AssertionError e1) {
                reTryClickPlaceOrderButton();
            }
        }
        return step3ThankYouSteps;
    }

    private void tryClickPlaceOrderButton() {
        clickPlaceOrderButton()
                .waitForPageToBeOpened()
                .verifyPageTitle();
    }

    private void reTryClickPlaceOrderButton() {
        refreshPage();
        waitForPageToBeLoadedJS();
        waitForPageToBeOpened();
        Selenide.sleep(3*1000);
        tryClickPlaceOrderButton();
    }

    @Step(Step2PaymentMethodPage.NAME + ": Click 'Place Order' button")
    public Step2PaymentMethodSteps verifyShippingInformationRequest(ShippingInformationRequest actualShippingInformation, UserModel expectedUserModel) {
        verifyAddressInfoItem(actualShippingInformation.getAddressInformation().getShippingAddress(), expectedUserModel)
                .verifyAddressInfoItem(actualShippingInformation.getAddressInformation().getBillingAddress(), expectedUserModel);
        return this;
    }

    private Step2PaymentMethodSteps verifyAddressInfoItem(AddressInfoItem addressInfoItem, UserModel expectedUserModel) {
        assertThat(addressInfoItem.getFirstname()).isEqualTo(expectedUserModel.getFirstName());
        assertThat(addressInfoItem.getLastname()).isEqualTo(expectedUserModel.getLastName());
        assertThat(addressInfoItem.getCompany()).isEqualTo(expectedUserModel.getCompany());
        assertThat(addressInfoItem.getStreet().stream().findFirst().orElseThrow()).isEqualTo(expectedUserModel.getStreetAddress());
        assertThat(addressInfoItem.getCity()).isEqualTo(expectedUserModel.getCity());
        assertThat(addressInfoItem.getPostcode()).isEqualTo(expectedUserModel.getPostCode());
        return this;
    }
}
