package com.tecacet.finance.service;

import java.time.LocalDate;
import java.util.List;

import com.tecacet.finance.model.Split;

public interface SplitService {

	List<Split> getSplitHistory(String ticker, LocalDate fromDate, LocalDate toDate) throws StockServiceException;

}