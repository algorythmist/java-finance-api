package com.tecacet.finance.io.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.jflat.CSVReader;

public class BarchartAssetParser {
	
	public List<Asset> parse(InputStream is, AssetType assetType) throws IOException {
		CSVReader<Asset> reader = new CSVReader<>(Asset.class, new String[] {"symbol", "name"});
		reader.setDelimiter(':');
		List<Asset> assets = new ArrayList<>();
		reader.readWithCallback(is, (int rowIndex, String[] tokens, Asset asset) -> {
			asset.setAssetType(assetType);
			assets.add(asset);
		});
		return assets;
	}
}
