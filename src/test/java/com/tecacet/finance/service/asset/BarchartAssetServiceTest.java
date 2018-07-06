package com.tecacet.finance.service.asset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;

import com.tecacet.finance.model.Asset;
import com.tecacet.finance.service.asset.AssetService;
import com.tecacet.finance.service.asset.BarchartAssetService;

public class BarchartAssetServiceTest {

	@Test
	public void testGetAssets() throws IOException {
		AssetService assetProvider = new BarchartAssetService();
		Set<Asset> assets = assetProvider.getAssets();
		assertEquals(34799, assets.size());
	}
	
	@Test
	public void testGetAssetsFromURL() throws IOException {
		AssetService assetProvider = new BarchartAssetService(true);
		Set<Asset> assets = assetProvider.getAssets();
		assertFalse(assets.isEmpty());
	}

}
