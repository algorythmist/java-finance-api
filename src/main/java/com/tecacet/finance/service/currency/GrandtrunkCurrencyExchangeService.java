package com.tecacet.finance.service.currency;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tecacet.finance.service.WebServiceException;
import com.tecacet.finance.service.WebUtil;

public class GrandtrunkCurrencyExchangeService implements CurrencyExchangeService {

    private final static String REQUEST_FAILED = "False";
    // TODO private static final String SUPPORTED_CURRENCIES_URL =
    // "http://currencies.apps.grandtrunk.net/currencies[/<date>]";

    private static final String CURRENT_RATE_URL = "http://currencies.apps.grandtrunk.net/getlatest/%s/%s";

    private static final String HISTORICAL_RATE_URL = "http://currencies.apps.grandtrunk.net/getrate/%s/%s/%s";

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public GrandtrunkCurrencyExchangeService() {
        // TODO read and cache suported currencies
    }

    @Override
    public double getExchangeRate(String fromCurrencyCode, String toCurrencyCode, Date date)
            throws ExchangeRateException {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String url = String.format(HISTORICAL_RATE_URL, dateFormat.format(date), fromCurrencyCode, toCurrencyCode);
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
        } catch (WebServiceException e) {
           throw new ExchangeRateException(e);
        }
        if (REQUEST_FAILED.equals(responseAsText)) {
            throw new ExchangeRateException("No exchange rate found");
        }
        return Double.parseDouble(responseAsText);
    }

}