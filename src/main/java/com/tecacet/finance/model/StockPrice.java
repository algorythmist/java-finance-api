package com.tecacet.finance.model;

import java.io.Serializable;
import java.time.LocalDate;

public class StockPrice implements Serializable {

	private LocalDate date; 
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;
	private double adjustedClose;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public double getAdjustedClose() {
		return adjustedClose;
	}

	public void setAdjustedClose(double adjustedClose) {
		this.adjustedClose = adjustedClose;
	}
	
	@Override
	public String toString() {
		return String.format("%s %f %d", date.toString(), adjustedClose, volume);
	}

}
