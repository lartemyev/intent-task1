package com.magento.task.models;

import com.magento.task.framework.utils.NumberUtils;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import static com.magento.task.framework.utils.DateTimeUtils.ENVIRONMENT_LOCALE;
import static org.assertj.core.api.Assertions.assertThat;

@Data
@Builder
public class MoneyModel {

    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency DEFAULT_CURRENCY = USD;
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    private final BigDecimal amount;
    private final Currency currency;
    private final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
    private final DecimalFormat df = new DecimalFormat("###,##0.00", symbols);

    public static MoneyModel getMoneyModelWithMoneySign(BigDecimal amount) {
        return new MoneyModel(amount, Currency.getInstance(ENVIRONMENT_LOCALE));
    }

    public static MoneyModel getMoneyModel(BigDecimal amount, String currency) {
        return new MoneyModel(amount, Currency.getInstance(currency));
    }

    public static MoneyModel parse(String amount, Currency currency) {
        String money = amount;
        try {
            NumberFormat.getNumberInstance(ENVIRONMENT_LOCALE);
            money = NumberFormat.getCurrencyInstance(ENVIRONMENT_LOCALE).parse(amount).toString();
        } finally {
            return new MoneyModel(new BigDecimal(money), currency, DEFAULT_ROUNDING);
        }
    }

    public static MoneyModel parse(String amount) {
        return parse(amount, DEFAULT_CURRENCY);
    }

    public static MoneyModel parse(String amount, String currencyCode) {
        return parse(amount, Currency.getInstance(currencyCode));
    }

    MoneyModel(String amount, Currency currency) {
        this(new BigDecimal(amount), currency, DEFAULT_ROUNDING);
    }

    MoneyModel(BigDecimal amount, Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING);
    }

    MoneyModel(BigDecimal amount, Currency currency, RoundingMode rounding) {
        this.currency = currency;
        this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding);
    }

    @Override
    public String toString() {
        return getCurrency().getSymbol() + df.format(getAmount());
    }

    public String toString(Locale locale) {
        return getCurrency().getSymbol(locale) + " " + df.format(getAmount());
    }

    public static void verifyIsEqual(BigDecimal firstPrice, BigDecimal secondPrice) {
        assertThat(NumberUtils.isEqual(firstPrice, secondPrice))
                .as("first price " + firstPrice + ", second price = " + secondPrice)
                .isTrue();
    }

    public static void verifyIsNotEqual(BigDecimal firstPrice, BigDecimal secondPrice) {
        assertThat(NumberUtils.isEqual(firstPrice, secondPrice))
                .as("first price " + firstPrice + ", second price = " + secondPrice)
                .isFalse();
    }

    public static void verifyLess(BigDecimal firstPrice, BigDecimal secondPrice) {
        assertThat(NumberUtils.isLess(firstPrice, secondPrice))
                .as("first price " + firstPrice + ", second price = " + secondPrice)
                .isTrue();
    }

    public static void verifyGreater(BigDecimal firstPrice, BigDecimal secondPrice) {
        assertThat(NumberUtils.isGreater(firstPrice, secondPrice))
                .as("first price " + firstPrice + ", second price = " + secondPrice)
                .isTrue();
    }

    public static void verifyGreaterOrEqual(BigDecimal firstPrice, BigDecimal secondPrice) {
        assertThat(firstPrice)
                .as("first price " + firstPrice + ", second price = " + secondPrice)
                .isGreaterThanOrEqualTo(secondPrice);
    }
}