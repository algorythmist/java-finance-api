package com.tecacet.finance.service.google;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.tecacet.finance.model.StockPrice;
import com.tecacet.jflat.BeanReaderRowMapper;
import com.tecacet.jflat.CSVReader;
import com.tecacet.jflat.PositionColumnMapping;
import com.tecacet.jflat.conversion.DataConverter;

public class StockPriceParser {

	private final BeanReaderRowMapper<StockPrice> rowMapper;
	private final int skipLines;

	public StockPriceParser(String[] properties) {
		super();
		this.rowMapper = new BeanReaderRowMapper<>(StockPrice.class, new PositionColumnMapping(properties));
		this.skipLines = 1;
	}
	
	public List<StockPrice> parse(InputStream is) throws IOException {
		CSVReader<StockPrice> reader = new CSVReader<StockPrice>(rowMapper);
		reader.setSkipLines(skipLines);
		return reader.readAll(is);
	}

	public void registerDateFormat(String format) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		rowMapper.getConverterRegistry().registerConverter("date", new DataConverter<String, LocalDate>() {

			@Override
			public LocalDate convert(String from) {
				return LocalDate.parse(from, formatter);
			}

		});
	}
}
