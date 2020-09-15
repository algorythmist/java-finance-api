package com.tecacet.finance.service.currency;

import com.tecacet.finance.service.WebServiceException;
import com.tecacet.finance.service.WebUtil;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GrandtrunkCurrencyExchangeService implements CurrencyExchangeService {

    private final static String REQUEST_FAILED = "False";

    private static final String SUPPORTED_CURRENCIES_URL = "http://currencies.apps.grandtrunk.net/currencies";

    private static final String CURRENT_RATE_URL = "http://currencies.apps.grandtrunk.net/getlatest/%s/%s";

    private static final String HISTORICAL_RATE_URL = "http://currencies.apps.grandtrunk.net/getrate/%s/%s/%s";

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public List<String> getSupportedCurrencies() throws ExchangeRateException {
        try {
            String responseAsText = WebUtil.getResponseAsString(SUPPORTED_CURRENCIES_URL);
            return Arrays.asList(responseAsText.split("\\r?\\n"));
        } catch (IOException e) {
            throw new ExchangeRateException(e);
        }

    }

    @Override
    public double getExchangeRate(String fromCurrencyCode, String toCurrencyCode, Date date) throws ExchangeRateException, IOException {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String url = String.format(HISTORICAL_RATE_URL, dateFormat.format(date), fromCurrencyCode, toCurrencyCode);
        return getServiceResponse(url);
    }

    @Override
    public double getCurrentExchangeRate(String fromCurrencyCode, String toCurrencyCode) throws ExchangeRateException, IOException {
        String url = String.format(CURRENT_RATE_URL, fromCurrencyCode, toCurrencyCode);
        return getServiceResponse(url);
    }

    private double getServiceResponse(String url) throws ExchangeRateException, IOException {
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