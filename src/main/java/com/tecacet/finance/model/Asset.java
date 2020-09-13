package com.tecacet.finance.model;

public class Asset {

    private String symbol;
    private String name;
    private AssetType assetType;
    private int roundLotSize;
    private Exchange exchange;

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public int getRoundLotSize() {
        return roundLotSize;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }


}
