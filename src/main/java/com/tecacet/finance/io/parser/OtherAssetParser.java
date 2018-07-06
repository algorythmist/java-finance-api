package com.tecacet.finance.io.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.finance.model.Exchange;
import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.CSVFileFormat;
import com.tecacet.jflat8.impl.CSVFlatFileReader;
import com.tecacet.jflat8.impl.HeaderBeanMapper;

public class OtherAssetParser {

	private static final Map<String, Exchange> EXCHNAGE_MAP = new HashMap<>();
	
	static {
		EXCHNAGE_MAP.put("A", Exchange.NYSE_MKT);
		EXCHNAGE_MAP.put("N", Exchange.NYSE);
		EXCHNAGE_MAP.put("P", Exchange.NYSE_ARCA);
		EXCHNAGE_MAP.put("Z", Exchange.BATS);
	}
	public List<Asset> parse(InputStream fis) throws IOException {
		BeanMapper<Asset> beanMapper = new HeaderBeanMapper<>(Asset.class,
				new String[] { "ACT Symbol", "Security Name", "Round Lot Size", "ETF", "Exchange" },
				new String[] { "symbol", "name", "roundLotSize", "assetType", "exchange" }
				);
		CSVFileFormat fileFormat = new CSVFileFormat(CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('|'));
		CSVFlatFileReader<Asset> reader = new CSVFlatFileReader<>(beanMapper, fileFormat);
		
	
		reader.registerConverter(AssetType.class,
				s -> isETF((String) s) ? AssetType.ETF : AssetType.STOCK);
		reader.registerConverter(Exchange.class, s -> EXCHNAGE_MAP.get(s));
		
		List<Asset> assets = reader.readToList(fis);
		// The last one is a footer
		assets.remove(assets.size() - 1);
		return assets;
	}

	private boolean isETF(String token) {
		return "Y".equals(token.trim());
	}
}
