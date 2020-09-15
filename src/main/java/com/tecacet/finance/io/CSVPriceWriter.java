package com.tecacet.finance.io;

import com.tecacet.finance.model.Quote;
import com.tecacet.jflat.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class CSVPriceWriter {

    private static final String[] HEADER = new String[] {"Date", "Open", "High", "Low", "Close", "Volume", "Adj Close"};
    private static final String[] PROPERTIES = new String[] {"date", "open", "high", "low", "close", "volume", "adjustedClose"};

    public void write(String filename, Collection<Quote> prices) throws IOException {
        FileWriter fw = new FileWriter(filename);
        CSVWriter<Quote> writer = CSVWriter.createForProperties(PROPERTIES);
        writer.withHeader(HEADER);
        writer.write(fw, prices);
        fw.close();
    }
}
