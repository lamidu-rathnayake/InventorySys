package JujubesInventoryManager.Backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * SQLStatisticsManagerNew
 */
public class SQLStatisticsManagerNew  extends SQLManagerNew{
    // getResult(String query, String sDate, String eDate) (inherited method)

    //gets total statistics
    public static List<Object[]> getTotalSummery(String sDate, String eDate)throws SQLException{
        List <Object[]> rows = new ArrayList<>();
        ResultSet results = null;
        String query = "SELECT \n" + //
                        "\tSUM(ti.quantity * s.selling_price) AS total_amount,\n" + //
                        "    SUM(ti.quantity) AS total_sold_items,\n" + //
                        "    SUM(ti.quantity * s.buying_price) AS total_cost,\n" + //
                        "    SUM(ti.quantity * (s.selling_price - s.buying_price)) AS total_profit\n" + //
                        "FROM \n" + //
                        "    transaction_items ti\n" + //
                        "INNER JOIN \n" + //
                        "    stock s ON ti.stock_id = s.stock_id\n" + //
                        "INNER JOIN \n" + //
                        "    transaction t ON ti.transaction_id = t.transaction_id\n"+
                        "where t.date between ? and ?;";
        results = SQLManagerNew.getResult(query,sDate,eDate);
        while(results.next()) rows.add(new Object[]{
            results.getDouble("total_amount"), 
            results.getInt("total_sold_items"), 
            results.getDouble("total_cost"),
            results.getDouble("total_profit") 
        });
        SQLManagerNew.closeConnection();
        return rows;        
    }


    //Amount (gets the amounts of the sold items),
    //Overall: Category id | Category name | total amount
    public static List<Object[]> getOverallAmount(String sDate, String eDate) throws SQLException{
        List <Object[]> rows = new ArrayList<>();
        ResultSet results = null;
        String query = "SELECT \r\n" + //
                        "    c.category_id,\r\n" + //
                        "    c.category_name,\r\n" + //
                        "    SUM(ti.quantity * s.selling_price) AS total_sold_amount\r\n" + //
                        "FROM \r\n" + //
                        "    transaction_items ti\r\n" + //
                        "INNER JOIN \r\n" + //
                        "    stock s ON ti.stock_id = s.stock_id\r\n" + //
                        "INNER JOIN \r\n" + //
                        "    category c ON s.category_id = c.category_id\r\n" + //
                        "INNER JOIN \r\n" + //
                        "    transaction t ON ti.transaction_id = t.transaction_id\r\n" + //
                        "WHERE \r\n" + //
                        "    t.date BETWEEN ? AND ?\r\n" + //
                        "GROUP BY \r\n" + //
                        "    c.category_id, c.category_name;\r\n" + //
                        "";
        results = SQLManagerNew.getResult(query,sDate,eDate);
        while(results.next())rows.add(new Object[]{
            results.getInt("category_id"),
            results.getString("category_name"),
            results.getDouble("total_sold_amount")});
        SQLManagerNew.closeConnection();
        return rows;
    }

    //Size: Category id | category name | size | amount 
    public static List<Object[]> getSizeAmount(String sDate, String eDate)throws SQLException{
        List <Object[]> rows = new ArrayList<>();
        ResultSet results = null;
        String query = "SELECT \n" + //
                        "    c.category_id,\n" + //
                        "    c.category_name,\n" + //
                        "    sz.size_name AS size,\n" + //
                        "    SUM(ti.quantity * s.selling_price) AS amount\n" + //
                        "FROM \n" + //
                        "    transaction_items ti\n" + //
                        "INNER JOIN \n" + //
                        "    stock s ON ti.stock_id = s.stock_id\n" + //
                        "INNER JOIN \n" + //
                        "    category c ON s.category_id = c.category_id\n" + //
                        "INNER JOIN \n" + //
                        "    size sz ON s.size_id = sz.size_id\n" + //
                        "INNER JOIN \n" + //
                        "    transaction t ON ti.transaction_id = t.transaction_id\n" + //
                        "WHERE \n" + //
                        "    t.date BETWEEN ? AND ?\n" + //
                        "GROUP BY \n" + //
                        "    c.category_id, c.category_name, sz.size_name\n" + //
                        "order by category_id\n" + //
                        "";
        results = SQLManagerNew.getResult(query,sDate,eDate);
        while(results.next())rows.add(new Object[]{
            results.getInt("category_id"),
            results.getString("category_name"),
            results.getString("size"),
            results.getDouble("amount")});
        SQLManagerNew.closeConnection();
        return rows;        
    }

