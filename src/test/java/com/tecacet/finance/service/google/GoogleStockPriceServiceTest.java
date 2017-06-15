package com.tecacet.finance.service.google;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.model.StockPrice;
import com.tecacet.finance.service.StockPriceService;
import com.tecacet.finance.service.StockServiceException;

public class GoogleStockPriceServiceTest {

	@Test
	public void testGetPriceHistory() throws StockServiceException {
		StockPriceService priceService = new GoogleStockPriceService();
		List<StockPrice> prices = priceService.getPriceHistory("AAPL", LocalDate.of(2005, 1, 1),
				LocalDate.of(2015, 12, 31), StandardPeriodType.DAY);
		assertEquals(2768, prices.size());
	}

}
