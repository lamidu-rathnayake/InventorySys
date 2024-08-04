package JujubesInventoryManager.Backend.classes;

public class Customer {
    private int customer_id;
    private String name;
    private int contact;
    private String address;
    private String email;

    Customer(int cid, String name, int contact, String address, String email){
        this.customer_id = cid;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.email = email;
    }

    public int getCustomer_id(){
        return this.customer_id;
    }
    public void setCustomer_id(int cid){
        this.customer_id = cid;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getContact(){
        return this.contact;
    }
    public void setContact(int contact){
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
