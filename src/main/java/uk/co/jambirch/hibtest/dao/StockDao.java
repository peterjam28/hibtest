package uk.co.jambirch.hibtest.dao;

import uk.co.jambirch.hibtest.model.Stock;

public interface StockDao {

    void save(Stock stock);
    Stock findByStockCode(String stockCode);
    void update(Stock stock);
    void delete(Stock stock);
    int deleteAll();
}