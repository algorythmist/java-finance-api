package com.tecacet.finance.service.stock;

public class StockServiceException extends RuntimeException {

    public StockServiceException(Exception e) {
        super(e);
    }

    public StockServiceException(String message) {
        super(message);
    }

}
