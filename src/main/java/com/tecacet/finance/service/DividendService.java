package com.tecacet.finance.service;

import java.time.LocalDate;
import java.util.Map;

public interface DividendService {

	Map<LocalDate, Double> getHistoricalDividends(String ticker, LocalDate fromDate, LocalDate toDate)
			throws  StockServiceException;

}