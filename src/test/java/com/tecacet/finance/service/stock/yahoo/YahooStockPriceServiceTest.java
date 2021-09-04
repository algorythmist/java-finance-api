package com.tecacet.finance.service.stock.yahoo;

import static org.junit.Assert.assertEquals;

import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.model.Quote;
import com.tecacet.finance.service.stock.StockPriceService;
import com.tecacet.finance.service.stock.StockServiceException;
import com.tecacet.finance.service.stock.yahoo.YahooStockPriceService;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class YahooStockPriceServiceTest {

    @Test
    public void getPriceHistory() throws StockServiceException {
        StockPriceService stockPriceService = new YahooStockPriceService();
        LocalDate fromDate = LocalDate.of(2014, 1, 2);
        LocalDate toDate = LocalDate.of(2014, 11, 1);
        List<Quote> prices = stockPriceService.getPriceHistory("AAPL", fromDate, toDate, StandardPeriodType.DAY);
        assertEquals(211, prices.size());

        Quote firstPrice = prices.get(0);
        assertEquals(fromDate, firstPrice.getDate());
        //Yahoo changed all prices to adjusted
//        assertEquals(58671200, firstPrice.getVolume());
//        assertEquals(79.01875, firstPrice.getClose(), 0.001);
//        assertEquals(79.5757, firstPrice.getHigh(), 0.001);
//        assertEquals(78.86, firstPrice.getLow(), 0.001);
//        assertEquals(79.3828, firstPrice.getOpen(), 0.001);

        Quote lastPrice = prices.get(prices.size() - 1);
        assertEquals(LocalDate.of(2014, 10,31), lastPrice.getDate());
//        assertEquals(44639300, lastPrice.getVolume());
//        assertEquals(108.0, lastPrice.getClose(), 0.001);
//        assertEquals(108.040, lastPrice.getHigh(), 0.001);
//        assertEquals(107.21, lastPrice.getLow(), 0.001);
//        assertEquals(108.01, lastPrice.getOpen(), 0.001);

    }

    @Test(expected = StockServiceException.class)
    public void testBadSymbol() throws StockServiceException {
        LocalDate fromDate = LocalDate.of(2014, Month.JANUARY, 1);
        LocalDate toDate = LocalDate.of(2014, Month.MARCH, 31);
        StockPriceService stockPriceService = new YahooStockPriceService();
        stockPriceService.getPriceHistory("APL", fromDate, toDate, StandardPeriodType.DAY);
    }

    @Test(expected = StockServiceException.class)
    public void testUnsupportedPeriod() throws StockServiceException {
        LocalDate fromDate = LocalDate.of(2014, Month.JANUARY, 1);
        LocalDate toDate = LocalDate.of(2014, Month.MARCH, 31);
        StockPriceService stockPriceService = new YahooStockPriceService();
        stockPriceService.getPriceHistory("APL", fromDate, toDate, StandardPeriodType.YEAR);
    }
}
