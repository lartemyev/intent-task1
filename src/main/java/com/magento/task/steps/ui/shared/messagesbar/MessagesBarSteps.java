package com.magento.task.steps.ui.shared.messagesbar;

import com.codeborne.selenide.Selenide;
import com.magento.task.models.BagModel;
import com.magento.task.objects.ui.pages.shared.messagesbar.MessagesBarPage;
import com.magento.task.steps.ui.BaseStepsUi;
import com.magento.task.steps.ui.shared.headerbar.HeaderBarSteps;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.magento.task.objects.ui.pages.shared.messagesbar.MessageType.UNKNOWN;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessagesBarSteps extends BaseStepsUi {
    private final MessagesBarPage page = new MessagesBarPage();
    @Step(MessagesBarPage.NAME + ": Wait for page to be opened ...")
    public HeaderBarSteps waitForPageToBeOpened() {
        if (page.getMessageType() != UNKNOWN) {
            page.getPageId().shouldBe(visible, Duration.of(15, ChronoUnit.SECONDS));
            page.getPageId().scrollIntoView(true);
            Selenide.sleep(1000); // just in case
        }
        return headerBarSteps;
    }
    @Step(MessagesBarPage.NAME + ": Verify message text is - '{messageText}'")
    public MessagesBarSteps verifyMessageText(String messageText) {
        page
                .getMessageText()
                .as("Message text is: " + messageText)
                .shouldHave(text(messageText));
        return this;
    }

    @Step(MessagesBarPage.NAME + ": Verify error message text")
    public MessagesBarSteps verifyErrorMessageTextIfAvailable() {
        if (page.getMessageType() != UNKNOWN) {
            verifyMessageText(page
                    .THE_REQUESTED_QTY_IS_NOT_AVAILABLE
                    .get());
        }
        return this;
    }

    @Step(MessagesBarPage.NAME + ": Verify success message text")
    public MessagesBarSteps verifySuccessMessageText(BagModel bagModel) {
        return verifyMessageText(
                String.format(page
                        .YOU_ADDED_TO_YOUR_SHOPPING_CART_MESSAGE.get(),
                            bagModel
                                    .getTitle()));
    }
}