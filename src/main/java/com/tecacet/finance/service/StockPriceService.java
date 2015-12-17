package com.tecacet.finance.service;

import java.time.LocalDate;
import java.util.List;

import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.model.StockPrice;

public interface StockPriceService {

    List<StockPrice> getPriceHistory(String ticker, LocalDate fromDate, LocalDate toDate, final StandardPeriodType periodType) throws StockServiceException;
}
