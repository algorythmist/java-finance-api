package com.tecacet.finance.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface DividendService {

	Map<LocalDate, BigDecimal> getHistoricalDividends(String ticker, LocalDate fromDate, LocalDate toDate)
			throws  StockServiceException;

}