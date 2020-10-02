package com.tecacet.finance.service.stock;

import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.model.Quote;

import java.time.LocalDate;
import java.util.List;

public interface StockPriceService {

    List<Quote> getPriceHistory(String ticker, LocalDate fromDate, LocalDate toDate, final StandardPeriodType periodType) throws StockServiceException;
}
