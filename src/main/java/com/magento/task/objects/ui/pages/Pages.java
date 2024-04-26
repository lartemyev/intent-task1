package com.magento.task.objects.ui.pages;

import com.magento.task.objects.ui.pages.bagspage.BagsPage;
import com.magento.task.objects.ui.pages.checkout.Step1ShippingAddressPage;
import com.magento.task.objects.ui.pages.shared.headerbar.HeaderBarPage;
import com.magento.task.objects.ui.pages.homepage.HomePage;
import com.magento.task.objects.ui.pages.shared.messagesbar.MessagesBarPage;

public interface Pages {
    HomePage homePage = new HomePage();
    BagsPage bagsPage = new BagsPage();
    HeaderBarPage headerBarPage = new HeaderBarPage();
    MessagesBarPage messagesBarPage = new MessagesBarPage();
    Step1ShippingAddressPage step1ShippingAddressPage = new Step1ShippingAddressPage();
}
