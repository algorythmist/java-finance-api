package com.tecacet.finance.service.currency;

import java.time.LocalDate;
import java.util.Currency;

/**
 * Interface for services that provide currency conversion
 */
public interface CurrencyExchangeService {

    /**
     * Get a historical exchange rate
     * @param fromCurrencyCode 3-letter code of source currency
     * @param toCurrencyCode 3-letter code of target currency
     * @param date date for rate
     * @return the exchange rate for that day
     * @throws ExchangeRateException if anything goes wrong
     */
    double getExchangeRate(String fromCurrencyCode, String toCurrencyCode, LocalDate date) throws ExchangeRateException;

    /**
     * Get current exchange rate
     * @param fromCurrencyCode 3-letter code of source currency
     * @param toCurrencyCode 3-letter code of target currency
     * @return the current exchange rate
     * @throws ExchangeRateException if anything goes wrong
     */
    double getCurrentExchangeRate(String fromCurrencyCode, String toCurrencyCode) throws ExchangeRateException;

    /**
     * Get historical exchange rate
     * @param fromCurrency the source currency
     * @param toCurrency the target currency
     * @param date the date
     * @return the exchange rate
     * @throws ExchangeRateException if anything goes wrong
     */
    default double getExchangeRate(Currency fromCurrency, Currency toCurrency, LocalDate date) throws ExchangeRateException {
        return getExchangeRate(fromCurrency.getCurrencyCode(), toCurrency.getCurrencyCode(), date);
    }

    /**
     * Get current exchange rate
     * @param fromCurrency the source currency
     * @param toCurrency the target currency
     * @return the exchange rate
     * @throws ExchangeRateException if anything goes wrong
     */
    default double getCurrentExchangeRate(Currency fromCurrency, Currency toCurrency) throws ExchangeRateException {
        return getCurrentExchangeRate(fromCurrency.getCurrencyCode(), toCurrency.getCurrencyCode());
    }
}
