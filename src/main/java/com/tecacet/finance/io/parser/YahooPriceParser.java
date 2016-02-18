package com.tecacet.finance.io.parser;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import com.tecacet.finance.model.StockPrice;
import com.tecacet.jflat.CSVReader;
import com.tecacet.jflat.conversion.DataConverter;

public class YahooPriceParser {

	private final static String[] PRICE_PROPERTIES = new String[] { "date", "open", "close", "volume", "high",
			"low", "adjustedClose" };
	private final static String[] PRICE_COLUMNS = new String[] { "Date", "Open", "Close", "Volume", "High", "Low",
			"Adj Close" };

	public List<StockPrice> parseStockHistory(InputStream is) throws IOException {
		CSVReader<StockPrice> csvReader = new CSVReader<StockPrice>(StockPrice.class, PRICE_PROPERTIES, PRICE_COLUMNS);
		csvReader.getConverterRegistry().registerConverter(LocalDate.class,getLocalDateConverter());
		return csvReader.readAll(is);
	}

	private DataConverter<String, LocalDate> getLocalDateConverter() {
		return s -> LocalDate.parse(s);
	}
}