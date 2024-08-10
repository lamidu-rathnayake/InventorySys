package JujubesInventoryManager.Backend.UpdateFunctionClasses;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
    
public class SqlUpdateManagerTransaction extends SqlUpdateManager{
    
    public SqlUpdateManagerTransaction(){
        super();//initializing supper constructor 
    }
    
    
    //<<<<1>>>> search event
    public void initializeTransaction(int transaction_id){            
        initializeTransactionFromdb(transaction_id);
        initializeCustomerFromdb();
        initializeTransactionItemFromdb();   
        System.out.println("from SqlUpdateManagerTransaction:"); 
        System.out.println(this.transaction.getDate()); 
    }
    public void initializeTransactionFromdb(int transaction_id){
        //get transaction info <<< transaction table
        String query = "SELECT * FROM transaction WHERE transaction_id = ? ;";
        ResultSet results = getResult(query, new Object[]{transaction_id});
        try {
            while (results.next()) {
                createTransaction(
                    results.getInt("transaction_id"), 
                    results.getInt("customer_id"), 
                    results.getString("date"), 
                    results.getDouble("total_amount")
                    );
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void initializeCustomerFromdb(){
        //get customer info <<< customer Table
        String query = "SELECT * FROM customer WHERE customer_id = ? ;";
        ResultSet results = getResult(query, new Object[]{this.transaction.getCustomer_id()}); 
        try {
            while (results.next()) {
                createCustomer(
                    results.getInt("customer_id"), 
                    results.getString("customer_name"), 
                    results.getLong("customer_contact"), 
                    results.getString("address"), 
                    results.getString("email_address")
                    );
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void initializeTransactionItemFromdb(){
        //get transactionItems into <<< transactionItem table
        String query = "SELECT * FROM  transaction_items WHERE transaction_id = ? ;";
        ResultSet results = getResult(query, new Object[]{this.transaction.getTransaction_id()});
        List<Integer> stockIds = new ArrayList<>(); 
        List<Integer> quantities = new ArrayList<>(); 
        List<Double> amounts = new ArrayList<>(); 

        try {
            while (results.next()) {

                //appending stock id to the array
                stockIds.add(results.getInt("stock_id"));
                
                //appending the quantities for the stock ids
                quantities.add(results.getInt("quantity"));

                //appending the amounts for the stock ids
                amounts.add(results.getDouble("amount"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //creating the transaction_item object
        createTransactionItem(this.transaction.getTransaction_id(), stockIds, quantities, amounts);
    }


    //<<<<2>>>> update event
    //change date
    public void changeDateOfTransaction(String date){
        this.transaction.setDate(date);
        System.out.println("date has been changed.");
    }
    //change customer details
    public void changeCustomerInfo(String name, long contact, String address, String email){
        this.customer.setName(name);
        this.customer.setContact(contact);
        this.customer.setAddress(address);
        this.customer.setEmail(email);
        System.out.println("customer info have been changed");
    }
    //change stock ids , quantities , amounts
    public void changeStockAndQuantities(List<Integer> stock_ids, List<Integer> quantities, List<Double> amounts){
        this.transactionItem.setStock_ids(stock_ids);
        this.transactionItem.setQuantitys(quantities);
        this.transactionItem.setAmounts(amounts);    

        //assign the new total to the object
        this.transaction.setAmount(calTotalAmount());

        System.out.println("stocks and quantities have been changed");
    }


    //<<<<3>>>> submit event
    //updates transaction table
    public void updateTransactionTable(){
        // String query1 = "update transaction " +
        // "set customer_id = ? " +
        // "where transaction_id = ? ;";

        String query2 = "update transaction " +
        "set date = ? " +
        "where transaction_id = ? ;";
        
        String query3 = "update transaction " +
        "set total_amount = ? " +
        "where transaction_id = ? ;";

        // insertData(query1, new Object[]{this.transaction.getCustomer_id(),this.transaction.getTransaction_id()});
        insertData(query2, new Object[]{this.transaction.getDate(),this.transaction.getTransaction_id()});
        insertData(query3, new Object[]{this.transaction.getAmount(),this.transaction.getTransaction_id()});
        System.out.println("transaction table has been upadeted");
    }
    //updates customer table
    public void updateCustomerTable(){

        String query1 = "update customer " +
            "set customer_name = ? " +
            "where customer_id = ? ;";
        
        String query2 = "update customer " +
            "set customer_contact = ? " +
            "where customer_id = ? ;";
        
        String query3 = "update customer " +
            "set address = ? " +
            "where customer_id = ? ;";
        
        String query4 = "update customer " +
            "set email_address = ? " +
            "where customer_id = ? ;";
            
        insertData(query1, new Object[]{customer.getName(),customer.getCustomer_id()});
        insertData(query2, new Object[]{customer.getContact(),customer.getCustomer_id()});
        insertData(query3, new Object[]{customer.getAddress(),customer.getCustomer_id()});
        insertData(query4, new Object[]{customer.getEmail(),customer.getCustomer_id()});
        System.out.println("customer table has been updated");

    }
    //updates  transaction_items table
    public void updateTransactionItemTable(){
        // handling the stocks and amounts and updating

        String insertTransactionItem = "insert into Transaction_Items(transaction_id,stock_id,quantity,amount) values (?,?,?,?)";

        
        // cleaning the current existing stocks 
        deleteExistingStock();
        
        // iterating each stock id from the stock_ids array 
        // inserting the each stock transactions for the transaction id
        int i = 0;
        int quantity = 0;
        double selling_price = 0.0;

        for (int stock_id : transactionItem.getStock_ids()) {
            selling_price = getSellingPrice(stock_id);
            quantity = transactionItem.getQuantitys().get(i);
            
            insertData(
                insertTransactionItem, 
                new Object[] { 
                    transactionItem.getTransaction_id(), 
                    stock_id, 
                    quantity,
                    selling_price * quantity
                }
            );

            i++;
        }
         
    }


    //<<<<4>>>> reset event
    public void resetObjects(){
        this.customer = null;
        this.transaction = null;
        this.transactionItem = null;
    }



    //sub functionalities
    //==========================

    //calculate total_amount
    //must need to be updated with the new stocks and quantities.
    public double calTotalAmount(){
        double total_amount = 0;//sended to transaction table
        int i = 0;
        int quantity = 0;
        double amountForWorkingStock = 0.0;
        double selling_price = 0.0;

        for (int stock_id : transactionItem.getStock_ids()) {
            selling_price = getSellingPrice(stock_id);
            quantity = transactionItem.getQuantitys().get(i);
            amountForWorkingStock = selling_price * quantity;
            total_amount += amountForWorkingStock;
            i++;
        }

        return total_amount;
    }

    //cleans the existing items for the transaction_id before inserting changed info
    public void deleteExistingStock(){
        String query = "DELETE FROM transaction_items WHERE transaction_id = ? ;";
        insertData(query, new Object[] {transactionItem.getTransaction_id()});
    }


    public double getSellPrice(int sid){
        return getSellingPrice(sid);
    }
    // //returns objs when needed
    // public Transaction getTransactionObj(){
    //     return this.transaction;
    // }
    // public Customer getCustomerObj(){
    //     return this.customer;
    // }
    // public TransactionItem getTransactionItemObj(){
    //     return this.transactionItem;
    // }

    // //updating the objects with new values
    // public void upadateObject(Transaction transaction, Customer customer, TransactionItem transactionItem){
    //     if(transaction != null)this.transaction = transaction;
    //     if(customer != null)this.customer = customer;
    //     if(transactionItem != null)this.transactionItem = transactionItem;
    // }  

    //outside access
    public String getCname(){
        return this.customer.getName();
    }
    public String getCaddress(){
        return this.customer.getAddress();
    }
    public long getCcontact(){
        return this.customer.getContact();
    }
    public String getCemail(){
        return this.customer.getEmail();
    }

    public String getTdate(){
        return this.transaction.getDate();
    }
    public double getTotalAmount(){
        return this.transaction.getAmount();
    }

    public List<Integer> getSids(){
        return this.transactionItem.getStock_ids();
    }
    public List<Integer> getSQuantities(){
        return this.transactionItem.getQuantitys();
    }
    public List<Double> getAmounts(){
        return this.transactionItem.getAmounts();
    }

}
    




    













