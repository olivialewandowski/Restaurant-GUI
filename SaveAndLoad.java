package project;
//forbidden characters in any name of fucking anything are (& and , and ; and !)
// do not use these in ANY name EVER

import java.util.*;
import java.io.*;


public class SaveAndLoad 
{
public static void main(String[] args)
{
    //System.out.println(loadFile().length);
    saveFile("saveTest", loadFile("readTest"));
}

// loads data from a text file
public static Location[] loadFile(String toRead)
{
    String fullFile = "";
    try
    {
        // create file instance
        String filename = toRead + ".txt";

        File file = new File(filename);

        // create Scanner object to read the file
        Scanner input = new Scanner(file);

        // the Scanner object has this neat method that tells us when there is something left to read
        while(input.hasNext())
        {
            fullFile += input.nextLine();
        }
        // we have to close our scanner object, because the file can be damaged if we dont, i guess
        input.close();
    }
    catch(IOException ex)
    {
        System.out.print(ex.getMessage());
    }

    String[] rawLocations = fullFile.split("!");

    Location[] r = new Location[rawLocations.length];

    Employee[] employees;
    OrderQueue orders;
    Product[] inventory;
    String[] menu;
    String[] locString;
    Manager manager;

    for (int i = 0; i < rawLocations.length; i++)
    {
        employees = scrapeEmployees(rawLocations[i]);
        inventory = scrapeProducts(rawLocations[i]);
        orders = scrapeOrders(rawLocations[i], inventory);
        menu = scrapeMenu(rawLocations[i]);
        locString = scrapeLocationStrings(rawLocations[i]);
        manager = scrapeManager(rawLocations[i]);

        //Location(Manager manager, String name, String address, Employee[] employees, Product[] inventory, OrderQueue orders) 
        r[i] = new Location(manager, locString[0], locString[1], employees, inventory, orders, menu);
    }

    for ( Location location : r)
    {
        for ( Employee employee : location.getEmployees() )
        {
            employee.setLocation(location);
        }

        location.getManager().setLocation(location);
    }

    return r;

}

// this pulls the employees from the text, returns a list (to be used in the Location constructor)
public static Employee[] scrapeEmployees(String data)
{
    String[] employees = data.split("&");
    String[] args;
    String[] hours;
    int[] intHours = new int[24];
    ArrayList<Employee> r = new ArrayList<Employee>();

    int i = 0;
    while (!(employees[i].equals("endEmployee")))
    {
        args = employees[i].split(",");
        hours = args[3].split(";");
        for (int j = 0; j < 24; j++)
        {
            intHours[j] = (Integer.parseInt(hours[j]));
        }

        // no location for now. Will be added later.
        r.add(new Employee(args[0], Integer.parseInt(args[1]), Double.parseDouble(args[2]), intHours, Long.parseLong(args[4])));
        i++;
    }

    Object[] r2 = r.toArray();
    Employee[] r3 = new Employee[r2.length];

    for (int p = 0; p < r3.length; p++)
    {
        r3[p] = (Employee)r2[p];
    }

    return r3;
}  

// this pulls orders from the text, returns an orderQueue
public static OrderQueue scrapeOrders(String data, Product[] products)

{
    //public Order(int id, String status, Date orderDate, String name, String contact, String address) 
    OrderQueue r = new OrderQueue();

    String[] orders = data.split("&");
    String[] args;

    int i = 0;

    while ( !(orders[i].equals("endInventory")) )
    {
        i++;
    }
    i++;

    String[] orderedItems;
    ArrayList<Product> arrListProd;
    while ( !(orders[i].equals("endOrder")) )
    {
        args = orders[i].split(",");

        orderedItems = args[6].split(";");
        arrListProd = new ArrayList<Product>();
        for (String itemName : orderedItems) // [Lasagna, Tiramisu]
        {
            for (Product soldItem : products)
            {
                if ( itemName.equals(soldItem.getName()))
                {
                    arrListProd.add(soldItem);
                }
            }
        }

        r.enqueue(new Order(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2]), args[3], args[4], args[5], arrListProd));
        i++;
    }
    
    return r;

}

// pulls the inventory
public static Product[] scrapeProducts(String data)
{
    ArrayList<Product> r = new ArrayList<Product>();

    String[] products = data.split("&");
    String[] args;

    int i = 0;

    while ( !(products[i].equals("endEmployee")) )
    {
        i++;
    }
    i++;

    while ( !(products[i].equals("endInventory")) )
    {
        args = products[i].split(",");
        r.add(new Product(args[0], Integer.parseInt(args[1]), Double.parseDouble(args[2]), Integer.parseInt(args[3])));
        i++;
    }
    
    Object[] r2 = r.toArray();
    Product[] r3 = new Product[r2.length];

    for (int p = 0; p < r3.length; p++)
    {
        r3[p] = (Product)r2[p];
    }

    return r3;
}

