package com.tecacet.finance.service.currency;

/**
 * Exception thrown when currency conversion fails
 */
public class ExchangeRateException extends RuntimeException {

    public ExchangeRateException(String message) {
        super(message);
    }

    public ExchangeRateException(Throwable t) {
        super(t);
    }

}
