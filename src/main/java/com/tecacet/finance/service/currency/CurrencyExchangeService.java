package com.tecacet.finance.service.currency;

import java.io.IOException;
import java.util.Date;

public interface CurrencyExchangeService {

    double getExchangeRate(String fromCurrencyCode, String toCurrencyCode, Date date) throws ExchangeRateException, IOException;

    double getCurrentExchangeRate(String fromCurrencyCode, String toCurrencyCode) throws ExchangeRateException, IOException;
}
