package com.tecacet.finance.service.yahoo;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Test;

import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.model.StockPrice;
import com.tecacet.finance.service.StockPriceService;
import com.tecacet.finance.service.StockServiceException;

public class YahooStockPriceServiceTest {

	@Test
	public void getPriceHistory() throws StockServiceException {
		LocalDate fromDate = LocalDate.of(2014, 1, 2);
		LocalDate toDate = LocalDate.of(2014, 10, 31);
		StockPriceService stockPriceService = new YahooStockPriceService();
		List<StockPrice> prices = stockPriceService.getPriceHistory("AAPL", fromDate, toDate, StandardPeriodType.DAY);
		assertEquals(211, prices.size());

		StockPrice firstPrice = prices.get(0);
		assertEquals(fromDate, firstPrice.getDate());
		assertEquals(58671200, firstPrice.getVolume());
		assertEquals(73.811, firstPrice.getAdjustedClose(), 0.001);
		assertEquals(553.13, firstPrice.getClose(), 0.001); // This huge because
															// it's pre-split
		assertEquals(74.33169, firstPrice.getHigh(), 0.001);
		assertEquals(73.663, firstPrice.getLow(), 0.001);
		assertEquals(74.1515, firstPrice.getOpen(), 0.001);

		StockPrice lastPrice = prices.get(prices.size() - 1);
		assertEquals(toDate, lastPrice.getDate());
		assertEquals(44639300, lastPrice.getVolume());
		assertEquals(102.561, lastPrice.getAdjustedClose(), 0.001);
		assertEquals(108.0, lastPrice.getClose(), 0.001);
		assertEquals(102.599, lastPrice.getHigh(), 0.001);
		assertEquals(101.8109, lastPrice.getLow(), 0.001);
		assertEquals(102.5706, lastPrice.getOpen(), 0.001);

	}

	@Test(expected = StockServiceException.class)
	public void testBadSymbol() throws StockServiceException {
		LocalDate fromDate = LocalDate.of(2014, Month.JANUARY, 1);
		LocalDate toDate = LocalDate.of(2014, Month.MARCH, 31);
		StockPriceService stockPriceService = new YahooStockPriceService();
		stockPriceService.getPriceHistory("APL", fromDate, toDate, StandardPeriodType.DAY);

	}

}
