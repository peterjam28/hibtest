package uk.co.jambirch.hibtest.bo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.jambirch.hibtest.dao.StockDao;
import uk.co.jambirch.hibtest.model.Stock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockBoImplTest {

    private final Stock stock1 = new Stock("7668", "HAIO");
    private final Stock stock2 = new Stock("7668", "HAIO-1");

    @Mock
    private StockDao stockDao;

    @Test
    public void testSave() throws Exception {
        StockBo stockBo = new StockBoImpl(stockDao);
        stockBo.save(stock1);
        verify(stockDao, times(1)).save(stock1);
    }

    @Test
    public void testFindByStockCode() throws Exception {
        StockBo stockBo = new StockBoImpl(stockDao);
        when(stockDao.findByStockCode("7668")).thenReturn(stock1);
        Stock expStock = stockBo.findByStockCode("7668");
        assertEquals(stock1, expStock);
        verify(stockDao, times(1)).findByStockCode("7668");
    }

    @Test
    public void testUpdate() throws Exception {
        StockBo stockBo = new StockBoImpl(stockDao);
        stockBo.update(stock2);
        verify(stockDao, times(1)).update(stock2);
    }

    @Test
    public void testDelete() throws Exception {
        StockBo stockBo = new StockBoImpl(stockDao);
        stockBo.delete(stock2);
        verify(stockDao, times(1)).delete(stock2);
    }
}