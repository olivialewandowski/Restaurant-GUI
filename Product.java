package project;

public class Product
{
   //state
   private String name;
   private int quantity;
   private double price;
   private int minThreshold;


   //constructors
   //no-arg constructor
   public Product()
   {
       //defaults set to null at this point - could give them default values here if applicable
       this.name = null;
       this.quantity = 0;
       this.price = 0.0;
       this.minThreshold = 0;
   }
  
   //all args
   public Product(String name, int quantity, double price, int minThreshold) 
   {
       this.name = name;
       this.quantity = quantity;
       this.price = price;
       this.minThreshold = minThreshold;
   }


   //getters - aka accessors
   public String getName()
   {
       return name;
   }
  
   public int getQuantity()
   {
       return quantity;
   }
  
   public double getPrice()
   {
       return price;
   }
  
   public int getMinThreshold()
   {
       return (int)(quantity * 0.1);
   }


   //setters - aka mutators
   public void setName(String name)
   {
       this.name = name;
   }
  
   public void setQuantity(int quantity)
   {
       this.quantity = quantity;
   }
  
   public void setPrice(double price)
   {
       this.price = price;
   }
  
   public void setMinThreshold(int minThreshold)
   {
       this.minThreshold = minThreshold;       //10% of quantity of product stored will be minThreshold
   }


   //behavior -
   @Override
   public String toString()
   {
       return String.format("%s (quantity: %d, price: %.2f, min threshold: %d)",
           name, quantity, price, minThreshold);
   }


}
