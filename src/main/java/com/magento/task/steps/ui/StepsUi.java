package com.magento.task.steps.ui;

import com.magento.task.steps.ui.bagspage.BagsSteps;
import com.magento.task.steps.ui.checkout.Step1ShippingAddressSteps;
import com.magento.task.steps.ui.checkout.Step2PaymentMethodSteps;
import com.magento.task.steps.ui.checkout.Step3ThankYouSteps;
import com.magento.task.steps.ui.homepage.HomeSteps;
import com.magento.task.steps.ui.shared.headerbar.HeaderBarSteps;
import com.magento.task.steps.ui.shared.messagesbar.MessagesBarSteps;
import com.magento.task.steps.ui.navigation.NavigationSteps;

public interface StepsUi {
    HomeSteps homeSteps = new HomeSteps();
    BagsSteps bagsSteps = new BagsSteps();
    NavigationSteps navigationSteps = new NavigationSteps();
    MessagesBarSteps messagesBarSteps = new MessagesBarSteps();
    HeaderBarSteps headerBarSteps = new HeaderBarSteps();
    Step1ShippingAddressSteps step1ShippingAddressSteps = new Step1ShippingAddressSteps();
    Step2PaymentMethodSteps step2PaymentMethodSteps = new Step2PaymentMethodSteps();
    Step3ThankYouSteps step3ThankYouSteps = new Step3ThankYouSteps();
}
