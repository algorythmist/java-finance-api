package com.tecacet.finance.io.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.csv.CSVFormat;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;
import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.CSVFileFormat;
import com.tecacet.jflat8.impl.CSVFlatFileReader;
import com.tecacet.jflat8.impl.HeaderBeanMapper;

public class MutualFundAssetParser {

	public List<Asset> parse(InputStream is) throws IOException {
		BeanMapper<Asset> beanMapper = new HeaderBeanMapper<>(Asset.class,
				new String[] { "Fund Symbol", "Fund Name", "Type" }, new String[] { "symbol", "name", "assetType" });
		CSVFileFormat fileFormat = new CSVFileFormat(CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('|').withQuote(null));
		CSVFlatFileReader<Asset> reader = new CSVFlatFileReader<>(beanMapper, fileFormat);
		reader.registerConverter(AssetType.class, s -> AssetType.MUTUAL_FUND);
		return reader.readToList(is);
	}
}
