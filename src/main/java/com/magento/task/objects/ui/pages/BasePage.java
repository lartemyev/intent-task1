package com.magento.task.objects.ui.pages;

import lombok.Data;

import static com.magento.task.framework.localization.var1_locme.LocMeUtils.localizeLocMeDialogFields;

@Data
public class BasePage {

    public final String PRODUCTS_TITLE_TEXT_XPATH = "//h1[@id='page-title-heading']/span[text()='%s']";
    public final String PRODUCT_INFO_TEXT_XPATH = "//*[@class='product-info-main']//h1[@class='page-title']/span[text()='%s']";
    public BasePage () {
        localizeLocMeDialogFields(this);
    }

}
