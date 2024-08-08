package JujubesInventoryManager.Backend;
import JujubesInventoryManager.Backend.UpdateFunctionClasses.SqlUpdateManager;
import JujubesInventoryManager.Backend.UpdateFunctionClasses.SqlUpdateManagerTransaction;
import JujubesInventoryManager.Backend.UpdateFunctionClasses.Stock;

public class test extends SQLTableUpdateManager{
    public static void main(String[] args) {
        SqlUpdateManagerTransaction manager = new SqlUpdateManagerTransaction();

        manager.getTransactionDataFromdb(1);

    }
}
 