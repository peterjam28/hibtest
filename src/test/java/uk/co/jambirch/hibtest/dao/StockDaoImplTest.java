package uk.co.jambirch.hibtest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.jambirch.hibtest.model.Stock;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockDaoImplTest {

    private final Stock stock1 = new Stock("7668", "HAIO");
    private final Stock stock2 = new Stock("7668", "HAIO-1");

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    Query<Stock> q;

    @Before
    public void setUp(){
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    public void testSave()
    {
        StockDao dao = new StockDaoImpl(sessionFactory);
        dao.save(stock1);

        verify(sessionFactory, times(1)).getCurrentSession();
        verify(session, times(1)).save(stock1);
    }

    @Test
    public void testFindByStockCode()
    {
        StockDao dao = new StockDaoImpl(sessionFactory);
        when(session.createQuery("from Stock where stockCode=?", Stock.class)).thenReturn(q);
        List<Stock> l = new LinkedList<>();
        l.add(stock1);
        List<Stock> emptyList = new LinkedList<>();
        when(q.list()).thenReturn(l).thenReturn(emptyList);

        Stock expectedStock = dao.findByStockCode("7668");
        Stock nullStock = dao.findByStockCode("7669");
        verify(sessionFactory, times(2)).getCurrentSession();
        verify(session, times(2)).createQuery("from Stock where stockCode=?", Stock.class);
        verify(q, times(2)).list();
        assertEquals(stock1, expectedStock);
        assertNull(nullStock);
    }

    @Test
    public void testUpdate()
    {
        StockDao dao = new StockDaoImpl(sessionFactory);
        dao.update(stock2);
        verify(sessionFactory, times(1)).getCurrentSession();
        verify(session, times(1)).update(stock2);
    }

    @Test
    public void testDelete()
    {
        StockDao dao = new StockDaoImpl(sessionFactory);
        dao.delete(stock2);
        verify(sessionFactory, times(1)).getCurrentSession();
        verify(session, times(1)).delete(stock2);
    }

    @Test
    public void testDeleteAll()
    {
        StockDao dao = new StockDaoImpl(sessionFactory);
        when(session.createQuery("delete from Stock")).thenReturn(q);
        dao.deleteAll();
        verify(sessionFactory, times(1)).getCurrentSession();
        verify(session, times(1)).createQuery("delete from Stock");
        verify(q, times(1)).executeUpdate();
    }

}