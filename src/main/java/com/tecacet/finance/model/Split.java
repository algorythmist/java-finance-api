package com.tecacet.finance.model;

import java.time.LocalDate;

public class Split {

	private final LocalDate date;
	private final int numerator;
	private final int denominator;

	public Split(LocalDate date, int numerator, int denominator) {
		super();
		this.date = date;
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	public boolean isReverse() {
		return numerator < denominator;
	}
	
	@Override
	public String toString() {
		return String.format("%d:%d on %s", numerator, denominator, date);
	}
}
