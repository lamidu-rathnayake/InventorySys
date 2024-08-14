package JujubesInventoryManager.Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.spi.DirStateFactory.Result;

public class SQLManagerNew {
    // Defining the essential attributes for sql connection
    protected static final String url = "jdbc:mysql://localhost:3306/new_cloth_shop_test";
    protected static final String userName = "root";
    protected static final String password = "lamidu@123";
    protected static Connection connection;
    protected static PreparedStatement statement;


    // CONNECTION
    // gives the connection
    protected static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, userName, password);
        }
        return connection;
    }
    // closes the connection
    protected static void closeConnection() throws SQLException {
        connection.close();
    }


    // INSERTING AND SELECTING
    // fetches the data from tables
    protected static ResultSet getResult(String query, String sDate, String eDate){
        ResultSet results;
        try{
            statement = getConnection().prepareStatement(query);
            statement.setString(1,sDate);
            statement.setString(2,eDate);
            results = statement.executeQuery();          
            return results;
        }catch(SQLException exc){
            System.out.println(exc.getMessage());
            return null;
        }
    }
    //overload getResult
    protected static ResultSet getResult(String query, Object[] array){
        ResultSet results;
        try{
            statement = getConnection().prepareStatement(query);
            
            int i = 1;// used to indecate the current column "starts from the 1st column"
            for (Object object : array) {
                if (object instanceof String)
                    statement.setString(i, (String) object);
                else if (object instanceof Double)
                    statement.setDouble(i, (Double) object);
                else if (object instanceof Integer)
                    statement.setInt(i, (Integer) object);
                else if (object instanceof Long)
                    statement.setLong(i, (Long) object);
                else
                    throw new SQLException("invalid data input");
                i++;
            } // setting up the statement by iterating the array

            results = statement.executeQuery();          
            return results;
        }catch(SQLException exc){
            System.out.println(exc.getMessage());
            return null;
        }
    }
    // inserts data into table
    protected static int insertData(String query, Object[] array) {
        int flag = 0;
        try {
            statement = getConnection().prepareStatement(query);
            int i = 1;// used to indecate the current column "starts from the 1st column"

            for (Object object : array) {
                if (object instanceof String)
                    statement.setString(i, (String) object);
                else if (object instanceof Double)
                    statement.setDouble(i, (Double) object);
                else if (object instanceof Integer)
                    statement.setInt(i, (Integer) object);
                else if (object instanceof Long) 
                    statement.setLong(i, (Long) object);
                else
                    System.out.println("Column type can not recognize!!!");
                i++;
            } // setting up the statement by iterating the array

            flag = statement.executeUpdate();
            closeConnection();
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
        return flag;
    }


    // SUB FUNCTIONS
    //this function for the selling history window
    //transactions: for the time range and the default time range 
    public static List<Object[]> getHistory (String sDate ,String eDate)throws SQLException{ 
        List <Object[]> rows = new ArrayList<>();
        ResultSet results = null;
        
        String query = "SELECT t.transaction_id, t.date AS date, c.customer_name AS customer_name, " +
        "c.customer_contact AS customer_number, c.address AS address, c.email_address as email," +
        "s.stock_id AS sid, s.category_id AS cid, cat.category_name AS category, " +
        "clr.color_name AS color, sz.size_name AS size, ti.quantity AS count, " +
        "ti.amount " +
        "FROM transaction t " +
        "LEFT JOIN transaction_items ti ON t.transaction_id = ti.transaction_id " +
        "LEFT JOIN stock s ON ti.stock_id = s.stock_id " +
        "LEFT JOIN color clr ON s.color_id = clr.color_id " +
        "LEFT JOIN category cat ON s.category_id = cat.category_id " +
        "LEFT JOIN size sz ON s.size_id = sz.size_id " +
        "LEFT JOIN customer c ON t.customer_id = c.customer_id " +
        "WHERE t.date BETWEEN ? AND ?;";
                
        results = SQLManagerNew.getResult(query,sDate,eDate);
        while(results.next())rows.add(new Object[]{
            results.getInt("transaction_id"), 
            results.getString("date"), 
            results.getString("customer_name"), 
            results.getString("customer_number"), 
            results.getString("address"),
            results.getString("email"), 
            results.getInt("sid"), 
            results.getInt("cid"), 
            results.getString("category"), 
            results.getString("color"), 
            results.getString("size"), 
            results.getInt("count"),
            results.getDouble("amount")    
        });
        SQLManagerNew.closeConnection();
        return rows;
    }


    // gets all the stock in the inventory
    // this function is used by the transactions window to show up the current
    // availability in the invetory for the user
    public static List<Object[]> getCurrentAvailability() {
        String query = "SELECT stk.stock_id as stock_id , cat.category_name as category_name , siz.size_name as size , col.color_name as color ,\n"
                + //
                "       stk.quantity AS initial_quantity,\n" + //
                "       (stk.quantity - COALESCE(SUM(tsj.quantity), 0)) AS available_quantity,\n" + //
                "       stk.selling_price as selling_price\n" + //
                "FROM stock stk\n" + //
                "JOIN category cat ON stk.category_id = cat.category_id\n" + //
                "JOIN color col ON stk.color_id = col.color_id\n" + //
                "JOIN size siz ON stk.size_id = siz.size_id\n" + //
                "LEFT JOIN transaction_items tsj ON stk.stock_id = tsj.stock_id\n" + //
                "GROUP BY stk.stock_id, cat.category_name, siz.size_name, col.color_name, stk.selling_price\n" +
                "ORDER BY stock_id;";

        List<Object[]> rows = new ArrayList<Object[]>();
        try {
            statement = getConnection().prepareStatement(query);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Object[] row = {
                        results.getInt("stock_id"),
                        results.getString("category_name"),
                        results.getString("size"),
                        results.getString("color"),
                        results.getInt("initial_quantity"),
                        results.getInt("available_quantity"),
                        results.getDouble("selling_price")
                };
                rows.add(row);
            }
            return rows;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }


    // gets availability of a stock in inventory
    public static int isItemAvailable(int sid, int count) {
        String query = "SELECT  (stk.quantity - COALESCE(SUM(tsj.quantity), 0)) AS available_quantity \n" + //
                "FROM stock stk\n" + //
                "LEFT JOIN transaction_items tsj ON stk.stock_id = tsj.stock_id \n" + //
                "WHERE stk.stock_id = ?;";
        String checkQuery = "SELECT COUNT(*) AS count FROM stock WHERE stock_id = ?";

        int quantity = 0;
        int rowCount = 0;
        try {
            // getting the existency of the stock id
            PreparedStatement checkStatement = getConnection().prepareStatement(checkQuery);
            checkStatement.setInt(1, sid);
            ResultSet checkResult = checkStatement.executeQuery();
            while (checkResult.next())
                rowCount = checkResult.getInt("count");

            if (rowCount > 0) {
                statement = getConnection().prepareStatement(query);
                statement.setInt(1, sid);
                ResultSet result = statement.executeQuery();
                while (result.next())
                    quantity = result.getInt("available_quantity");

                if (quantity < count)
                    return -1;
                else if (quantity >= count)
                    return 1;
                else
                    return 0;
            } else {
                System.out.println("sid doesn't exist!");
                return -2;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return -1;
        }
    }


    // this functions help to keep track of the highest id in below table
    // stock id
    public static int getLastCid() {
        String query = "SELECT MAX(Category_id) AS max_id FROM category";
        int id = -1;
        try {
            statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                id = result.getInt("max_id");
            }
            if (connection != null)
                connection.close();
            return id;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            return id;
        }
    }
    // color id
    public static int getLastColorid() {
        String query = "SELECT MAX(color_id) AS max_id FROM color";
        int id = -1;
        try {
            statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                id = result.getInt("max_id");
            }
            if (connection != null)
                connection.close();
            return id;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            return id;
        }
    }
    // size id
    public static int getLastSizeid() {
        String query = "SELECT MAX(size_id) AS max_id FROM size";
        int id = -1;
        try {
            statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                id = result.getInt("max_id");
            }
            if (connection != null)
                connection.close();
            return id;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            return id;
        }
    }
    // transaction id
    public static int getLastTid() {
        String query = "SELECT MAX(transaction_id) AS max_id FROM transaction";
        int id = -1;
        try {
            statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                id = result.getInt("max_id");
            }
            if (connection != null)
                connection.close();
            return id;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            return id;
        }
    }
    // customer id
    public static int getLastCustomerid() {
        String query = "SELECT MAX(customer_id) AS max_id FROM customer";
        int id = -1;
        try {
            statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                id = result.getInt("max_id");
            }
            if (connection != null)
                connection.close();
            return id;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            return id;
        }
    }
    // stock id
    public static int getLastSid() {
        String query = "SELECT MAX(Stock_id) AS max_id FROM stock";
        int id = -1;
        try {
            statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                id = result.getInt("max_id");
            }
            if (connection != null)
                connection.close();
            return id;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            return id;
        }
    }

    // gets the selling price for the sid;
    public static double getSellingPrice(int sid) {
        String query = "select selling_price from stock where stock_id = ?";
        double selling_price = 0.0;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, sid);
            ResultSet dataSet = statement.executeQuery();
            while (dataSet.next()) {
                selling_price = dataSet.getDouble("selling_price");
            }
            connection.close();
            return selling_price;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return 0.0;
        }
    }
    
    // gets the customer id
    protected static int getCustomerId(String customer_name, String customer_contact, String customer_address) {
        int customer_id = -1;
        String query = "select customer_id from customer where customer_name = ? and customer_contact = ? and address = ?";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, customer_name);
            statement.setString(2, customer_contact);
            statement.setString(3, customer_address);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                customer_id = result.getInt("customer_id");
            }
            connection.close();
            return customer_id;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customer_id;
    }
    // gets cid for the category name
    public static int getCid(String cname) {
        String query = "SELECT category_id FROM category where category_name = ?";
        int category_id = -1;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setString(1, cname);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                category_id = result.getInt("category_id");
            }
            return category_id;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            return category_id;
        }
    }
    // gets color_id
    public static int getColorId(String color) {
        int color_id = -1;
        try {
            String query = "select color_id as id from color where color_name = ?";
            statement = getConnection().prepareStatement(query);
            statement.setString(1, color);
            ResultSet results = statement.executeQuery();
            while (results.next()) color_id = results.getInt("id");
            return color_id;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return color_id;
    }
    // gets size_id
    public static int getSizeId(String size) {
        int size_id = -1;
        try {
            String query = "select size_id from size where size_name = ?";
            statement = getConnection().prepareStatement(query);
            statement.setString(1, size);
            ResultSet result = statement.executeQuery();
            while (result.next())
                size_id = result.getInt("size_id");
            return size_id;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return size_id;
    }
    
    // 20240804 
    // gets cateogry name for the sid
    public static String getCategoryName(int sid){
        String category = "category doesn't exist.";
        String query = "select category_name from category where category_id = ?";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, sid);
            ResultSet results = statement.executeQuery();
            while (results.next()) category = results.getString("category_name");
            closeConnection();
            return category;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return category;
        }
    }
    // gets color name
    public static String getColorName(int colorId){
        String color = "color doesn't exist.";
        String query = "select color_name from color where color_id = ?";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, colorId);
            ResultSet results = statement.executeQuery();
            while (results.next()) color = results.getString("color_name");
            closeConnection();
            return color;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return color;
        }

    }
    // gets size name
    public static String getSizeName(int sizeId){
        String size = "size doesn't exist.";
        String query = "select size_name from size where size_id = ?";
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1, sizeId);
            ResultSet results = statement.executeQuery();
            while (results.next()) size = results.getString("size_name");
            closeConnection();
            return size;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return size;
        }

    }


    // gets category name from the category table (used for dropdown)
    public static List<String> getCategoryNames() {
        String query = "SELECT category_name FROM category";
        List<String> categories = new ArrayList<>();
        try {
            statement = getConnection().prepareStatement(query);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                categories.add(results.getString("category_name"));
            }
            if (connection != null)
                connection.close();
            return categories;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return categories;
    }
    // gets the color in the table
    public static List<String> getColors() {
        String query = "SELECT color_name FROM color";
        List<String> colors = new ArrayList<>();
        try {
            statement = getConnection().prepareStatement(query);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                colors.add(results.getString("color_name"));
            }
            if (connection != null)
                connection.close();
            return colors;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return null;
        }
    }
    // get sizes from the size table
    public static List<String> getSizes() {
        List<String> sizes = new ArrayList<>();
        String query = "select size_name from size";
        try {
            statement = getConnection().prepareStatement(query);
            ResultSet results = statement.executeQuery();
            while (results.next())
                sizes.add(results.getString("size_name"));
            if (connection != null)
                connection.close();
            return sizes;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return sizes;
    }
    //getting the stock info for the stock id
    public static Object[] getStockInfo(int stockId){
        String query = "select * from stock where stock_id = ?";
        Object[] array;
        try {
            statement = getConnection().prepareStatement(query);
            statement.setInt(1,stockId);
            ResultSet results = statement.executeQuery();
            while(results.next()) {
                array = new Object[]{results.getInt("stock_id"),
                    results.getInt("category_id"),
                    results.getInt("color_id"),
                    results.getInt("size_id"),
                    results.getInt("quantity"),
                    results.getDouble("buying_price"),
                    results.getDouble("selling_price"),
                    results.getString("buying_date")
                };
                return array;
            }
            statement.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(SQLManagerNew.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    // INSERTING NEW CATEGORY
    public static void addNewCategory(String categoryName) {
        int rowCount = 0;
        String checkQuery = "SELECT COUNT(*) AS count FROM category WHERE category_name = ?";
        try {
            PreparedStatement checkStatement = getConnection().prepareStatement(checkQuery);
            checkStatement.setString(1, categoryName.toLowerCase());
            ResultSet result = checkStatement.executeQuery();
            while (result.next())
                rowCount = result.getInt("count");
            if (rowCount == 0) {
                String query = "insert into category(category_id, category_name) values (?,?)";
                int category_id = getLastCid()+1;
                insertData(query, new Object[] { category_id ,categoryName.toLowerCase() });
            }
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }

    }
    // INSERTING NEW COLOR
    public static void addColor(String color) {
        try {
            int rowCount = 0;
            String checkQuery = "SELECT COUNT(*) AS count FROM color WHERE color_name = ?";
            PreparedStatement checkStatement = getConnection().prepareStatement(checkQuery);
            checkStatement.setString(1, color.toLowerCase());
            ResultSet checkResult = checkStatement.executeQuery();
            while (checkResult.next())
                rowCount = checkResult.getInt("count");
            if (rowCount == 0) {
                String query = "insert into color(color_id, color_name) values (?,?)";
                insertData(query, new Object[] {getLastColorid()+1, color.toLowerCase()});
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }
    // INSERTING NEW SIZE
    public static void addSize(String size) {
        int rowCount = 0;
        String checkQuery = "SELECT COUNT(*) AS count FROM size WHERE size_name = ?";
        try {
            PreparedStatement checkStatement = getConnection().prepareStatement(checkQuery);
            checkStatement.setString(1, size.toLowerCase());
            ResultSet result = checkStatement.executeQuery();
            while (result.next())
                rowCount = result.getInt("count");
            if (rowCount == 0) {
                String query = "insert into size(size_id, size_name) values (?,?)";
                int size_id = getLastSizeid()+1;
                insertData(query, new Object[] { size_id, size.toLowerCase()});
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }
    // adding new stock
    public static void addNewStock(int stock_id, String category, String size, String color, int quantity, double buying_price,
            double selling_price, String buying_date) {
        String query = "insert into stock(\n" +
                "stock_id,\n" +
                "category_id,\n" +
                "color_id,\n" +
                "size_id,\n" +
                "quantity,\n" +
                "buying_price,\n" +
                "selling_price,\n" +
                "buying_date)\n" +
                "values (?,?,?,?,?,?,?,?)";
        int flag = insertData(query, new Object[] {
                stock_id,
                getCid(category),
                getColorId(color),
                getSizeId(size),
                quantity,
                buying_price,
                selling_price,
                buying_date });
        if(flag == 1)System.out.println("new stock added");
        else System.out.println("stock wasnt added");
    }
    // transaction
    public static void doTransaction(String date, String customer_name, String customer_address,
        String customer_tel_number, List<Integer> stock_ids, List<Integer> quantities) {
        String queryCustomer = "insert ignore into customer(customer_id, customer_name, customer_contact, address) values (?,?,?,?)";
        String queryTransaction = "insert into transaction(transaction_id, customer_id, date, total_amount) values (?,?,?,?)";
        String queryTransactionItem = "insert into Transaction_Items(transaction_id,stock_id,quantity,amount) values (?,?,?,?)";
        List<Double> amountOfSelectedStocks = new ArrayList<>();

        // inserting customer details
        insertData(queryCustomer,
                new Object[] { getLastCustomerid() + 1, customer_name, customer_tel_number, customer_address });

        // calculating the total amount
        int i = 0;
        int quantity = 0;
        double total_amount = 0.0;
        double amountForWorkingStock = 0.0;
        double selling_price = 0.0;
        for (int stock_id : stock_ids) {
            quantity = quantities.get(i);
            selling_price = getSellingPrice(stock_id);
            amountForWorkingStock = selling_price * quantity;
            amountOfSelectedStocks.add(amountForWorkingStock);
            total_amount += amountForWorkingStock;
            i++;
        }

        // inserting into transaction
        int customer_id = getCustomerId(customer_name, customer_tel_number, customer_address);
        int transaction_id = getLastTid()+1;
        insertData(queryTransaction, new Object[] {transaction_id, customer_id, date, total_amount });

        // inserting into transaction item table (junction)
        int i1 = 0;
        for (int stock_id : stock_ids) {
            insertData(queryTransactionItem, new Object[] { transaction_id, stock_id, quantities.get(i1),
                    getSellingPrice(stock_id) * quantities.get(i1) });
            i1++;
        }

    }

    //delete func
    //delete transaction (customer include)
    public static int deleteTransaction(int tid){
        int flag1 = 0;
        int flag2 = 0;
        String query1 = "DELETE FROM transaction_items WHERE transaction_id = ? ;";
        String query2 = "DELETE FROM transaction WHERE transaction_id = ? ;";
        flag1 = insertData(query1, new Object[] {tid});
        if(flag1>=0){
            flag2 = insertData(query2, new Object[] {tid});
            if(flag2==0 && flag1==0){
                return -2;
            }
            return 1;
        }
        return -1;
    }
    //delete stock
    public static int deleteStock(int sid){
        try{
            String query1 = "Select count(stock_id) as count from transaction_items where stock_id = ?;";
            String query2 = "DELETE FROM stock WHERE stock_id = ? ;";
            
            //cheking if has transactions
            int count = 0;
            ResultSet result = getResult(query1, new Object[]{sid});
            while(result.next()) count = result.getInt("count");
            if(count == 0){
                insertData(query2, new Object[]{sid});
                return 1;  
            } 
            else return -1; 
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return -1;
        }
    }


    //danger zone
    //delete category
    public static int deleteCategory(String category){
        try {
            String lowercategory = category.toLowerCase();
            int category_id = getCid(lowercategory);
            String query1 = "Select count(stock_id) as count from stock where category_id = ?;";
            String query2 = "DELETE FROM category WHERE category_id = ? ;";
    
            int count = 0;
            ResultSet result = getResult(query1, new Object[]{category_id});
    
            while(result.next()) count = result.getInt("count"); 
    
            if (count == 0) {
                insertData(query2,new Object[]{category_id});
                return 1;
            }
            else return -1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }
    //delete color
    public static int deleteColor(String color){
        try {
            String lowerColor = color.toLowerCase();
            String query1 = "Select count(stock_id) as count from stock where color_id = ?;";
            String query2 = "DELETE FROM color WHERE color_id = ? ;";
            int color_id = getColorId(lowerColor);
            int count = 0;
            ResultSet result = getResult(query1, new Object[]{color_id});
            while (result.next()) count = result.getInt("count");
            if(count == 0) {
                insertData(query2, new Object[]{color_id});
                return 1;
            }
            else{
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }
    //delete size
    public static int deleteSize(String size){
        try {
            String lowerSize = size.toLowerCase();
            String query1 = "Select count(size_id) as count from stock where size_id = ?;";
            String query2 = "DELETE FROM size WHERE size_id = ? ;";
            int size_id = getColorId(lowerSize);
            int count = 0;
            ResultSet result = getResult(query1, new Object[]{size_id});
            while (result.next()) count = result.getInt("count");
            if(count == 0) {
                insertData(query2, new Object[]{size_id});
                return 1;
            }
            else{
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }
}