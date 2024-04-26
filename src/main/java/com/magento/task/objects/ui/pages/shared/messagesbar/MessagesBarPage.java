package com.magento.task.objects.ui.pages.shared.messagesbar;

import com.codeborne.selenide.SelenideElement;
import com.magento.task.framework.localization.var1_locme.LocMe;
import com.magento.task.objects.ui.pages.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.codeborne.selenide.Selenide.$x;
import static com.magento.task.objects.ui.pages.shared.messagesbar.MessageType.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessagesBarPage extends BasePage {

    public final static String NAME = "Messages bar";

    public LocMe YOU_ADDED_TO_YOUR_SHOPPING_CART_MESSAGE;
    public LocMe THE_REQUESTED_QTY_IS_NOT_AVAILABLE;

    private final SelenideElement pageId = $x("//*[@class='messages' and @role='alert']");
    private final SelenideElement successMessage = pageId.$x("./div[contains(@class,'success')]");
    private final SelenideElement errorMessage = pageId.$x("./div[contains(@class,'error')]");
    private final SelenideElement messageText = pageId.$x("./div[contains(@class,'message')]/div");

    public boolean isSuccessMessageAvailable() {
        return getSuccessMessage().isDisplayed();
    }

    public boolean isErrorMessageAvailable() {
        return getErrorMessage().isDisplayed();
    }

    public MessageType getMessageType() {
        MessageType mType = UNKNOWN;
        if (isSuccessMessageAvailable())
            mType = SUCCESS_MESSAGE;
        else if (isErrorMessageAvailable())
            mType = ERROR_MESSAGE;
        return mType;
    }

}
