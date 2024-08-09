package JujubesInventoryManager.Backend.UpdateFunctionClasses;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
    
public class SqlUpdateManagerTransaction extends SqlUpdateManager{
    
    public SqlUpdateManagerTransaction(){
        super();//initializing supper constructor
    }
    
    //<<<<1>>>>
    //quering transaction data with a transaction id
    public void getTransactionDataFromdb(int transaction_id){            
        initializeTransactionFromdb(transaction_id);
        initializeCustomerFromdb();
        initializeTransactionItemFromdb();    
        System.out.println(transactionItem.getStock_ids());
        System.out.println(transactionItem.getQuantitys());
        System.err.println(transactionItem.getAmounts());
    }



    //<<<<2>>>>
    //updating the objects with new values
    public void upadateObject(Transaction transaction, Customer customer, TransactionItem transactionItem){
        if(transaction != null)this.transaction = transaction;
        if(customer != null)this.customer = customer;
        if(transactionItem != null)this.transactionItem = transactionItem;
    }   

    //change date
    public void changeDateOfTransaction(String date){
        this.transaction.setDate(date);
    }

    //change customer details
    public void changeCustomerInfo(String name, long contact, String address, String email){
        this.customer.setName(name);
        this.customer.setContact(contact);
        this.customer.setAddress(address);
        this.customer.setEmail(email);
    }

    //change stock ids , quantities , amounts
    public void changeStockAndQuantities(List<Integer> stock_ids, List<Integer> quantities, List<Double> amounts){
        this.transactionItem.setStock_ids(stock_ids);
        this.transactionItem.setQuantitys(quantities);
        this.transactionItem.setAmounts(amounts);

        //calculating the total amount
        int i = 0;
        double total_amount = 0;
        for(Integer stock_id : stock_ids){
            int quantity = this.transactionItem.getQuantitys().get(i);
            double selling_price = getSellingPrice(stock_id);
            double amount = selling_price*quantity;
            total_amount += amount; 
            i++;
        }

        this.transaction.setAmount(total_amount);
    }



    //<<<<3>>>>
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
        insertData(query3, new Object[]{calTotalAmount(),this.transaction.getTransaction_id()});
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

        int i = 0;
        int quantity = 0;
        double selling_price = 0.0;
        
        // cleaning the current existing stocks 
        deleteExistingStock();

        // iterating each stock id from the stock_ids array 
        // inserting the each stock transactions for the transaction id
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


    //sub functionalities
    //==========================

    //calculate total_amount
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

    //returns objs when needed
    public Transaction getTransactionObj(){
        return this.transaction;
    }
    public Customer getCustomerObj(){
        return this.customer;
    }
    public TransactionItem getTransactionItemObj(){
        return this.transactionItem;
    }

    
    public void initializeTransactionFromdb(int transaction_id){
        //get transaction info <<< transaction table
        String query1 = "SELECT * FROM transaction WHERE transaction_id = ? ;";
        ResultSet results1 = getResult(query1, new Object[]{transaction_id});
        try {
            while (results1.next()) {
                createTransaction(
                    results1.getInt("transaction_id"), 
                    results1.getInt("customer_id"), 
                    results1.getString("date"), 
                    results1.getDouble("total_amount")
                    );
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initializeCustomerFromdb(){
        //get customer info <<< customer Table
        String query2 = "SELECT * FROM customer WHERE customer_id = ? ;";
        ResultSet results2 = getResult(query2, new Object[]{this.transaction.getCustomer_id()}); 
        try {
            while (results2.next()) {
                createCustomer(
                    results2.getInt("customer_id"), 
                    results2.getString("customer_name"), 
                    results2.getLong("customer_contact"), 
                    results2.getString("address"), 
                    results2.getString("email_address")
                    );
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initializeTransactionItemFromdb(){
        //get transactionItems into <<< transactionItem table
        String query3 = "SELECT * FROM  transaction_items WHERE transaction_id = ? ;";
        ResultSet results3 = getResult(query3, new Object[]{this.transaction.getTransaction_id()});
        List<Integer> stockIds = new ArrayList<>(); 
        List<Integer> quantities = new ArrayList<>(); 
        List<Double> amounts = new ArrayList<>(); 

        try {
            while (results3.next()) {

                //appending stock id to the array
                stockIds.add(results3.getInt("stock_id"));
                
                //appending the quantities for the stock ids
                quantities.add(results3.getInt("quantity"));

                //appending the amounts for the stock ids
                amounts.add(results3.getDouble("amount"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //creating the transaction_item object
        createTransactionItem(this.transaction.getTransaction_id(), stockIds, quantities, amounts);
    }



}
    




    













