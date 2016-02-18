package com.tecacet.finance.io;

import com.tecacet.finance.model.StockPrice;
import com.tecacet.jflat.CSVWriter;
import com.tecacet.jflat.LineMergerException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class CSVPriceWriter {

	private static final String[] HEADER = new String[] { "Date", "Open", "High", "Low", "Close", "Volume",
			"Adj Close" };
	private static final String[] PROPERTIES = new String[] { "date", "open", "high", "low", "close", "volume",
			"adjustedClose" };
	
	public void write(String filename, Collection<StockPrice> prices) throws LineMergerException, IOException {
		FileWriter fw = new FileWriter(filename);
		CSVWriter<StockPrice> writer = new CSVWriter<>(fw, StockPrice.class, PROPERTIES);
		writer.writeNext(HEADER);
		writer.writeAll(prices);
		writer.close();
		fw.close();
	}
}
