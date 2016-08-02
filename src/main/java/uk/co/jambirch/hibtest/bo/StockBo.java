package uk.co.jambirch.hibtest.bo;

import uk.co.jambirch.hibtest.model.Stock;

public interface StockBo {

    void save(Stock stock);
    void update(Stock stock);
    void delete(Stock stock);
    Stock findByStockCode(String stockCode);
}