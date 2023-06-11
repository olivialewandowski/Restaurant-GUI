package project;


public class Manager
{
   //state
   private String name;
   private int years;
   private Location location;
   private double salary;


   //constructors
   //no-arg constructor
   public Manager()
   {
       //defaults set to null at this point - could give them default values here if desired
       this.name = "Joe";
       this.years = 0;
       this.location = null;
       this.salary = 50000.00;
   }
  
   //all args
   public Manager(String name, int years, Location location, double salary)
   {
       this.name = name;
       this.years = years;
       this.location = location;
       this.salary = salary;
   }

   //loader constructor
   public Manager(String name, int years, double salary)
   {
       this.name = name;
       this.years = years;
       this.salary = salary;
   }


   //getters - aka accessors
   public String getName()
   {
       return name;
   }
  
   public int getYears()
   {
       return years;
   }
  
   public Location getLocation()
   {
       return location;
   }
  
   public double getSalary()
   {
       return salary;
   }


   //setters - aka mutators
   public void setName(String name)
   {
       this.name = name;
   }
  
   public void setYears(int years)
   {
       if (years >= 0)
           this.years = years;
   }
  
   public void setLocation(Location location)
   {
       this.location = location;
   }
  
   public void setSalary(double salary)        //implying that the managers are salaried employees rather than hourly employees
   {
       this.salary = salary;
   }


   //behavior -
   @Override
   public String toString()
   {
       return String.format("%s (%d years of experience), located at %s, with a salary of %.2f",
           name, years, location, salary);
   }
}

