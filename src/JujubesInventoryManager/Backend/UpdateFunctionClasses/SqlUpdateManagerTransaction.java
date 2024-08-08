package JujubesInventoryManager.Backend.UpdateFunctionClasses;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//front end window operations
    //===========================
    //getInfo(itd)
    //initializeWindow()
    //validateInteger()
    //vslidateString()
    //refresh() cleans everything
    //cleanObj() cleans only objects
    //cancel event => refetch existing values from objects
    //reset event => clean all the objects and data
    //cancel, reset events => refresh()
    
public class SqlUpdateManagerTransaction extends SqlUpdateManager{
    
    public SqlUpdateManagerTransaction(){
        super();//initializing supper constructor
    }

    
    //quering transaction data with a transaction id
    public void getTransactionDataFromdb(int transaction_id){
        try {
           
            //get transaction info <<< transaction table
            String query1 = "SELECT * FROM transaction WHERE transaction_id = ? ;";
            ResultSet results1 = getResult(query1, new Object[]{transaction_id});
            while (results1.next()) {
                createTransaction(
                    results1.getInt("transaction_id"), 
                    results1.getInt("customer_id"), 
                    results1.getString("date"), 
                    results1.getDouble("total_amount")
                );
            }


            //get customer info <<< customer Table
            String query2 = "SELECT * FROM customer WHERE customer_id = ? ;";
            ResultSet results2 = getResult(query2, new Object[]{transaction.getCustomer_id()}); 
            while (results2.next()) {
                createCustomer(
                    results2.getInt("customer_id"), 
                    results2.getString("customer_name"), 
                    results2.getInt("customer_contact"), 
                    results2.getString("address"), 
                    results2.getString("email_address")
                );
            }


            //get transactionItems into <<< transactionItem table
            String query3 = "SELECT * FROM  transaction_items WHERE transaction_id = ? ;";
            ResultSet results3 = getResult(query3, new Object[]{transaction.getTransaction_id()});
            List<Integer> stockIds = new ArrayList<>(); 
            List<Integer> quantities = new ArrayList<>(); 
            List<Double> amounts = new ArrayList<>(); 

            while (results3.next()) {

                //appending stock id to the array
                stockIds.add(results3.getInt("stock_id"));
                
                //appending the quantities for the stock ids
                quantities.add(results3.getInt("quantity"));

                //appending the amounts for the stock ids
                amounts.add(results3.getDouble("amount"));

            }

            //creating the transaction_item object
            createTransactionItem(transaction.getTransaction_id(), stockIds, quantities, amounts);

        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


 





    //updating the objects with new values
    //updating the table with new values
    

    //current operations    
    //initializing => getTransactionDataFromdb() 
    //use super class methods to create objects
    //use super class's objects such as transaction transaction_item customer 
    //update event => updateTransaction() 
    //update event => updateTransactionItem()
    //update event => updateCustomer()
    //submit event => updateTransactionTable()
    //submit event => updateTransactionItemTable()
    //submit event => updateCustomerTable()


}









