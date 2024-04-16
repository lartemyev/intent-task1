package com.intent.task.steps.ui.checkout;

import com.intent.task.framework.utils.WebElementUtils;
import com.intent.task.models.UserModel;
import com.intent.task.objects.api.ShippingInformationRequest;
import com.intent.task.objects.api.items.AddressInfoItem;
import com.intent.task.objects.ui.pages.checkout.Step2PaymentMethodPage;
import com.intent.task.objects.ui.pages.checkout.Step3ThankYouPage;
import com.intent.task.steps.ui.BaseStepsUi;
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