// pulls the menu
public static String[] scrapeMenu(String data)
{
    String[] lines = data.split("&");
    String[] menu;

    int i = 0;

    while ( !(lines[i].equals("endOrder")) )
    {
        i++;
    }
    i++;

    menu = lines[i].split(",");

    return menu;
}

// pulls name and address
public static String[] scrapeLocationStrings(String data)
{
    String[] lines = data.split("&");
    String[] locationString;

    int i = 0;

    while ( !(lines[i].equals("endManager")) )
    {
        i++;
    }
    i++;

    locationString = lines[i].split(",");

    return locationString;
}

// pulls the manager
public static Manager scrapeManager(String data)
{
    String[] lines = data.split("&");

    int i = 0;

    while ( !(lines[i].equals("endMenu")) )
    {
        i++;
    }
    i++;

    String[] args = lines[i].split(",");

    return new Manager(args[0], Integer.parseInt(args[1]), Double.parseDouble(args[2]));
}

// SAVE DATA
public static void saveFile(String writeTo, Location[] toSave)
{
    try
    {
        String filename = writeTo + ".txt";

        // setting up for deletin'
        boolean openToAppend = false;  
        FileWriter fw = new FileWriter(filename, openToAppend);
        PrintWriter pw = new PrintWriter(fw);

        for (Location location : toSave)
        {
            writeEmployees(location.getEmployees(), pw);
            writeInventory(location.getInventory(), pw);
            writeOrders(location.getOrders(), pw);
            writeMenu(location.getMenu(), pw);
            writeManager(location.getManager(), pw);

            pw.print(location.getName() + "," + location.getAddress() + "\n");
            pw.print("!\n");
        }      


        pw.close();
    }
    catch (Exception ex)
    {
        System.out.println("File could not be accessed");
    }


}

// writes employee data to the file
public static void writeEmployees(Employee[] employees, PrintWriter pw)
{
    String append;
    for (Employee e : employees)
    {
        append = e.getName() + "," + e.getYears() + "," + e.getWage() + ",";
        for (int entry = 0; entry < e.getHoursArray().length - 1; entry++)
        {
            append = append + e.getHoursArray()[entry] + ";";
        }
        append = append + e.getHoursArray()[e.getHoursArray().length - 1] + ',';

        append = append + e.getDate().getTime() + "&" + "\n";

        pw.print(append);
    }

    pw.print("endEmployee&\n");
    pw.flush();
}

// writes order data to the file
public static void writeOrders(OrderQueue orders, PrintWriter pw)
{
    String append;

    for (int i = 0; i < orders.getNumberOfOrders(); i++)
    {
        append = "";
        append = append + orders.peek(i).getId() + "," + orders.peek(i).getStatus() + "," + 
                          orders.peek(i).getDate().getTime() + "," + orders.peek(i).getName() + "," +
                          orders.peek(i).getContact() + "," + orders.peek(i).getAddress() + ",";

        for (int j = 0; j < orders.peek(i).getItems().size() - 1; j++)
        {
            append = append + orders.peek(i).getItems().get(j).getName() + ";";
        }
        append = append + orders.peek(i).getItems().get(orders.peek(i).getItems().size() - 1).getName();
                                    
        append = append + "&" + "\n";

        pw.print(append);
    }

    pw.print("endOrder&\n");
    pw.flush();
}

// writes inventory data to the file
public static void writeInventory(Product[] inventory, PrintWriter pw)
{
    String append;

    for (int i = 0; i < inventory.length; i++)
    {
        append = "";
        append = append + inventory[i].getName() + "," + inventory[i].getQuantity() + "," + 
                          inventory[i].getPrice() + "," + inventory[i].getMinThreshold() + "&" + "\n";

        pw.print(append);
    }

    pw.print("endInventory&\n");
    pw.flush();
}

// writes menu data to the file
public static void writeMenu(String[] menu, PrintWriter pw)
{
    String append = "";

    for (int i = 0; i < menu.length - 1; i++)
    {
        append += menu[i];
        append += ",";
    }
    append += menu[menu.length - 1];
    append += "&\n";

    pw.print(append);
    pw.print("endMenu&\n");
    pw.flush();
}

// writes manager data to the file
public static void writeManager(Manager manager, PrintWriter pw)
{
    String append = "";

    append = append + manager.getName() + "," + manager.getYears() + "," + manager.getSalary() + "&\n";

    pw.print(append);
    pw.print("endManager&\n");
    pw.flush();
}

}