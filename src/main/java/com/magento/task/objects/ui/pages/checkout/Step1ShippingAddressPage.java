package com.magento.task.objects.ui.pages.checkout;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.magento.task.framework.localization.var1_locme.LocMe;
import com.magento.task.objects.ui.controls.Input;
import com.magento.task.objects.ui.controls.MenuWithElements;
import com.magento.task.objects.ui.controls.SelectWithDropdown;
import com.magento.task.objects.ui.pages.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@EqualsAndHashCode(callSuper = true)
@Data
public class Step1ShippingAddressPage extends BasePage {

    public final static String NAME = "'Shipping address' page";

    private final SelenideElement pageId = $x("//*[@class='checkout-shipping-address']/*[@data-role='title']");

    public LocMe PAGE_TITLE_TEXT;

    private final SelenideElement shippingFrame = pageId.$x("./../*[@id='checkout-step-shipping']");
    private final Input emailAddressInput = new Input(shippingFrame.$x(".//input[@type='email']"));
    private final Input passwordInput = new Input(shippingFrame.$x(".//input[@name='password']"));
    private final Input firstNameInput = new Input(shippingFrame.$x(".//input[@name='firstname']"));
    private final Input lastNameInput = new Input(shippingFrame.$x(".//input[@name='lastname']"));
    private final Input companyInput = new Input(shippingFrame.$x(".//input[@name='company']"));
    private final Input strAddressInput = new Input(shippingFrame.$x(".//input[@name='street[0]']"));
    private final SelectWithDropdown countrySelect = new SelectWithDropdown(shippingFrame.$x(".//select[@name='country_id']"),
            shippingFrame.$$x(".//select[@name='country_id']/..//*[contains(local-name(), 'option')]"));
    private final Input cityInput = new Input(shippingFrame.$x(".//input[@name='city']"));
    private final Input postCodeInput = new Input(shippingFrame.$x(".//input[@name='postcode']"));
    private final Input phoneNumberInput = new Input(shippingFrame.$x(".//input[@name='telephone']"));

    private final SelenideElement shippingMethodsId = $x("//*[@id='checkout-shipping-method-load']");
    private final ElementsCollection shippingMethodsElements = shippingMethodsId.$$x(".//tbody//tr//input[@type='radio']");
    private final SelenideElement nextButton = $x("//button[@data-role='opc-continue']/span");
    public Step1ShippingAddressPage selectCountry(String country) {
        getCountrySelect().selectItemByTypeText(country);
        Selenide.sleep(3 * 1000); // to let shipping methods updated
        return this;
    }

    public Step1ShippingAddressPage selectFirstShippingMethod() {
        getShippingMethodsElements().first().click();
        return this;
    }
}
