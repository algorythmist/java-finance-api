package com.tecacet.finance.service;

import com.tecacet.finance.model.Split;

import java.time.LocalDate;
import java.util.List;

public interface SplitService {

    List<Split> getSplitHistory(String ticker, LocalDate fromDate, LocalDate toDate) throws StockServiceException;

}