package com.tecacet.finance.service.asset;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.tecacet.finance.io.parser.BarchartAssetParser;
import com.tecacet.finance.model.Asset;
import com.tecacet.finance.model.AssetType;

public class BarchartAssetService implements AssetService {

	private static final String[] URLS = { "http://datasrv.ddfplus.com/names/amex.txt",
			"http://datasrv.ddfplus.com/names/etf.txt", "http://datasrv.ddfplus.com/names/funds_fullname.txt",
			"http://datasrv.ddfplus.com/names/nasd.txt", "http://datasrv.ddfplus.com/names/nyse.txt" };
	private static final String[] FILENAMES = { "amex.txt", "etf.txt", "funds_fullname.txt", "nasd.txt", "nyse.txt" };
	private static final boolean[] MUTUAL_FUND = { false, false, true, false, false };

	private final BarchartAssetParser assetParser = new BarchartAssetParser();

	private final boolean useOnline;

	public BarchartAssetService() {
		this(false);
	}

	public BarchartAssetService(boolean useOnline) {
		super();
		this.useOnline = useOnline;
	}

	@Override
	public Set<Asset> getAssets() throws IOException {
		Set<Asset> assets = new TreeSet<>((a, b) -> a.getSymbol().compareTo(b.getSymbol()));
		for (int i = 0; i < FILENAMES.length; i++) {
	
			InputStream is = getStream(i);
			List<Asset> assetSet = assetParser.parse(is, getAssetType(i));
			assetSet.stream().filter(a -> a.getSymbol() != null).forEach(a -> assets.add(a));
			is.close();
		}
		return assets;
	}

	private InputStream getStream(int i) throws IOException {
		try {
			return useOnline ? new URL(URLS[i]).openStream()
					: this.getClass().getClassLoader().getResourceAsStream("barchart" + File.separator + FILENAMES[i]);
		} catch (MalformedURLException e) {
			throw new IOException(e);
		}
	}

	private AssetType getAssetType(int i) {
		return MUTUAL_FUND[i] ? AssetType.MUTUAL_FUND : AssetType.STOCK;
	}

}
