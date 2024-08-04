package test;
import java.sql.ResultSet;
import java.util.List;

import JujubesInventoryManager.Backend.SQLTableUpdateManager;

public class Test {
    public static void main(String[] args) {
        List <Object[]> arrays = SQLTableUpdateManager.getTransactionInfo(1);
        int i = 1;
        for(Object[][] array : arrays.get(2)){
            if(i==3) {
                for (Object[][] array : array){

                }
            }
            for (int j = 0; j<array.length; j++) {
                System.out.println("loop "+j);
                System.out.println(array[j]);
            }
            System.out.println("\n\n");
            i++;
        }
    }
}
