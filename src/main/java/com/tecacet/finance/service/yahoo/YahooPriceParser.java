package com.tecacet.finance.service.yahoo;

import com.tecacet.finance.model.StockPrice;
import com.tecacet.jflat.CSVReader;

import org.apache.commons.io.input.BOMInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

public class YahooPriceParser {

    private final static String[] PRICE_PROPERTIES = new String[] {"date", "open", "close", "volume", "high", "low", "adjustedClose"};
    private final static String[] PRICE_COLUMNS = new String[] {"Date", "Open", "Close", "Volume", "High", "Low", "Adj Close"};

    private final CSVReader<StockPrice> reader;

    public YahooPriceParser() {
        this(PRICE_PROPERTIES, PRICE_COLUMNS);

    }

    public YahooPriceParser(String[] properties, String[] columns) {
        super();
        reader = CSVReader.createWithHeaderMapping(StockPrice.class, columns, properties);
        reader.registerConverter(LocalDate.class, s -> LocalDate.parse(s));
    }

    public List<StockPrice> parse(InputStream is) throws IOException {
        return reader.readAll(new BOMInputStream(is));
    }

}