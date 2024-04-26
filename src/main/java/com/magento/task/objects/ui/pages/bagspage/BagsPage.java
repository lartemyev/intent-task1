package com.magento.task.objects.ui.pages.bagspage;

import com.codeborne.selenide.ElementsCollection;
import com.magento.task.framework.localization.var1_locme.LocMe;
import com.magento.task.framework.utils.NumberUtils;
import com.magento.task.models.BagModel;
import com.magento.task.objects.ui.pages.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$$x;

@EqualsAndHashCode(callSuper = true)
@Data
public class BagsPage extends BasePage {

    public static final String NAME = "Bags page";

    public LocMe TITLE_TEXT;
    public LocMe YOU_ADDED_TO_YOUR_SHOPPING_CART_MESSAGE;


    public ElementsCollection getCards() { // card blocks
        return $$x("//ol[contains(@class, 'product-items')]//li//div[contains(@data-container,'product-grid')]");
    }

    public int getRandomCardIndex() {
        return NumberUtils.getRandomNumber(0, getCardsAmount() - 1);
    }

    public BagCard getCardModelByIndex(int index) {
        return new BagCard(getCards().get(index));
    }

    public BagCard getRandomBagCard() {
        int cardIndex = getRandomCardIndex();
        return getCardModelByIndex(cardIndex);
    }

    public BagModel getRandomBagCardModel() {
        BagCard card = getRandomBagCard();
        return card.getModel();
    }

    public List<BagCard> getCardsModelList() {
        List<BagCard> cards = new ArrayList<>();
        for (int i = 0; i < getCards().size(); i++) {
            cards.add(getCardModelByIndex(i));
        }
        return cards;
    }

    public int getCardsAmount() {
        return getCardsModelList().size();
    }

    public BagCard getCardFromModel(BagModel bagModel) {
        List<BagCard> bagCards =
                getCardsModelList()
                        .stream()
                        .filter(c -> c.getTitle().getText().equalsIgnoreCase(bagModel.getTitle().trim()))
                        .toList();
        return bagCards
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Unable to find card element for model: " + bagModel));
    }

}
