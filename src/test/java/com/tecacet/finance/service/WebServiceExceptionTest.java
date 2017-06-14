package com.tecacet.finance.service;

import org.junit.Test;

public class WebServiceExceptionTest {

	@Test
	public void testWebServiceException() {
		new WebServiceException("just for coverage");
	}

}
