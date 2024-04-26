package com.magento.task.steps.ui.bagspage;

import com.magento.task.models.BagModel;
import com.magento.task.objects.ui.pages.bagspage.BagCard;
import com.magento.task.objects.ui.pages.bagspage.BagsPage;
import com.magento.task.steps.ui.BaseStepsUi;
import com.magento.task.steps.ui.shared.messagesbar.MessagesBarSteps;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.magento.task.objects.ui.pages.shared.messagesbar.MessageType.SUCCESS_MESSAGE;

@EqualsAndHashCode(callSuper = true)
@Data
public class BagsSteps extends BaseStepsUi {
    private final BagsPage page = new BagsPage();
    @Step(BagsPage.NAME + ": Wait for 'Bags Place' page is loaded...")
    public BagsSteps waitForPageToBeOpened() {
        $x(String.format(page.PRODUCTS_TITLE_TEXT_XPATH, page.TITLE_TEXT.get())).shouldBe(visible, Duration.of(10, ChronoUnit.SECONDS));
        return this;
    }
    @Step(BagsPage.NAME + ": Add one of the bags to your cart")
    public BagsSteps addRandomBagToCart() {
        BagModel cardModel = page.getRandomBagCardModel();
        clickOnAddToCard(cardModel)
                .waitForPageToBeOpened()
                .waitForLoadingIsCompleted();
        while (messagesBarPage.getMessageType() != SUCCESS_MESSAGE && headerBarPage.getCartItemsAmount() == 0) {
            messagesBarSteps
                    .verifyErrorMessageTextIfAvailable();
            cardModel = navigationSteps
                    .navigateToBagsPage()
                    .getPage()
                    .getRandomBagCardModel();
            clickOnAddToCard(cardModel)
                    .waitForPageToBeOpened()
                    .waitForLoadingIsCompleted();
        }
        messagesBarSteps
                .verifySuccessMessageText(cardModel);
        int expectedCartItems = 1;
        headerBarSteps
                .verifyCartItemsAmount(expectedCartItems)
                .verifyCartItemsAmountIcon(expectedCartItems);
        return this;
    }

    @Step(BagsPage.NAME + ": Click on bag card - {bagModel}")
    public MessagesBarSteps clickOnAddToCard(BagModel bagModel) {
        BagCard card = page.getCardFromModel(bagModel);
        card
                .scrollTo()
                .clickOnAddToCard();
        return messagesBarSteps;
    }
}