    //Stock and color: Stock id | category id | category name | size | color | amount
    public static List<Object[]> getStockColorAmount(String sDate, String eDate)throws SQLException{
        List <Object[]> rows = new ArrayList<>();
        ResultSet results = null;
        String query = "SELECT "+ 
        "stock.stock_id AS Stock_id,"+
        "category.category_id AS Category_id,"+
        "category.category_name AS Category_name,"+
        "size.size_name AS Size,"+
        "color.color_name AS Color,"+
        "SUM(transaction_items.quantity * stock.selling_price) AS Amount "+
    "FROM "+
        "transaction_items "+
    "JOIN "+
        "transaction ON transaction_items.transaction_id = transaction.transaction_id "+
    "JOIN "+
        "stock ON transaction_items.stock_id = stock.stock_id "+
    "JOIN "+
        "category ON stock.category_id = category.category_id "+
    "JOIN "+
        "size ON stock.size_id = size.size_id "+
    "JOIN "+
        "color ON stock.color_id = color.color_id "+
    "WHERE "+
        "transaction.date BETWEEN ? AND ? "+
    "GROUP BY "+
        "stock.stock_id, category.category_id, category.category_name, size.size_name, color.color_name;";
        results = SQLManagerNew.getResult(query,sDate,eDate);
        while(results.next())rows.add(new Object[]{
            results.getInt("Stock_id"),
            results.getInt("Category_id"),
            results.getString("Category_name"),
            results.getString("Size"),
            results.getString("Color"),
            results.getDouble("Amount")});
        SQLManagerNew.closeConnection();
        return rows; 
    }


    //Count (get count of the sold items),
    //Overall: Category id | Category name | Total quantity  
    public static List<Object[]> getOverallCount(String sDate, String eDate) throws SQLException{
        ResultSet results = null;
        List <Object[]> rows = new ArrayList<>();
        String query2 =  "SELECT c.category_id AS 'Category id', c.category_name AS 'Category name', SUM(ti.quantity) AS 'Total quantity' " +
        "FROM category c " +
        "LEFT JOIN stock s ON c.category_id = s.category_id " +
        "LEFT JOIN transaction_items ti ON s.stock_id = ti.stock_id " +
        "LEFT JOIN transaction t ON ti.transaction_id = t.transaction_id " +
        "WHERE t.date BETWEEN ? AND ? " +
        "GROUP BY c.category_id, c.category_name;";
        results = SQLManagerNew.getResult(query2,sDate,eDate);
        while(results.next())rows.add(new Object[]{
            results.getInt("Category id"),
            results.getString("Category name"),
            results.getInt("Total quantity")});
        SQLManagerNew.closeConnection();
        return rows;
    }

    //Size: Category id | Category name | size | Quantity  
    public static List<Object[]> getSizeCount(String sDate, String eDate)throws SQLException{
        ResultSet results = null;
        List <Object[]> rows = new ArrayList<>();
        String query2 = "SELECT c.category_id AS 'Category id', c.category_name AS 'Category name', sz.size_name AS 'Size', SUM(ti.quantity) AS 'Quantity' " +
        "FROM category c " +
        "LEFT JOIN stock s ON c.category_id = s.category_id " +
        "LEFT JOIN size sz ON s.size_id = sz.size_id " +
        "LEFT JOIN transaction_items ti ON s.stock_id = ti.stock_id " +
        "LEFT JOIN transaction t ON ti.transaction_id = t.transaction_id " +
        "WHERE t.date BETWEEN ? AND ? " +
        "GROUP BY c.category_id, c.category_name, sz.size_name;";
        results = SQLManagerNew.getResult(query2,sDate,eDate);
        while(results.next())rows.add(new Object[]{
            results.getInt("Category id"),
            results.getString("Category name"),
            results.getString("Size"),
            results.getInt("Quantity")});
        SQLManagerNew.closeConnection();
        return rows;
    }

