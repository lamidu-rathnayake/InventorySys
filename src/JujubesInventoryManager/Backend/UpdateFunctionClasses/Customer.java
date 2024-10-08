package JujubesInventoryManager.Backend.UpdateFunctionClasses;

public class Customer {
    private int customer_id;
    private String name;
    private long contact;
    private String address;
    private String email;

    Customer(int cid, String name, long contact, String address, String email){
        this.customer_id = cid;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.email = email;
    }

    public int getCustomer_id(){
        return this.customer_id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public long getContact(){
        return this.contact;
    }
    public void setContact(long contact){
        this.contact = contact;
    }

    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    
}
