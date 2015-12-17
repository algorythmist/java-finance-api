package com.tecacet.finance.service.currency;

public class ExchangeRateException extends Exception {

    private static final long serialVersionUID = 4166873124541350585L;

    public ExchangeRateException(String message) {
        super(message);
    }

    public ExchangeRateException(Throwable t) {
       super(t);
    }

}
