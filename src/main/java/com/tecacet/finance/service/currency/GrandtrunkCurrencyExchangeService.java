package com.tecacet.finance.service.currency;

import com.tecacet.finance.service.WebUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class GrandtrunkCurrencyExchangeService implements CurrencyExchangeService {

    private final static String REQUEST_FAILED = "False";

    private static final String SUPPORTED_CURRENCIES_URL = "http://currencies.apps.grandtrunk.net/currencies";

    private static final String CURRENT_RATE_URL = "http://currencies.apps.grandtrunk.net/getlatest/%s/%s";

    private static final String HISTORICAL_RATE_URL = "http://currencies.apps.grandtrunk.net/getrate/%s/%s/%s";

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<String> getSupportedCurrencies() throws ExchangeRateException {
        try {
            String responseAsText = WebUtil.getResponseAsString(SUPPORTED_CURRENCIES_URL);
            return Arrays.asList(responseAsText.split("\\r?\\n"));
        } catch (IOException e) {
            throw new ExchangeRateException(e);
        }

    }

    @Override
    public double getExchangeRate(String fromCurrencyCode, String toCurrencyCode, LocalDate date) throws ExchangeRateException {
        String url = String.format(HISTORICAL_RATE_URL, DATE_FORMAT.format(date), fromCurrencyCode, toCurrencyCode);
        return getServiceResponse(url);
    }

    @Override
    public double getCurrentExchangeRate(String fromCurrencyCode, String toCurrencyCode) throws ExchangeRateException {
        String url = String.format(CURRENT_RATE_URL, fromCurrencyCode, toCurrencyCode);
        return getServiceResponse(url);
    }

    private double getServiceResponse(String url) throws ExchangeRateException {
        String responseAsText;
        try {
            responseAsText = WebUtil.getResponseAsString(url);
        } catch (IOException e) {
            throw new ExchangeRateException(e);
        }
        if (REQUEST_FAILED.equals(responseAsText)) {
            throw new ExchangeRateException("No exchange rate found");
        }
        return Double.parseDouble(responseAsText);
    }

}