package JujubesInventoryManager.Backend.UpdateFunctionClasses;

public class Transaction {
    private int transaction_id;
    private int customer_id;
    private String date;
    private double totalAmount;

    Transaction(int tid, int cid, String date, double totalAmount){
        this.transaction_id = tid;
        this.customer_id = cid;
        this.date = date;
        this.totalAmount = totalAmount;
    }  

    public int getTransaction_id(){
        return this.transaction_id;
    }

    public int getCustomer_id(){
        return this.customer_id;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public double getAmount(){
        return this.totalAmount;
    }
    
    public void setAmount(double amount){
        this.totalAmount = amount;
    }
}
