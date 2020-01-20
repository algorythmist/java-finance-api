package com.tecacet.finance.service.yahoo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.tecacet.finance.model.Split;
import com.tecacet.finance.service.SplitService;
import com.tecacet.finance.service.StockServiceException;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class YahooSplitServiceTest {

    @Test
    public void testGetSplitHistory() throws StockServiceException {
        SplitService splitService = new YahooSplitService();
        List<Split> splits = splitService.getSplitHistory("AAPL", LocalDate.of(2005, 1, 1), LocalDate.of(2015, 12, 31));
        assertEquals(2, splits.size());
        Split split1 = splits.get(0);
        assertEquals(LocalDate.of(2014, 6, 9), split1.getDate());
        assertEquals("7:1 on 2014-06-09", split1.toString());
        Split split2 = splits.get(1);
        assertEquals(LocalDate.of(2005, 2, 28), split2.getDate());
        assertEquals("2:1 on 2005-02-28", split2.toString());
        assertFalse(split2.isReverse());
    }

    @Test
    public void testReverseSplit() throws StockServiceException {
        SplitService splitService = new YahooSplitService();
        List<Split> splits = splitService.getSplitHistory("ZSL", LocalDate.of(2005, 1, 1), LocalDate.of(2015, 12, 31));
        assertEquals(4, splits.size());
        Split split1 = splits.get(1);
        assertEquals("2:1 on 2015-11-13", split1.toString());
        assertFalse(split1.isReverse());
        assertEquals(LocalDate.of(2015, 11, 13), split1.getDate());
        Split split4 = splits.get(3);
        assertEquals("1:4 on 2011-02-25", split4.toString());
        assertTrue(split4.isReverse());
        assertEquals(LocalDate.of(2011, 2, 25), split4.getDate());
    }

}
