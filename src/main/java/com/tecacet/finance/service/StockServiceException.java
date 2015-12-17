package com.tecacet.finance.service;

public class StockServiceException extends Exception {

    private static final long serialVersionUID = 5860024882517169404L;

    public StockServiceException(Exception e) {
        super(e);
    }

    public StockServiceException(String message) {
        super(message);
    }

}
