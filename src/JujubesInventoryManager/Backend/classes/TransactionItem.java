package JujubesInventoryManager.Backend.classes;
import java.util.*;

public class TransactionItem {
    private int transaction_id;
    private ArrayList<Integer> stock_id;
    private ArrayList<Integer> quantity;
    private ArrayList<Double> amount;

    TransactionItem(int tid, ArrayList<Integer> sid, ArrayList<Integer> quantity, ArrayList<Double> amount){
        this.transaction_id = tid;
        this.stock_id = sid;
        this.quantity = quantity;
        this.amount = amount;
    }

    public int getTransaction_id(){
        return this.transaction_id;
    }
    public void setTransacion_id(int tid){
        this.transaction_id = tid;
    }

    public ArrayList<Integer> getStock_id(){
        return this.stock_id;
    }
    public void setStock_id(ArrayList<Integer> stock_id){
        this.stock_id = stock_id;
    }

    public ArrayList<Integer> getQuantity(){
        return this.quantity;
    }
    public void setQuantity(ArrayList<Integer> quantity){
        this.quantity = quantity;
    }

    public ArrayList<Double> getAmount(){
        return this.amount;       
    }
    public void setAmount(ArrayList<Double> amount){
        this.amount = amount;
    }
}
