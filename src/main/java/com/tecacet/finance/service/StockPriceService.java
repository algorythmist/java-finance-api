package com.tecacet.finance.service;

import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.model.StockPrice;

import java.time.LocalDate;
import java.util.List;

public interface StockPriceService {

    List<StockPrice> getPriceHistory(String ticker, LocalDate fromDate, LocalDate toDate, final StandardPeriodType periodType) throws StockServiceException;
}
