package uk.co.jambirch.hibtest.bo;

import org.springframework.stereotype.Service;
import uk.co.jambirch.hibtest.model.Stock;
import uk.co.jambirch.hibtest.dao.StockDao;

import javax.inject.Inject;

@Service("stockBo")
public class StockBoImpl implements StockBo{

    private final StockDao stockDao;

    @Inject
    public StockBoImpl(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    public void save(Stock stock){
        stockDao.save(stock);
    }

    public void update(Stock stock){
        stockDao.update(stock);
    }

    public void delete(Stock stock){
        stockDao.delete(stock);
    }

    public Stock findByStockCode(String stockCode){
        return stockDao.findByStockCode(stockCode);
    }
}