package com.tecacet.finance.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Map;

import org.junit.Test;

public class YahooDividendServiceTest {

	@Test
	public void testGetHistoricalDividends() throws StockServiceException {
		DividendService dividendService = new YahooDividendService();
		Map<LocalDate, Double> dividends = dividendService.getHistoricalDividends("IBM", LocalDate.of(2014, 1, 1),
				LocalDate.of(2016, 1, 1));
		assertEquals(8, dividends.size());
		String[] expected = { "2014-02-06", "2014-05-07", "2014-08-06", "2014-11-06", "2015-02-06", "2015-05-06",
				"2015-08-06", "2015-11-06" };
		LocalDate[] dates = dividends.keySet().stream().sorted().toArray(s -> new LocalDate[s]);
		double[] prices = { 0.95, 1.1, 1.1, 1.1, 1.1, 1.3, 1.3, 1.3 };
		for (int i = 0; i < expected.length; i++) {
			LocalDate date = dates[i];
			assertEquals(expected[i], date.toString());
			assertEquals(prices[i], dividends.get(date), 0.0001);
		}
	}
	
	//@Test
	public void testGetMoreDividends() throws StockServiceException {
		DividendService dividendService = new YahooDividendService();
		Map<LocalDate, Double> dividends = dividendService.getHistoricalDividends("AAPL", LocalDate.of(2010, 1, 1),
				LocalDate.of(2016, 1, 1));
		System.out.println(dividends);
		assertEquals(8, dividends.size());
		String[] expected = { "2014-02-06", "2014-05-07", "2014-08-06", "2014-11-06", "2015-02-06", "2015-05-06",
				"2015-08-06", "2015-11-06" };
		LocalDate[] dates = dividends.keySet().stream().sorted().toArray(s -> new LocalDate[s]);
		double[] prices = { 0.95, 1.1, 1.1, 1.1, 1.1, 1.3, 1.3, 1.3 };
		for (int i = 0; i < expected.length; i++) {
			LocalDate date = dates[i];
			assertEquals(expected[i], date.toString());
			assertEquals(prices[i], dividends.get(date), 0.0001);
		}
	}

}
