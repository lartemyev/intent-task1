package com.magento.task.objects.ui.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import com.magento.task.framework.localization.var1_locme.LocMe;
import com.magento.task.objects.ui.pages.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.codeborne.selenide.Selenide.$x;

@EqualsAndHashCode(callSuper = true)
@Data
public class Step3ThankYouPage extends BasePage {

    public final static String NAME = "'Thank you' page";

    private final SelenideElement pageId = $x("//*[@class='page-title']/*[@data-ui-id='page-title-wrapper']");

    public LocMe PAGE_TITLE_TEXT;

    private final SelenideElement orderIdText = $x("//*[@class='checkout-success']/p/span");
}