    //Stock and color: Stock id | category id | category name | size | color | count
    public static List<Object[]> getStockColorCount(String sDate, String eDate)throws SQLException{
        ResultSet results = null;
        List <Object[]> rows = new ArrayList<>();
        String query2 = "SELECT s.stock_id AS 'stock id', c.category_id AS 'category id', c.category_name AS 'category name', " +
        "sz.size_name AS 'size', clr.color_name AS 'color', SUM(ti.quantity) AS 'total quantity' " +
        "FROM category c " +
        "LEFT JOIN stock s ON c.category_id = s.category_id " +
        "LEFT JOIN size sz ON s.size_id = sz.size_id " +
        "LEFT JOIN color clr ON s.color_id = clr.color_id " +
        "LEFT JOIN transaction_items ti ON s.stock_id = ti.stock_id " +
        "LEFT JOIN transaction t ON ti.transaction_id = t.transaction_id " +
        "WHERE t.date BETWEEN ? AND ? " +
        "GROUP BY s.stock_id, c.category_id, c.category_name, sz.size_name, clr.color_name;";
        results = SQLManagerNew.getResult(query2,sDate,eDate);
        while(results.next())rows.add(new Object[]{
            results.getInt("stock id"),
            results.getInt("category id"),
            results.getString("category name"),
            results.getString("size"),
            results.getString("color"),
            results.getInt("total quantity")});
        SQLManagerNew.closeConnection();
        return rows;
    }

    //Overall: Category id | Category name | profit  
    public static List<Object[]> getOverallProfit(String sDate, String eDate)throws SQLException{
        List <Object[]> rows = new ArrayList<>();
        ResultSet results = null;
        String query2 = "SELECT " +
        "c.category_id AS 'Category id', " +
        "c.category_name AS 'Category name', " +
        "SUM((ti.quantity * s.selling_price) - (ti.quantity * s.buying_price)) AS 'Total profit' " +
        "FROM category c " +
        "LEFT JOIN stock s ON c.category_id = s.category_id " +
        "LEFT JOIN transaction_items ti ON s.stock_id = ti.stock_id " +
        "LEFT JOIN transaction t ON ti.transaction_id = t.transaction_id " +
        "WHERE t.date BETWEEN ? AND ? " +
        "GROUP BY c.category_id, c.category_name;";
        results = SQLManagerNew.getResult(query2,sDate,eDate);
        while(results.next())rows.add(new Object[]{
            results.getInt("Category id"),
            results.getString("Category name"),
            results.getDouble("Total profit")});
        SQLManagerNew.closeConnection();
        return rows;
    }

    //Size: Category id | category name | size | count | profit 
    public static List<Object[]> getSizeProfit(String sDate, String eDate)throws SQLException{
        List <Object[]> rows = new ArrayList<>();
        ResultSet results = null;
        String query2 = "SELECT " +
        "c.category_id AS 'Category id', " +
        "c.category_name AS 'Category name', " +
        "sz.size_name AS 'Size', " +
        "SUM(ti.quantity) AS 'Quantity', " +
        "SUM((ti.quantity * s.selling_price) - (ti.quantity * s.buying_price)) AS 'Profit' " +
        "FROM category c " +
        "LEFT JOIN stock s ON c.category_id = s.category_id " +
        "LEFT JOIN size sz ON s.size_id = sz.size_id " +
        "LEFT JOIN transaction_items ti ON s.stock_id = ti.stock_id " +
        "LEFT JOIN transaction t ON ti.transaction_id = t.transaction_id " +
        "WHERE t.date BETWEEN ? AND ? " +
        "GROUP BY c.category_id, c.category_name, sz.size_name;";
        results = SQLManagerNew.getResult(query2,sDate,eDate);
        while(results.next())rows.add(new Object[]{
            results.getInt("Category id"),
            results.getString("Category name"),
            results.getString("Size"),
            results.getInt("Quantity"),
            results.getDouble("Profit")});
        SQLManagerNew.closeConnection();
        return rows;
    }

    //Color: category id | category name | size | color | count | profit
    public static List<Object[]> getColorProfit(String sDate, String eDate)throws SQLException{
        List <Object[]> rows = new ArrayList<>();
        ResultSet results = null;
        String query2 = "SELECT " +
        "c.category_id AS 'Category id', " +
        "c.category_name AS 'Category name', " +
        "sz.size_name AS 'Size', " +
        "clr.color_name AS 'color', " +
        "SUM(ti.quantity) AS 'Quantity', " +
        "SUM((ti.quantity * s.selling_price) - (ti.quantity * s.buying_price)) AS 'Profit' " +
        "FROM category c " +
        "LEFT JOIN stock s ON c.category_id = s.category_id " +
        "LEFT JOIN size sz ON s.size_id = sz.size_id " +
        "LEFT JOIN color clr ON s.color_id = clr.color_id " +
        "LEFT JOIN transaction_items ti ON s.stock_id = ti.stock_id " +
        "LEFT JOIN transaction t ON ti.transaction_id = t.transaction_id " +
        "WHERE t.date BETWEEN ? AND ? " +
        "GROUP BY c.category_id, c.category_name, sz.size_name, clr.color_name;";
        results = SQLManagerNew.getResult(query2,sDate,eDate);
        while(results.next())rows.add(new Object[]{
            results.getInt("Category id"),
            results.getString("Category name"),
            results.getString("Size"),
            results.getString("color"),
            results.getInt("Quantity"),
            results.getDouble("Profit")});
        SQLManagerNew.closeConnection();
        return rows;
    }

