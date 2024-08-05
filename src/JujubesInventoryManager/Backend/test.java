package JujubesInventoryManager.Backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import JujubesInventoryManager.Backend.classes.SqlUpdateManager;
import JujubesInventoryManager.Backend.classes.Stock;

public class test extends SQLTableUpdateManager{
    public static void main(String[] args) {
        SqlUpdateManager updater = new SqlUpdateManager();
        updater.intializeStock(1);
        Stock stock = updater.getStockObj();
        System.out.println("outside");
        System.out.println(stock.getStockId());
        System.out.println(stock.getColor());
        System.out.println(stock.getBuying_price());
    }
}
 