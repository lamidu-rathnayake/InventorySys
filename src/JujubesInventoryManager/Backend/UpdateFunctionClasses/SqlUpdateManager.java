package JujubesInventoryManager.Backend.UpdateFunctionClasses;
import java.util.ArrayList;
import java.util.List;

import JujubesInventoryManager.Backend.SQLManagerNew;

/**
 * SqlUpdateManager
 */
public class SqlUpdateManager extends SQLManagerNew {
    // Essential methods of SQLManagerNew class
    // getConnection() (inherited)
    // closeConnection() (inherited)
    // insertData(String query, Object[] array) (inherited)
    // getResult(query, sDate, eDate) (inhetited)

    protected Customer customer;
    protected Stock stock;
    protected Transaction transaction;
    protected TransactionItem transactionItem;

    public SqlUpdateManager(){
        this.customer = null;
        this.stock = null;
        this.transaction = null;
        this.transactionItem = null;
    }

    //creating essential objects
    public void createStock(int sid, String category, String color, String size, int quantity, double buying_price, double selling_price, String buying_date){
        stock = new Stock(sid, category, color, size, quantity, buying_price, selling_price, buying_date);
    }
    public void createCustomer(int cid, String name, long contact, String address, String email){
        customer = new Customer(cid, name, contact, address, email);
    }
    public void createTransaction(int tid, int cid, String date, double totalAmount){
        transaction = new Transaction(tid, cid, date, totalAmount);
    }
    public void createTransactionItem(int tid, List<Integer> sid, List<Integer> quantity, List<Double> amount){
        transactionItem = new TransactionItem(tid, sid, quantity, amount);
    }

    //scenario 1 (HANDLING STOCKS)
    //getting stock for the given stock id or for the last record.
    public void intializeStock(int sid){
        List<Object> stockInfo = new ArrayList<>();
        //getting the stock infomations from the stock table
        for(Object item : getStockInfo(sid)) stockInfo.add(item);
        
        //create stock object with that informations
        createStock(
            (int) stockInfo.get(0), 
            getCategoryName((int)stockInfo.get(1)), 
            getColorName((int)stockInfo.get(2)), 
            getSizeName((int)stockInfo.get(3)), 
            (int) stockInfo.get(4), 
            (double) stockInfo.get(5), 
            (double) stockInfo.get(6),
            String.valueOf(stockInfo.get(7)) 
        ); 
    }
    //updating the stock infomation 
    public void changeStockInfo(Stock stock){
        this.stock = stock;
        System.out.println("stock info have been changed..");
    }
    //returning stock obj
    public Stock getStockObj(){
        return stock;
    }
    //pushing the updated stock infomation into the databse (stock table)
    public void updateStockTable(){
        String updateQuery = "update stock \n" + //
                        "set\n" + //
                        "category_id = ? ,\n" + //
                        "color_id = ? ,\n" + //
                        "size_id = ? ,\n" + //
                        "quantity = ? ,\n" + //
                        "buying_price = ? ,\n" + //
                        "selling_price = ? ,\n" + //
                        "buying_date = ? \n" + //
                        "where stock_id = ?;";

        
        // checking the existency and add
        if(getCid(stock.getCategory())==-1)addNewCategory(stock.getCategory());
        if(getColorId(stock.getColor())==-1)addColor(stock.getColor()); 
        if(getSizeId(stock.getSize())==-1)addSize(stock.getSize());

        insertData(updateQuery, new Object[]{
            getCid(stock.getCategory()),
            getColorId(stock.getColor()),
            getSizeId(stock.getSize()),
            stock.getQuantity(),
            stock.getBuying_price(),
            stock.getSelling_price(),
            stock.getBuying_date(),
            stock.getStockId()
        });
    }

    //scenario 2 (HANDLING TRANSACTION)
    //HAVE HANDLED IN A ANOTHER CLASS CALLED SqlUpdateManagerTransaction
        
}