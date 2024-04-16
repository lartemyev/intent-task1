package com.intent.task.steps.ui;

import com.intent.task.steps.ui.bagspage.BagsSteps;
import com.intent.task.steps.ui.checkout.Step1ShippingAddressSteps;
import com.intent.task.steps.ui.checkout.Step2PaymentMethodSteps;
import com.intent.task.steps.ui.checkout.Step3ThankYouSteps;
import com.intent.task.steps.ui.homepage.HomeSteps;
import com.intent.task.steps.ui.shared.headerbar.HeaderBarSteps;
import com.intent.task.steps.ui.shared.messagesbar.MessagesBarSteps;
import com.intent.task.steps.ui.navigation.NavigationSteps;

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
