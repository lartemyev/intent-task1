package com.magento.task.objects.ui.pages.bagspage;

import com.codeborne.selenide.SelenideElement;
import com.magento.task.models.BagModel;
import com.magento.task.models.MoneyModel;
import com.magento.task.steps.ui.bagspage.BagsSteps;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.interactable;

@Getter
public class BagCard {

    private final String NOT_AVAILABLE = "<not available>"; // in case some control is not available

    private final SelenideElement card;
    private final SelenideElement image;
    private final SelenideElement title;
    private final SelenideElement rating;
    private final SelenideElement price;
    private final SelenideElement addToCardButton;

    public BagCard(SelenideElement card) {
        this.card = card;
        this.image = this.getCard().$x(".//span[@class='product-image-container']//img");
        this.title = this.getCard().$x(".//a[@class='product-item-link']");
        this.rating = this.getCard().$x(".//div[@class='rating-summary']//*[@class='rating-result']/span/span");
        this.price = this.getCard().$x(".//*[@data-role='priceBox']//*[@class='price']");
        this.addToCardButton = this.getCard().$x(".//div[@class='actions-primary']//button[@type='submit']/span");
    }

    public BagModel getModel() {
        this.scrollTo();
        return BagModel
                .builder()
                .title(this.getTitle().getText())
                .rating(getRatingAmount())
                .price(MoneyModel.parse(this.getPrice().getText()))
                .build();
    }

    public Double getRatingAmount() {
        double rating = 0.0;
        if (getRating().isDisplayed()) {
            rating = Double.parseDouble(getRating().getText().replace("%", ""));
        }
        return rating;
    }

    public BagCard scrollTo() {
        this
                .getImage()
                .scrollTo();
        return this;
    }

    public BagCard clickOnCard() {
        return click(this
                .scrollTo()
                .getImage());
    }

    public BagCard clickOnAddToCard() {
        this
                .scrollTo()
                .getCard()
                .hover(); // to show buttons
        return click(this.getAddToCardButton());
    }

    private BagCard click(SelenideElement element) {
        element
                .shouldBe(interactable, Duration.ofSeconds(5))
                .shouldBe(enabled, Duration.ofSeconds(5))
                .click();
        return this;
    }
}
