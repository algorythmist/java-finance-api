package com.tecacet.finance.io.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.jflat.CSVReader;

public class MutualFundAssetParser {

	public List<Asset> parse(InputStream is) throws IOException {
		CSVReader<Asset> reader = new CSVReader<>(Asset.class, new String[] { "symbol", "name","assetType" },
				new String[] { "Fund Symbol", "Fund Name", "Type" });
		reader.getConverterRegistry().registerConverter(AssetType.class, s -> AssetType.MUTUAL_FUND);
		reader.setDelimiter('|');
		List<Asset> assets = reader.readAll(is);
		return assets;
	}
}
