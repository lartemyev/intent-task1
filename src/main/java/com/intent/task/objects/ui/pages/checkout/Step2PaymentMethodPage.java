package com.intent.task.objects.ui.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import com.intent.task.framework.localization.var1_locme.LocMe;
import com.intent.task.objects.ui.pages.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.codeborne.selenide.Selenide.$x;

@EqualsAndHashCode(callSuper = true)
@Data
public class Step2PaymentMethodPage extends BasePage {

    public final static String NAME = "'Payment Method' page";

    private final SelenideElement pageId = $x("//*[@class='checkout-payment-method']//*[@class='step-title']");

    public LocMe PAGE_TITLE_TEXT;

    private final SelenideElement placeOrderButton = $x("//button[@data-role='opc-continue']");
}
