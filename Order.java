package project;

import java.util.*;

public class Order {
	// Fields 
    private int id; // The identification for each order
    private String status; // online, take out, dine
    private ArrayList<Product> items; // list of ordered items
    private Date date; // date of the order being made
    private String name; // name of the customer 
    private String contact; // contact information of the customer
    private String address; // address of the customer
    
    //Constructors
    public Order(int id, String status, int orderDate, String name, String contact, String address) {
        this.id = id;
        this.status = status;
        this.items = new ArrayList<Product>();
        this.date = new Date(orderDate);
        this.name = name;
        this.contact = contact;
        this.address=address;
    }

    public Order(int id, String status, String name, String contact, String address) {
        this.id = id;
        this.status = status;
        this.items = new ArrayList<Product>();
        this.date = new Date();
        this.name = name;
        this.contact = contact;
        this.address=address;
    }

    // load contructor
    public Order(int id, String status, int orderDate, String name, String contact, String address, ArrayList<Product> items) {
        this.id = id;
        this.status = status;
        this.items = new ArrayList<Product>();
        this.date = new Date(orderDate);
        this.name = name;
        this.contact = contact;
        this.address=address;
        this.items = items;
    }
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int orderId) {
        this.id = orderId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public ArrayList<Product> getItems() {
        return items;
    }
    
    public void setItems(ArrayList<Product> items) {
        this.items = items;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    // add an item to the order
    public void addItem(Product item) {
        items.add(item);
    }
    
    //  remove an item from the order
    public void removeItem(OrderedItem item) {
        items.remove(item);
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    //  update the quantity of an item in the order
    public void updateItemQuantity(Product item, int quantity) {
        for (Product orderedItem : items) {
            if (orderedItem.equals(item)) {
                orderedItem.setQuantity(quantity);
                break;
            }
        }
    }
    
    // Calculate the total income of the order
    public double getTotalIncome() {
        double totalIncome = 0;
        for (Product item : items) {
            totalIncome += item.getPrice();
        }
        return totalIncome;
    }
}

class OrderedItem {
    private String itemName; // name of the ordered food/drink
    private double price; // price of the food/drink
    private int quantity; // quantity ordered
  
    public OrderedItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
    

    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getQuantity() {
        return quantity;
    }

}