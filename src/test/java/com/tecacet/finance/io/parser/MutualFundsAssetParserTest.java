package com.tecacet.finance.io.parser;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.tecacet.finance.io.parser.MutualFundAssetParser;
import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;

public class MutualFundsAssetParserTest {

	@Test
	public void testParse() throws IOException {
		MutualFundAssetParser assetParser = new MutualFundAssetParser();
		String file = "nasdaqtrader" + File.separator + "mfundslist.txt";
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);
		
		List<Asset> assets = assetParser.parse(is);
		assertEquals(7676, assets.size());
		Random random = new Random();
		Asset randomAsset = assets.get(random.nextInt(7676));
		assertEquals(AssetType.MUTUAL_FUND, randomAsset.getAssetType());
		
	}

}
