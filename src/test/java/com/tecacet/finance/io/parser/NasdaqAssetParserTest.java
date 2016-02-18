package com.tecacet.finance.io.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.tecacet.finance.io.parser.NasdaqAssetParser;
import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.finance.model.Exchange;

public class NasdaqAssetParserTest {

	@Test
	public void testParse() throws IOException {
		NasdaqAssetParser parser = new NasdaqAssetParser();
		String file = "nasdaqtrader" + File.separator + "nasdaqlisted.txt";
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);
		List<Asset> assets = parser.parse(is);
		assertEquals(3004, assets.size());
		
		//AAIT|iShares MSCI All Country Asia Information Technology Index Fund|G|N|N|100
		Asset first = assets.get(0);
		assertEquals("AAIT", first.getSymbol());
		assertEquals("iShares MSCI All Country Asia Information Technology Index Fund", first.getName());
		assertEquals(Exchange.NASDAQ, first.getExchange());
		assertEquals(100, first.getRoundLotSize());
		assertEquals(AssetType.STOCK, first.getAssetType());
	}

}
