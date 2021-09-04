package com.tecacet.finance.service.stock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

/**
 * Provides dividend information for stocks
 */
public interface DividendService {

    /**
     * Retrieve historical dividends within a date range
     * @param ticker stock ticker
     * @param fromDate start of date rane
     * @param toDate end of date range
     * @return a map of dividends keyed by date
     * @throws StockServiceException if there is an API failure
     */
    Map<LocalDate, BigDecimal> getHistoricalDividends(String ticker, LocalDate fromDate, LocalDate toDate) throws StockServiceException;

}