package jfreerails.world.financial;




public interface StockOwner {


    int getNumberOfSharesOwned(ListedCompany inThisCompany);


    void addStock(Stock stock);

}

