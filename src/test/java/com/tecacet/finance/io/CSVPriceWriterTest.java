package com.tecacet.finance.io;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.tecacet.finance.model.Quote;
import com.tecacet.finance.service.yahoo.YahooPriceParser;

public class CSVPriceWriterTest {

    @Test
    public void testWrite() throws IOException {
        YahooPriceParser parser = new YahooPriceParser();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("TSLA.csv");
        List<Quote> prices = parser.parse(is);
        CSVPriceWriter priceWriter = new CSVPriceWriter();
        priceWriter.write("TEST.csv", prices);

        File file = new File("TEST.csv");
        assertTrue(file.exists());
        file.delete();

    }

}
