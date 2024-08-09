package JujubesInventoryManager.Backend.UpdateFunctionClasses;
import java.util.*;

public class TransactionItem {
    private int transaction_id;
    private List<Integer> stock_id;
    private List<Integer> quantity;
    private List<Double> amount;

    TransactionItem(int tid, List<Integer> sid, List<Integer> quantity, List<Double> amount){
        this.transaction_id = tid;
        this.stock_id = sid;
        this.quantity = quantity;
        this.amount = amount;
    }

    public int getTransaction_id(){
        return this.transaction_id;
    }
    // public void setTransacion_id(int tid){
    //     this.transaction_id = tid;
    // }

    public List<Integer> getStock_ids(){
        return this.stock_id;
    }
    public void setStock_ids(List<Integer> stock_id){
        this.stock_id = stock_id;
    }

    public List<Integer> getQuantitys(){
        return this.quantity;
    }
    public void setQuantitys(List<Integer> quantity){
        this.quantity = quantity;
    }

    public List<Double> getAmounts(){
        return this.amount;       
    }
    public void setAmounts(List<Double> amount){
        this.amount = amount;
    }
}
