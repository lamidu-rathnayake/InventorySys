package JujubesInventoryManager.Backend.classes;

public class Stock {
    private int stock_id;
    private String category;
    private String color;
    private String size;
    private int quantity;
    private double buying_price;
    private double selling_price;

    Stock(int sid, String category, String color, String size, int quantity, double buying_price, double selling_price){
        this.stock_id = sid;
        this.category = category;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.buying_price = buying_price;
        this.selling_price = selling_price;
    }

    public int getStockID(){
        return this.stock_id;
    }
    public void setStockID(int sid){
        this.stock_id = sid;
    }

    public String getCategory(){
        return this.category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public String color(){
        return this.color;
    }
    public void setColor(String color){
        this.color = color;
    }

    public String getSize(){
        return this.size;
    }
    public void setSize(String size){
        this.size = size;
    }
    
    public int getQuantity(){
        return this.quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;        
    }

    public double getBuying_price(){
        return buying_price;
    }
    public void setBuying_Price(double buying_price){
        this.buying_price = buying_price;
    }

    public double getSelling_price(){
        return this.selling_price;
    }
    public void setSelling_price(double selling_price){
        this.selling_price = selling_price;
    }

    

}
