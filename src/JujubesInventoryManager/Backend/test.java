package JujubesInventoryManager.Backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class test extends SQLTableUpdateManager{
    public static void main(String[] args) {
        // changeStockDetails(int stock_id, String category, String size, String color, int quantity, double buying_price,double selling_price, String buying_date)
        changeStockDetails(1, -1, -1, -1, -1, -1, -1, "");
    }
}
 