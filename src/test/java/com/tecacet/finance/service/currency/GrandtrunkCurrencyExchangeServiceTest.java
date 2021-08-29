package com.tecacet.finance.service.currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

public class GrandtrunkCurrencyExchangeServiceTest {

    @Test
    public void getExchangeRate() {
        CurrencyExchangeService exchangeService = new GrandtrunkCurrencyExchangeService();
        double rate;
        LocalDate date = LocalDate.of(2014, 1, 12);
        rate = exchangeService.getExchangeRate("USD", "GBP", date);
        assertTrue(rate < 1.0);

    }

    @Test
    public void getCurrentExchangeRate() {
        CurrencyExchangeService exchangeService = new GrandtrunkCurrencyExchangeService();
        double rate;

        rate = exchangeService.getCurrentExchangeRate(Currency.getInstance("USD"), Currency.getInstance("GBP"));
        assertTrue(rate < 1.0);

    }

    @Test(expected = ExchangeRateException.class)
    public void testBadCurrency() {
        CurrencyExchangeService exchangeService = new GrandtrunkCurrencyExchangeService();
        exchangeService.getCurrentExchangeRate("USX", "GBP");
    }

    @Test
    public void getSupportedCurrencies() throws ExchangeRateException {
        GrandtrunkCurrencyExchangeService exchangeService = new GrandtrunkCurrencyExchangeService();
        List<String> currencies = exchangeService.getSupportedCurrencies();
        assertEquals("AED", currencies.get(0));
    }
}
