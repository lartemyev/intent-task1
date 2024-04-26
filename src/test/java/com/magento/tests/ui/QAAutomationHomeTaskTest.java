package com.magento.tests.ui;

import com.codeborne.selenide.Condition;
import com.magento.task.framework.config.ConfigurationBrowser;
import com.magento.task.models.UserModel;
import com.magento.task.objects.api.ShippingInformationRequest;
import com.magento.tests.dataproviders.UsersDP;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Links;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.magento.task.framework.config.Constants.SAVED_FILES_FOLDER;
import static com.magento.task.framework.config.enums.Browser.*;
import static com.magento.task.framework.tags.TestPriorityTags.HIGH_PRIORITY_TAG;
import static com.magento.task.framework.utils.FilesUtils.saveTextToFile;

@Links({@Link(name = "Demonstrate buying process in the Internet shop feature",
        url = "https://<feature link>"),
        @Link(name = "Manual test cases for: Demonstrate buying process in the Internet shop feature",
                url = "https://<mgmt tool url>")})
public class QAAutomationHomeTaskTest extends BaseTestUi {
    ConfigurationBrowser browser = new ConfigurationBrowser();

    @TmsLink("0001") // https://test-url/0001
    @Tag(HIGH_PRIORITY_TAG)
    @Test
    @Description("buying process in the internet shop test")
    public void buyingProcessInTheInternetShopTest() {
        UserModel user = UsersDP.getDefault();
        String TEST_DATA_TXT_FILE = "test_data.txt";
        /* ************************ [STEP 1] ****************************
         * Navigate to this site: https://magento.softwaretestingboard.com/
         *********************************************************************/
        browser
                .configure(CHROME)
                        .openBaseUrl();
        navigationSteps
                .navigateToHomePage()
        /* ***************************** [STEP 2] *******************************
         * Use the menu in order to navigate into the bags section
         ************************************************************************/
                .navigateToBagsPage()
        /* ***************************** [STEP 3] *******************************
         * Add one of the bags to your cart
         ************************************************************************/
                .addRandomBagToCart();
        /* ***************************** [STEP 4] *******************************
         * Proceed to checkout and capture the HTTP request that sends the following data to server
         ************************************************************************/
        ShippingInformationRequest shippingInformation = headerBarSteps
                .clickCartIconAndProceedToCheckout()
                .waitForPageToBeOpened()
                .verifyPageTitle()
                .setPageData(user)
                .clickNextButtonAndExtractPostData();
        /* ***************************** [STEP 5] *******************************
         * Validate that the above request contains the expected data.
         ************************************************************************/
        step2PaymentMethodSteps
                .waitForPageToBeOpened()
                .verifyShippingInformationRequest(shippingInformation, user)
        /* ***************************** [STEP 6] *******************************
         * Place the order
         ************************************************************************/
                .clickPlaceOrderButtonAndWaitForNextPage();
        /* ***************************** [STEP 7] *******************************
         * Collect the order ID and write it into a new file called test_data.txt
         ************************************************************************/
        String orderIdText = step3ThankYouSteps
                .getOrderIdFromPage();
        saveTextToFile(SAVED_FILES_FOLDER, TEST_DATA_TXT_FILE, orderIdText);
        /* ************************ [POST CONDITIONS] ****************************
         * Delete all possible created data
         *********************************************************************/
    }

    @TmsLink("0002") // https://test-url/0002
    @Tag(HIGH_PRIORITY_TAG)
    @Test
    @Description("Create a utility method that initializes Firefox driver using Selenium")
    public void createAUtilityMethodThatInitializesFirefoxDriverUsingSeleniumTest() {
        /* ************************ [STEP 1] ****************************
         * Create a Firefox driver instance with rejected third-party
         * cookies and use this user agent:
         * Mozilla/5.0 (iPhone; CPU iPhone OS 16_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko)
         * Version/16.3 Mobile/15E148 Safari/604.1
         *********************************************************************/
        String agent = "Mozilla/5.0 (iPhone; CPU iPhone OS 16_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.3 Mobile/15E148 Safari/604.1";
        browser
                .configure(FIREFOX_CUSTOM_AGENT)
        /* ************************ [STEP 2] ****************************
         * Open the link https://www.whatismybrowser.com/detect/what-is-my-user-agent/ and verify that the user agent is correct
         *********************************************************************/
                .open("https://www.whatismybrowser.com/detect/what-is-my-user-agent/");
        $x("//*[@id='detected_value']/a")
                .as("Verify user agent is: " + agent)
                .shouldHave(Condition.text(agent));
        /* ************************ [STEP 3] ****************************
         *  Open the firefox settings page where the cookies setting
         * can be verified (no need to add the verification)
         *********************************************************************/
        browser
                .open("about:preferences#privacy");
    }

    @TmsLink("0003") // https://test-url/0003
    @Tag(HIGH_PRIORITY_TAG)
    @Test
    @Description("Create a utility method that initializes Chrome driver using Selenium")
    public void createAUtilityMethodThatInitializesChromeDriverUsingSelenium() {
        /* ************************ [STEP 1] ****************************
         * Create Chrome driver instance with rejected third party cookies
         *********************************************************************/
        browser
                .configure(CHROME_NO_COOKIES);
        /* ************************ [STEP 2] ****************************
         * Open the chrome settings page where this setting can be verified (no need to add the verification)
         *********************************************************************/
        browser
                .open("chrome://settings/cookies");
    }
}