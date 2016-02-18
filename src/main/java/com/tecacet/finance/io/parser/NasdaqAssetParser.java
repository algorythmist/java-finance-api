package com.tecacet.finance.io.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.finance.model.Exchange;
import com.tecacet.jflat.CSVReader;

public class NasdaqAssetParser {

	public List<Asset> parse(InputStream is) throws IOException {
		CSVReader<Asset> reader = new CSVReader<Asset>(Asset.class, new String[] { "symbol", "name", "roundLotSize" },
				new String[] { "Symbol", "Security Name", "Round Lot Size" });
		reader.setDelimiter('|');
		List<Asset> assets = new ArrayList<>();
		
		reader.readWithCallback(is, (int rowIndex, String[] tokens, Asset asset) -> {
			asset.setAssetType(AssetType.STOCK);
			asset.setExchange(Exchange.NASDAQ);
			assets.add(asset);
		});
		
		// The last one is a footer
		assets.remove(assets.size() - 1);
		return assets;
	}
}
