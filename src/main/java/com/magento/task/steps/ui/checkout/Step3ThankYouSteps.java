package com.magento.task.steps.ui.checkout;

import com.magento.task.framework.utils.WebElementUtils;
import com.magento.task.models.UserModel;
import com.magento.task.objects.api.ShippingInformationRequest;
import com.magento.task.objects.api.items.AddressInfoItem;
import com.magento.task.objects.ui.pages.checkout.Step2PaymentMethodPage;
import com.magento.task.objects.ui.pages.checkout.Step3ThankYouPage;
import com.magento.task.steps.ui.BaseStepsUi;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@EqualsAndHashCode(callSuper = true)
@Data
public class Step3ThankYouSteps extends BaseStepsUi {
    private final Step3ThankYouPage page = new Step3ThankYouPage();
    @Step(Step3ThankYouPage.NAME + ": Wait for page to be opened ...")
    public Step3ThankYouSteps waitForPageToBeOpened() {
        page.getPageId()
                .shouldBe(visible, Duration.of(10, ChronoUnit.SECONDS));
        headerBarSteps
                .waitForLoadingIsCompleted();
        return this;
    }

    @Step(Step3ThankYouPage.NAME + ": Verify page title")
    public Step3ThankYouSteps verifyPageTitle() {
        page.getPageId()
                .shouldHave(text(page.PAGE_TITLE_TEXT.get()), Duration.ofSeconds(10));
        return this;
    }
    @Step(Step3ThankYouPage.NAME + ": Get order id from page")
    public String getOrderIdFromPage() {
        return page
                .getOrderIdText()
                .getText();
    }


}
