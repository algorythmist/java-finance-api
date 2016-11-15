package com.tecacet.finance.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.tecacet.finance.model.Split;

public class YahooSplitServiceTest {

	@Test
	public void testGetSplitHistory() throws StockServiceException {
		YahooSplitService splitService= new YahooSplitService();
		List<Split> splits = splitService.getSplitHistory("AAPL", LocalDate.of(2005,1,1), LocalDate.of(2015, 12, 31));
		assertEquals(2, splits.size());
		Split split1 = splits.get(0);
		assertEquals(LocalDate.of(2014, 6, 9), split1.getDate());
		assertEquals("7:1", split1.toString());
		Split split2 = splits.get(1);
		assertEquals(LocalDate.of(2005, 2, 28), split2.getDate());
		assertEquals("2:1", split2.toString());
		assertFalse(split2.isReverse());
	}
	
	@Test
	public void testReverseSplit() throws StockServiceException {
		YahooSplitService splitService= new YahooSplitService();
		List<Split> splits = splitService.getSplitHistory("ZSL", LocalDate.of(2005,1,1), LocalDate.of(2015, 12, 31));
		assertEquals(4, splits.size());
		Split split1 = splits.get(0);
		assertEquals("2:1", split1.toString());
		assertFalse(split1.isReverse());
		assertEquals(LocalDate.of(2015, 11, 13), split1.getDate());
		Split split4 = splits.get(3);
        assertEquals("1:10", split4.toString());
        assertTrue(split4.isReverse());
        assertEquals(LocalDate.of(2010, 4, 15), split4.getDate());
	}

}
