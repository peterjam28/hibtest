package uk.co.jambirch.hibtest.model;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import uk.co.jambirch.hibtest.bo.StockBo;
import uk.co.jambirch.hibtest.config.DataSourceConfig;
import uk.co.jambirch.hibtest.config.HibernateConfig;
import uk.co.jambirch.hibtest.dao.StockDao;

import static org.junit.Assert.*;

@Ignore("Requires a database")
public class StockTest {

    @Configuration
    @ComponentScan("uk.co.jambirch.hibtest")
    @Import({DataSourceConfig.class, HibernateConfig.class})
    static class TestConfig{}

    private static final ApplicationContext CTX = new AnnotationConfigApplicationContext(TestConfig.class);

    private static StockBo stockBo;

    @BeforeClass
    public static void setUpBean(){
        stockBo = CTX.getBean("stockBo", StockBo.class);

        CTX.getBean("stockDao", StockDao.class).deleteAll();
    }

    @Test
    public void testCrud(){
        Stock stock = new Stock("7668", "HAIO");
        stockBo.save(stock);

        Stock retrievedStock = stockBo.findByStockCode("7668");
        assertEquals(stock, retrievedStock);

        stock.setStockName("HAIO-1");
        stockBo.update(stock);

        retrievedStock = stockBo.findByStockCode("7668");
        assertEquals(stock, retrievedStock);

        stockBo.delete(stock);
        retrievedStock = stockBo.findByStockCode("7668");
        assertNull(retrievedStock);
    }

    @Test
    public void testCompare()
    {
        Stock stock1_1 = new Stock("7668", "HAIO");
        Stock stock1_2 = new Stock("7668", "HAIO");
        Stock stock2 = new Stock("7669", "HAIO-2");

        assertEquals(stock1_1, stock1_2);
        assertNotEquals(stock1_1, stock2);
        assertNotEquals(stock1_1, "a string");
    }
}