package com.tecacet.finance.io.parser;

import static org.junit.Assert.assertEquals;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BarchartAssetParserTest {

    @Test
    public void testParse() throws IOException {
        String file = "barchart" + File.separator + "amex.txt";
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);
        BarchartAssetParser parser = new BarchartAssetParser();
        List<Asset> assets = parser.parse(is, AssetType.STOCK);
        assertEquals(1998, assets.size());
        Asset first = assets.get(0);
        assertEquals("AA-", first.getSymbol());
        assertEquals("Alcoa Inc Pf 3.75", first.getName());
        assertEquals(AssetType.STOCK, first.getAssetType());

    }

}
