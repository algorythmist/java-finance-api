package com.tecacet.finance.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.tecacet.finance.io.parser.YahooPriceParser;
import com.tecacet.finance.model.StockPrice;
import com.tecacet.jflat.LineMergerException;

public class CSVPriceWriterTest {

    @Test
    public void testWrite() throws LineMergerException, IOException {
        YahooPriceParser parser = new YahooPriceParser();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("TSLA.csv");
        List<StockPrice> prices = parser.parseStockHistory(is);
        CSVPriceWriter priceWriter = new CSVPriceWriter();
        priceWriter.write("TEST.csv", prices);

        File file = new File("TEST.csv");
        assertTrue(file.exists());
        file.delete();

    }

}
