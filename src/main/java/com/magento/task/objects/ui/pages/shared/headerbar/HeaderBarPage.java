package com.magento.task.objects.ui.pages.shared.headerbar;

import com.codeborne.selenide.SelenideElement;
import com.magento.task.objects.ui.pages.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.codeborne.selenide.Selenide.$x;
import static com.magento.task.framework.utils.WebElementUtils.verifyElementIsDisplayed;
import static com.magento.task.framework.utils.WebElementUtils.verifyElementIsNotDisplayed;
import static org.assertj.core.api.Assertions.assertThat;

@EqualsAndHashCode(callSuper = true)
@Data
public class HeaderBarPage extends BasePage {

    public static final String NAME = "Header bar";

    private final SelenideElement counterQuantityElement = $x("//*[@data-block='minicart']//*[contains(@class,'counter qty')]");
    private final SelenideElement counterNumberElement = counterQuantityElement.$x("./*[@class='counter-number']");
    private final SelenideElement pageLoader = $x("//*[@data-role='loader']//img");
    public int getCartItemsAmount() {
        try {
            return Integer.parseInt(counterNumberElement
                    .scrollTo()
                    .getText());
        } catch (NumberFormatException numberFormatException) {
            return 0;
        }
    }

    private final SelenideElement  miniCartId = $x("//*[@id='minicart-content-wrapper']");
    private final SelenideElement  proceedToCheckoutButton = miniCartId.$x(".//button[@id='top-cart-btn-checkout']");
}
