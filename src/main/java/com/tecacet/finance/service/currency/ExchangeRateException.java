package com.tecacet.finance.service.currency;

public class ExchangeRateException extends Exception {

    public ExchangeRateException(String message) {
        super(message);
    }

    public ExchangeRateException(Throwable t) {
        super(t);
    }

}
