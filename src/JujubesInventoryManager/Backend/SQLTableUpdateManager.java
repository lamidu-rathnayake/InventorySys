/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JujubesInventoryManager.Backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class SQLTableUpdateManager extends SQLManagerNew{
    //change stock details
    // addNewStock(int stock_id, String category, String size, String color, int quantity, double buying_price,double selling_price, String buying_date)
    public static void changeStockDetails(
        int stock_id, 
        int category, 
        int size, 
        int color, 
        int quantity, 
        double buying_price, 
        double selling_price, 
        String buying_date){

        String query1 = "update stock set category_id = ? where stock_id = ?;";
        String query2 = "update stock set size_id = ? where stock_id = ?;";
        String query3 = "update stock set color_id = ? where stock_id = ?;";
        String query4 = "update stock set quantity = ? where stock_id = ?;";
        String query5 = "update stock set buying_price = ? where stock_id = ?;";
        String query6 = "update stock set selling_price = ? where stock_id = ?;";
        String query7 = "update stock set buying_date = ? where stock_id = ?;"; 
 
        if(category!=-1){
            insertData(query1, new Object[]{category,stock_id});
            System.out.println("category updated");
        }
        if(size!=-1){
            insertData(query2, new Object[]{size,stock_id});
            System.out.println("sise updated");
        }
        if(color!=-1){
            insertData(query3, new Object[]{color,stock_id});
            System.out.println("color updated");
        }
        if(quantity!=-1){
            insertData(query4, new Object[]{quantity,stock_id});
            System.out.println("quantity upadated");
        }
        if(buying_price!=-1){
            insertData(query5, new Object[]{buying_price,stock_id});
            System.out.println("buying price updated");
        }
        if(selling_price!=-1){
            insertData(query6, new Object[]{selling_price,stock_id});
            System.out.println("selling Price updated");
        }
        if(!buying_date.isEmpty()){
            insertData(query7, new Object[]{buying_date,stock_id});
            System.out.println("buying date updated");
        }

    }

    //customer
    public static void changeCustomerDetails(int customer_id, String customer_name, String customer_contact, String address, String customer_email){
        int customer_status = 0;
        try {
            String isCustomerExist = "select count(customer_id) as count from customer where customer_id = ?";
            statement = getConnection().prepareStatement(isCustomerExist);
            statement.setInt(1, customer_id);
            ResultSet result = statement.executeQuery();
            while(result.next()) customer_status = result.getInt("count");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if(customer_status>0){
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
            
            if(!customer_name.isEmpty()) {
                insertData(query1, new Object[]{customer_name,customer_id});
                System.out.println("c name added");  
            } 
            if(!customer_contact.isEmpty()) {
                insertData(query2, new Object[]{customer_contact,customer_id});
                System.out.println("c contact added");
            }
            if(!address.isEmpty()) {
                insertData(query3, new Object[]{address,customer_id});
                System.out.println("c address added");
            }
            if(!customer_email.isEmpty()) {
                insertData(query4, new Object[]{customer_email,customer_id});
                System.out.println("c e-mail address added");
            } 
        }
    }

    //transaction
    public static void changeTransactionDetails(int transaction_id, int customer_id, String date, double total_amount){
        String query1 = "update transaction " +
        "set customer_id = ? " +
        "where transaction_id = ? ;";

        String query2 = "update transaction " +
        "set date = ? " +
        "where transaction_id = ? ;";
        
        String query3 = "update transaction " +
        "set total_amount = ? " +
        "where transaction_id = ? ;";

        if(customer_id != -1) insertData(query1, new Object[]{customer_id,transaction_id});
        if(!date.isEmpty()) insertData(query2, new Object[]{date,transaction_id});
        if(total_amount != -1) insertData(query3, new Object[]{total_amount,transaction_id});
    }

    //transaction_item
    public static void deleteExistingStock(int transaction_id){
        String query = "DELETE FROM transaction_items WHERE transaction_id = ? ;";
        insertData(query, new Object[] {transaction_id});
    }

    //change transaction details
    public static void updateTransaction(
        String date, 
        int transaction_id,
        int customer_id, 
        String customer_name, 
        String customer_address, 
        String customer_tel_number,
        String customer_email,
        List<Integer> stock_ids, 
        List<Integer> quantities){
        
        String insertTransactionItem = "insert into Transaction_Items(transaction_id,stock_id,quantity,amount) values (?,?,?,?)";
        String insertCustomer = "insert ignore into customer(customer_id, customer_name, customer_contact, address) values (?,?,?,?)";
        List<Double> amountOfSelectedStocks = new ArrayList<>();//sended to transaction_item table
        double total_amount = 0;//sended to transaction table
        
        // updating customer details
        // update the customer details if has changes
        // otherwise the insert as a new customer 
        if(customer_id != -1){
            changeCustomerDetails(customer_id, customer_name, customer_tel_number, customer_address, customer_email);
        }else {
            insertData(insertCustomer,
                new Object[] { getLastCustomerid() + 1, customer_name, customer_tel_number, customer_address });
        }

        // handling the stocks and amounts and updating
        if(stock_ids!=null || quantities!=null){
            int i = 0;
            int i1 = 0;
            int quantity = 0;
            total_amount = 0.0;
            double amountForWorkingStock = 0.0;
            double selling_price = 0.0;

            // cleaning the current existing stocks 
            deleteExistingStock(transaction_id);

            // calculating total amount and the stock amount by iterating
            // iterating each stock id from the stock_ids array 
            for (int stock_id : stock_ids) {
                quantity = quantities.get(i);
                selling_price = getSellingPrice(stock_id);
                amountForWorkingStock = selling_price * quantity;
                amountOfSelectedStocks.add(amountForWorkingStock);
                total_amount += amountForWorkingStock;
                
                //inserting the each stock transactions for the transaction id
                insertData(
                    insertTransactionItem, 
                    new Object[] { transaction_id, 
                    stock_id, 
                    quantities.get(i1),
                    selling_price * quantity}
                );
                i++;
            }

            // updating transaction table with the new amount
            changeTransactionDetails(transaction_id, customer_id, date, total_amount); 
            System.out.println("stocks are handled");
        }
    }

}
  