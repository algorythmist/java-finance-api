package com.tecacet.finance.service.stock;

import com.tecacet.finance.model.Split;

import java.time.LocalDate;
import java.util.List;

public interface SplitService {

    List<Split> getSplitHistory(String ticker, LocalDate fromDate, LocalDate toDate) throws StockServiceException;

}