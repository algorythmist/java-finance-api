package com.tecacet.finance.io;

import com.tecacet.finance.model.StockPrice;
import com.tecacet.jflat.CSVWriter;

import java.io.IOException;
import java.util.Collection;

public class CSVPriceWriter {

	private static final String[] HEADER = new String[] { "Date", "Open", "High", "Low", "Close", "Volume",
			"Adj Close" };
	private static final String[] PROPERTIES = new String[] { "date", "open", "high", "low", "close", "volume",
			"adjustedClose" };
	
	public void write(String filename, Collection<StockPrice> prices) throws IOException {

		CSVWriter writer = CSVWriter.createForProperties(PROPERTIES).withHeader(HEADER);
		writer.writeToFile(filename, prices);
	}
}
