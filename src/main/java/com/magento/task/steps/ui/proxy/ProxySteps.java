package com.magento.task.steps.ui.proxy;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.harreader.model.HarEntry;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.proxy.SelenideProxyServer;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.browserup.bup.proxy.CaptureType.*;
import static com.codeborne.selenide.WebDriverRunner.getSelenideProxy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
public class ProxySteps {
    public static void enableAndSetProxy() {
        Configuration.proxyEnabled = true;
    }

    public ProxySteps() {
        enableAndSetProxy();
    }

    public static BrowserUpProxy getProxy() {
        SelenideProxyServer selenideProxyServer = getSelenideProxy();
        assertThat(selenideProxyServer)
                .isNotNull();
        return Objects.requireNonNull(selenideProxyServer).getProxy();
    }

    @Step("Proxy: Enable traffic capturing including headers and clean log entries")
    public static void enableTrackingAndClear() {
        getProxy().setHarCaptureTypes(REQUEST_CONTENT, RESPONSE_CONTENT, REQUEST_HEADERS, RESPONSE_HEADERS,
                REQUEST_COOKIES, RESPONSE_COOKIES);
        getProxy().enableHarCaptureTypes(REQUEST_CONTENT, RESPONSE_CONTENT, REQUEST_HEADERS, RESPONSE_HEADERS,
                REQUEST_COOKIES, RESPONSE_COOKIES);
        getProxy().newHar();
    }

    public static List<HarEntry> getProxyLog() {
        return Objects.requireNonNull(getSelenideProxy()).getProxy().getHar().getLog().getEntries();
    }

    @Step("Proxy: Stop traffic capturing")
    public static void stopCapturingTraffic() {
        getProxy().endHar();
    }
    public static String getEntryText(HarEntry har) {
        return har.getResponse().getContent().getText();
    }

    public static String getRequestPostDataText(HarEntry har) {
        return har
                .getRequest()
                .getPostData()
                .getText();
    }

    public static List<HarEntry> extractHarEntriesByContainsUrl(List<HarEntry> proxyEntries, final String url) {
        return proxyEntries.stream()
                .filter(harEntry -> harEntry.getRequest().getUrl().toLowerCase().contains(url.toLowerCase()))
                .filter(harEntry -> !getEntryText(harEntry).isEmpty())
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty()) throw new RuntimeException("No HarEntry objects corresponding " + url);
                    return result;
                }));
    }
}
