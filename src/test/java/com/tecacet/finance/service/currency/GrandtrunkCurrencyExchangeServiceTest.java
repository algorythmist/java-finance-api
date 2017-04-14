package com.tecacet.finance.service.currency;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.tecacet.finance.service.currency.CurrencyExchangeService;
import com.tecacet.finance.service.currency.ExchangeRateException;
import com.tecacet.finance.service.currency.GrandtrunkCurrencyExchangeService;

public class GrandtrunkCurrencyExchangeServiceTest {

	@Test
	public void getExchangeRate() throws Exception {
		CurrencyExchangeService exchangeService = new GrandtrunkCurrencyExchangeService();
		double rate;
		Date date = new GregorianCalendar(2014, 1, 12).getTime();
		rate = exchangeService.getExchangeRate("USD", "GBP", date);
		assertTrue(rate < 1.0);

	}

	@Test
	public void getCurrentExchangeRate() throws Exception {
		CurrencyExchangeService exchangeService = new GrandtrunkCurrencyExchangeService();
		double rate;

		rate = exchangeService.getCurrentExchangeRate("USD", "GBP");
		assertTrue(rate < 1.0);

	}

	@Test(expected = ExchangeRateException.class)
	public void testBadCurrency() throws Exception {
		CurrencyExchangeService exchangeService = new GrandtrunkCurrencyExchangeService();
		exchangeService.getCurrentExchangeRate("USX", "GBP");
	}

}
