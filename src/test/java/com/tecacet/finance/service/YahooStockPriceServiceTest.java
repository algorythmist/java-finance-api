package com.tecacet.finance.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Test;

import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.model.StockPrice;

public class YahooStockPriceServiceTest {

	@Test
	public void getPriceHistory() throws StockServiceException {
		LocalDate fromDate = LocalDate.of(2014, Month.JANUARY, 1);
		LocalDate toDate = LocalDate.of(2014, Month.MARCH, 31);
		StockPriceService stockPriceService = new YahooStockPriceService();
		List<StockPrice> prices = stockPriceService.getPriceHistory("AAPL", fromDate, toDate, StandardPeriodType.DAY);
		assertEquals(61, prices.size());
	}

	@Test(expected = StockServiceException.class)
	public void testBadSymbol() throws StockServiceException {
		LocalDate fromDate = LocalDate.of(2014, Month.JANUARY, 1);
		LocalDate toDate = LocalDate.of(2014, Month.MARCH, 31);
		StockPriceService stockPriceService = new YahooStockPriceService();
		stockPriceService.getPriceHistory("APL", fromDate, toDate, StandardPeriodType.DAY);

	}
}
