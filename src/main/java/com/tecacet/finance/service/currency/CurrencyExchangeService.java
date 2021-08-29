package com.tecacet.finance.service.currency;

import java.time.LocalDate;
import java.util.Currency;

public interface CurrencyExchangeService {

    double getExchangeRate(String fromCurrencyCode, String toCurrencyCode, LocalDate date) throws ExchangeRateException;

    double getCurrentExchangeRate(String fromCurrencyCode, String toCurrencyCode) throws ExchangeRateException;

    default double getExchangeRate(Currency fromCurrency, Currency toCurrency, LocalDate date) throws ExchangeRateException {
        return getExchangeRate(fromCurrency.getCurrencyCode(), toCurrency.getCurrencyCode(), date);
    }

    default double getCurrentExchangeRate(Currency fromCurrency, Currency toCurrency) throws ExchangeRateException {
        return getCurrentExchangeRate(fromCurrency.getCurrencyCode(), toCurrency.getCurrencyCode());
    }
}
