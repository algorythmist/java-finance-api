package com.tecacet.finance.service.yahoo;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.io.input.BOMInputStream;

import com.tecacet.finance.model.StockPrice;
import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.CSVFileFormat;
import com.tecacet.jflat8.impl.CSVFlatFileReader;
import com.tecacet.jflat8.impl.HeaderBeanMapper;

public class YahooPriceParser {

	private final static String[] PRICE_PROPERTIES = new String[] { "date", "open", "close", "volume", "high", "low",
			"adjustedClose" };
	private final static String[] PRICE_COLUMNS = new String[] { "Date", "Open", "Close", "Volume", "High", "Low",
			"Adj Close" };

	private final CSVFlatFileReader<StockPrice> reader;

	public YahooPriceParser() {
		this(PRICE_PROPERTIES, PRICE_COLUMNS);

	}

	public YahooPriceParser(String[] properties, String[] columns) {
		super();
		BeanMapper<StockPrice> rowMapper = new HeaderBeanMapper<>(StockPrice.class, columns, properties);
		CSVFileFormat format = CSVFileFormat.defaultHeaderFormat();
		this.reader = new CSVFlatFileReader<>(rowMapper, format);
		reader.registerConverter(LocalDate.class, s -> LocalDate.parse(s.toString()));
	}

	public List<StockPrice> parse(InputStream is) throws IOException {
		 return reader.readToList(new BOMInputStream(is));
	}
	
}