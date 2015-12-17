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
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public void setRoundLotSize(int roundLotSize) {
        this.roundLotSize = roundLotSize;
    }
    public Exchange getExchange() {
        return exchange;
    }
    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }
    
    
}
