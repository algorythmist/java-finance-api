package com.tecacet.finance.service.stock.yahoo;

import static org.junit.Assert.assertEquals;

import com.tecacet.finance.service.stock.DividendService;
import com.tecacet.finance.service.stock.StockServiceException;
import com.tecacet.finance.service.stock.yahoo.YahooDividendService;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class YahooDividendServiceTest {

    @Test
    public void testGetHistoricalDividends() throws StockServiceException {
        DividendService dividendService = new YahooDividendService();
        Map<LocalDate, BigDecimal> dividends = dividendService.getHistoricalDividends("IBM", LocalDate.of(2014, 1, 1), LocalDate.of(2016, 1, 1));
        assertEquals(9, dividends.size());
        String[] expected = {"2014-02-06", "2014-05-07", "2014-08-06", "2014-11-06", "2015-02-06", "2015-05-06", "2015-05-07", "2015-08-06", "2015-11-06"};
        LocalDate[] dates = dividends.keySet().stream().sorted().toArray(LocalDate[]::new);
        double[] prices = {0.95, 1.1, 1.1, 1.1, 1.1, 1.3, 1.3, 1.3, 1.3};
        for (int i = 0; i < expected.length; i++) {
            LocalDate date = dates[i];
            assertEquals(expected[i], date.toString());
            assertEquals(prices[i], dividends.get(date).doubleValue(), 0.0001);
        }
    }


    @Test
    public void getEntireDividendHistory() throws StockServiceException {
        DividendService dividendService = new YahooDividendService();
        Map<LocalDate, BigDecimal> dividends = dividendService.getHistoricalDividends("AGG", LocalDate.of(2000, 1, 1), LocalDate.of(2016, 11, 9));
        assertEquals(157, dividends.size());
    }

}
