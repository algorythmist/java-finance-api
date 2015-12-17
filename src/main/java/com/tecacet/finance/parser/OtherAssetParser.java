package com.tecacet.finance.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.finance.model.Exchange;
import com.tecacet.jflat.CSVReader;

public class OtherAssetParser {

	private static final Map<String, Exchange> EXCHNAGE_MAP = new HashMap<>();
	
	static {
		EXCHNAGE_MAP.put("A", Exchange.NYSE_MKT);
		EXCHNAGE_MAP.put("N", Exchange.NYSE);
		EXCHNAGE_MAP.put("P", Exchange.NYSE_ARCA);
		EXCHNAGE_MAP.put("Z", Exchange.BATS);
	}
	public List<Asset> parse(InputStream fis) throws IOException {
		CSVReader<Asset> reader = new CSVReader<Asset>(Asset.class,
				new String[] { "symbol", "name", "roundLotSize", "assetType", "exchange" },
				new String[] { "ACT Symbol", "Security Name", "Round Lot Size", "ETF", "Exchange" });
		reader.setDelimiter('|');
		reader.getConverterRegistry().registerConverter(AssetType.class,
				s -> isETF((String) s) ? AssetType.ETF : AssetType.STOCK);
		reader.getConverterRegistry().registerConverter(Exchange.class, s -> EXCHNAGE_MAP.get(s));
		
		List<Asset> assets = reader.readAll(fis);
		// The last one is a footer
		assets.remove(assets.size() - 1);
		return assets;
	}

	private boolean isETF(String token) {
		return "Y".equals(token.trim());
	}
}
