package JujubesInventoryManager.Backend.classes;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private Customer customer;
    private Stock stock;
    private Transaction transaction;
    private TransactionItem transactionItem;

    public SqlUpdateManager(){
        this.customer = null;
        this.stock = null;
        this.transaction = null;
        this.transactionItem = null;
    }

    //creating essential objects
    public void createStock(int sid, String category, String color, String size, int quantity, double buying_price, double selling_price){
        stock = new Stock(sid, category, color, size, quantity, buying_price, selling_price);
    }
    public void createCustomer(int cid, String name, int contact, String address, String email){
        customer = new Customer(cid, name, contact, address, email);
    }
    public void createTransaction(int tid, int cid, String date, double totalAmount){
        transaction = new Transaction(tid, cid, date, totalAmount);
    }
    public void createTransactionItem(int tid, ArrayList<Integer> sid, ArrayList<Integer> quantity, ArrayList<Double> amount){
        transactionItem = new TransactionItem(tid, sid, quantity, amount);
    }


    //scenario 1
    //getting stock for the given stock id or for the last record.
    public void intializeStock(int sid){
        
        List<Object> stockInfo = new ArrayList<>();
        //getting the stock infomations from the stock table
        for(Object item : SQLManagerNew.getStockInfo(sid)) stockInfo.add(item);
        
        //create stock object with that informations
        System.out.println((int) stockInfo.get(0)+" "+
        SQLManagerNew.getCategoryName((int)stockInfo.get(1))+" "+ 
        SQLManagerNew.getColorName((int)stockInfo.get(2))+" "+ 
        SQLManagerNew.getSizeName((int)stockInfo.get(3))+" "+
        (int) stockInfo.get(4)+" "+ 
        (double) stockInfo.get(5)+" "+
        (double) stockInfo.get(6));

        createStock(
            (int) stockInfo.get(0), 
            SQLManagerNew.getCategoryName((int)stockInfo.get(1)), 
            SQLManagerNew.getColorName((int)stockInfo.get(2)), 
            SQLManagerNew.getSizeName((int)stockInfo.get(3)), 
            (int) stockInfo.get(4), 
            (double) stockInfo.get(5), 
            (double) stockInfo.get(6)
        ); 
    }



    
}