    //Stock and color: Stock id | Category id | category name | size | color | count | profit
    public static List<Object[]> getStockProfit(String sDate, String eDate)throws SQLException{
        List <Object[]> rows = new ArrayList<>();
        ResultSet results = null;
        String query2 = "SELECT " +
        "s.stock_id AS 'Stock id', " +
        "c.category_id AS 'Category id', " +
        "c.category_name AS 'Category name', " +
        "sz.size_name AS 'Size', " +
        "clr.color_name AS 'color', " +
        "SUM(ti.quantity) AS 'Quantity', " +
        "s.buying_price AS 'buying price', " +
        "s.selling_price AS 'selling price', " +
        "SUM((ti.quantity * s.selling_price) - (ti.quantity * s.buying_price)) AS 'Profit' " +
        "FROM stock s " +
        "LEFT JOIN color clr ON s.color_id = clr.color_id " +
        "LEFT JOIN category c ON s.category_id = c.category_id " +
        "LEFT JOIN size sz ON s.size_id = sz.size_id " +
        "LEFT JOIN transaction_items ti ON s.stock_id = ti.stock_id " +
        "LEFT JOIN transaction t ON ti.transaction_id = t.transaction_id " +
        "WHERE t.date BETWEEN ? AND ? " +
        "GROUP BY s.stock_id, c.category_id, c.category_name, sz.size_name, clr.color_name;";
        results = SQLManagerNew.getResult(query2,sDate,eDate);
        while(results.next()){
            rows.add(new Object[]{
                results.getInt("Stock id"),
                results.getInt("Category id"),
                results.getString("Category name"),
                results.getString("Size"),
                results.getString("color"),
                results.getInt("Quantity"),
                results.getDouble("buying price"),
                results.getDouble("selling price"),
                results.getDouble("Profit")});
        }
        SQLManagerNew.closeConnection();
        return rows;
    }


    //new function 20240504
    //gets the sold available statistics
    public static List<Object[]> getSoldAndAvailable(String sDate, String eDate)throws SQLException{
        List <Object[]> rows = new ArrayList<>();
        ResultSet results = null;
        String query = "SELECT " +
        "s.stock_id AS 'Stock id', " +
        "c.category_id AS 'Category id', " +
        "c.category_name AS 'Category name', " +
        "sz.size_name AS 'Size', " +
        "clr.color_name AS 'Color', " +
        "COALESCE(SUM(ti.quantity), 0) AS 'Sold quantity', " +
        "s.quantity - COALESCE(SUM(ti.quantity), 0) AS 'Available quantity', " +
        "s.quantity AS 'Bought quantity' " +
        "FROM stock s " +
        "JOIN color clr ON s.color_id = clr.color_id " +
        "JOIN category c ON s.category_id = c.category_id " +
        "JOIN size sz ON s.size_id = sz.size_id " +
        "LEFT JOIN transaction_items ti ON s.stock_id = ti.stock_id " +
        "LEFT JOIN transaction t ON ti.transaction_id = t.transaction_id AND t.date BETWEEN ? AND ? " +
        "GROUP BY s.stock_id, c.category_id, c.category_name, sz.size_name, clr.color_name "+
        "order by s.stock_id;";
        
        results = SQLManagerNew.getResult(query, sDate, eDate);
        while (results.next()) {
            rows.add(new Object[]{
                    results.getInt("Stock id"),
                    results.getInt("Category id"),
                    results.getString("Category name"),
                    results.getString("Size"),
                    results.getString("color"),
                    results.getInt("Bought quantity"),
                    results.getInt("Sold quantity"),
                    results.getInt("Available quantity")
            });
        }
        SQLManagerNew.closeConnection();
        return rows;
    }
}

