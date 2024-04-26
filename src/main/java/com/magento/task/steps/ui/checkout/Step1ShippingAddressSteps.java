package com.magento.task.steps.ui.checkout;

import com.browserup.harreader.model.HarEntry;
import com.magento.task.models.UserModel;
import com.magento.task.objects.api.ShippingInformationRequest;
import com.magento.task.objects.ui.pages.checkout.Step1ShippingAddressPage;
import com.magento.task.steps.ui.BaseStepsUi;
import com.magento.task.steps.ui.proxy.ProxySteps;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.magento.task.framework.utils.JsonUtils.toObjectFromJson;
import static com.magento.task.framework.utils.WebElementUtils.waitForElementToBeLoaded;
import static com.magento.task.steps.ui.proxy.ProxySteps.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class Step1ShippingAddressSteps extends BaseStepsUi {
    private final Step1ShippingAddressPage page = new Step1ShippingAddressPage();
    @Step(Step1ShippingAddressPage.NAME + ": Wait for page to be opened ...")
    public Step1ShippingAddressSteps waitForPageToBeOpened() {
        page.getPageId()
                .shouldBe(visible, Duration.of(15, ChronoUnit.SECONDS));
        headerBarSteps
                .waitForLoadingIsCompleted();
        return this;
    }

    @Step(Step1ShippingAddressPage.NAME + ": Verify page title")
    public Step1ShippingAddressSteps verifyPageTitle() {
        page.getPageId()
                .shouldHave(text(page.PAGE_TITLE_TEXT.get()));
        return this;
    }

    @Step(Step1ShippingAddressPage.NAME + ": Verify page title")
    public Step1ShippingAddressSteps setPageData(UserModel user) {
        page.getEmailAddressInput().setTextWithClear(user.getEmail());
        waitForElementToBeLoaded(page.getPasswordInput().getInputField(), Duration.ofSeconds(15));
        headerBarSteps.waitForLoadingIsCompleted();
        page.getFirstNameInput().setTextWithClear(user.getFirstName());
        page.getLastNameInput().setTextWithClear(user.getLastName());
        page.getCompanyInput().setTextWithClear(user.getCompany());
        page.getStrAddressInput().setTextWithClear(user.getStreetAddress());
        page.getCityInput().setTextWithClear(user.getCity());
        page.getPostCodeInput().setTextWithClear(user.getPostCode());
        page.selectCountry(user.getCountry());
        page.getPhoneNumberInput().setTextWithClear(user.getPhoneNumber());
        headerBarSteps.waitForLoadingIsCompleted();
        page.selectFirstShippingMethod();
        return this;
    }

    @Step(Step1ShippingAddressPage.NAME + ": Click 'Next' button")
    public Step2PaymentMethodSteps clickNextButton() {
        page.getNextButton().click();
        return step2PaymentMethodSteps;
    }

    @Step(Step1ShippingAddressPage.NAME + ": Click 'Next' button and extract post data")
    public ShippingInformationRequest clickNextButtonAndExtractPostData() {
        ProxySteps.enableTrackingAndClear();
        // make an action
        clickNextButton()
                .waitForPageToBeOpened();
        List<HarEntry> proxyEntries = getProxyLog();
        String SHIPPING_INFORMATION_URL = "shipping-information";
        HarEntry har = extractHarEntriesByContainsUrl(proxyEntries, SHIPPING_INFORMATION_URL).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No har found: " + SHIPPING_INFORMATION_URL));
        // stop tracking
        ProxySteps.stopCapturingTraffic();
        return toObjectFromJson(ShippingInformationRequest.class, getRequestPostDataText(har));
    }
}