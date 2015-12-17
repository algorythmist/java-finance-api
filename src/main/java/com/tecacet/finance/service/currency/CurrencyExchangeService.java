package com.tecacet.finance.service.currency;

import java.util.Date;

public interface CurrencyExchangeService {

    double getExchangeRate(String fromCurrencyCode, String toCurrencyCode, Date date) throws ExchangeRateException;

    double getCurrentExchangeRate(String fromCurrencyCode, String toCurrencyCode) throws ExchangeRateException;
}
