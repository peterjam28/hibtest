package uk.co.jambirch.hibtest.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.co.jambirch.hibtest.model.Stock;

import javax.inject.Inject;
import java.util.List;

@Repository("stockDao")
@Transactional
public class StockDaoImpl implements StockDao{

    private final SessionFactory sessionFactory;

    @Inject
    public StockDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void save(Stock stock){
        sessionFactory.getCurrentSession().save(stock);
    }

    public void update(Stock stock){
        sessionFactory.getCurrentSession().update(stock);
    }

    public void delete(Stock stock){
        sessionFactory.getCurrentSession().delete(stock);
    }

    public void deleteAll(){
        Query q = sessionFactory.getCurrentSession().createQuery("delete from Stock");
        q.executeUpdate();
    }

    public Stock findByStockCode(String stockCode){
        Query<Stock> q = sessionFactory.getCurrentSession().createQuery("from Stock where stockCode=?", Stock.class);
        q.setParameter(0, stockCode);
        List<Stock> list = q.list();
        return list.size() > 0 ? list.get(0) : null;
    }
}