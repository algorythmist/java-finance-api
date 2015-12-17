package com.tecacet.finance.parser;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.tecacet.finance.model.StockPrice;

public class YahooCSVParserTest {

	@Test
	public void testParseStockHistory() throws IOException {
		YahooCSVParser parser = new YahooCSVParser();
		FileInputStream is = new FileInputStream("TSLA.csv");
		List<StockPrice> prices = parser.parseStockHistory(is);
		assertEquals(502, prices.size());
		StockPrice first = prices.get(0);
		assertEquals(150.43, first.getAdjustedClose(),0.001);
		assertEquals(150.43, first.getClose(),0.001);
		assertEquals(4262400L, first.getVolume());
		assertEquals(LocalDate.of(2013, 12, 31), first.getDate());
	}

}
