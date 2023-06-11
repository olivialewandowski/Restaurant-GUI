package project;
import java.util.*;

public class Location
{
    private Manager manager;
    private String address;
    private String name;
    private Employee[] employees;
    private Product[] inventory;
    private static int numOfLocations;
    private OrderQueue orders;
    private String[] menu;

    public Location(Manager manager, String name, String address, Employee[] employees, Product[] inventory, OrderQueue orders, String[] menu) 
    {
        this.manager = manager;
        this.name = name;
        this.address = address;
        this.employees = employees;
        this.inventory = inventory;
        this.orders = orders;
        this.menu = menu;
    }

    public Location() 
    {
        name = "Johns Pit Stop";
        address = "123 Main Street, Westwood AZ 21039";
        
    }

    public static int getNumOfLocations() 
    {
        return numOfLocations;
    }

    public String getAddress()
    {
        return address;
    }

    public Employee[] getEmployees() 
    {
        return employees;
    }

    public Manager getManager() 
    {
        return manager;
    }

    public String getName()
    {
        return name;
    }

    public Product[] getInventory() 
    {
        return inventory;
    }

    public OrderQueue getOrders()
    {
        return orders;
    }

    public String[] lowInventory() 
    {

        // return an array of the products in low inventory
        // for example, products with quantity less than 10
        String[] lowItems = new String[inventory.length];
        int index = 0;
        for (Product p : inventory) {
            if (p.getQuantity() <= p.getMinThreshold()) 
            {
                lowItems[index++] = p.getName();
            }
        }
        return Arrays.copyOf(lowItems, index);
    }

    public String[] getMenu() 
    {
    	return menu;
    }

    public void clearInventory()
    {
    	for (Product p : inventory) 
    	{
            p.setQuantity(0);
        }
    }

    public void clearEmployees() 
    {
          // clear the employees array
        employees = new Employee[0];
    }

    public void addEmployee(Employee emp)
    {
    	  employees = Arrays.copyOf(employees, employees.length + 1);
          employees[employees.length - 1] = emp;
    }

    public void removeEmployee(String empName)
    {
        // only executes if the employee exists
        if (this.employeeExist(empName))
        {
            Employee[] newList = new Employee[employees.length -1];

            int j = 0;
            for (Employee emp : employees)
            {
                if (! (emp.getName().equals(empName)) ) 
                {
                    newList[j] = emp;
                    j++;
                }
            }

            employees = newList;
        }

    }

    public void setEmployeeList(Employee[] employees)
    {
        this.employees = employees;
    }

    public boolean employeeExist(String empName)
    {
        boolean r = false;
        for (Employee emp : employees)
        {
            if (emp.getName().equals(empName))
            {
                r = true;
            }
        }

        return r;
    }

    public void setManager(Manager man)
    {
        this.manager = man;
    }

    public double calculateWeeklyWages() 
    {
    	// calculate the total weekly wages of all employees
        double totalWages = 0;
        for (Employee emp : employees) 
        {
            totalWages += emp.getWage();
        }
        return totalWages;
    }

    @Override
    public String toString() 
    {
    	 return "Welcome to" + name + " at " + address + "!";
    }
}