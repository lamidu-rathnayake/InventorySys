package JujubesInventoryManager.Backend;
import JujubesInventoryManager.Backend.UpdateFunctionClasses.SqlUpdateManagerTransaction;
import JujubesInventoryManager.Backend.UpdateFunctionClasses.Transaction;

public class test {
    public static void main(String[] args) {
        SqlUpdateManagerTransaction manager = new SqlUpdateManagerTransaction();
        manager.initializeTransaction(2);//2

        // Transaction transaction = manager.getTransactionObj();
        SQLManagerNew.deleteStock(4);
        


    }
}
 