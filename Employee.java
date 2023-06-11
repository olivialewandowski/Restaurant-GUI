package project;
import java.util.*;

//add a date hired field?

public class Employee 
{
 public static void main(String[] args)
 {

 }

 private String name;
 private int years;
 private Location location;
 private double wage;
 private int[] hours;
 private Date hiringDate;

 public Employee()
 {
     name = "John Doe";
     years = 0;
     wage = 15.00;
     hours = new int[24];
     hiringDate = new Date();
 }

 public Employee(String name, int years, double wage, Location location)
 {
     this.name = name;
     this.years = years;
     this.wage = wage;
     this.location = location;
     hours = new int[24];
     hiringDate = new Date();
 }

 // the loading arg
 public Employee(String name, int years, double wage, int[] hours, Long millis)
 {
     this.name = name;
     this.years = years;
     this.wage = wage;
     this.hours = hours;
     hiringDate = new Date(millis);
 }

 public Employee(String name, int years, double wage, int[] hours)
 {
     this.name = name;
     this.years = years;
     this.wage = wage;
     this.hours = hours;
     hiringDate = new Date();
 }

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

 public String getHours()
 {
     String rString = "Hours for  " + name + "\n";
     for (int i = 0; i < 24; i++)
     {
         if (hours[i] == 1)
         {
             rString += i + ":00 - ";
             for (int j = i + 1; j < 24; j++)
             {
                 if (hours [j] == 0)
                 {
                     rString += j + ":00" + "\n";
                     i = j;
                     break;
                 }
                 if (j == 23)
                 {
                     rString += "24:00" + "\n";
                     i = j;
                     break;
                 }
                 
             }
         }
     }

     int shiftNum = 0;
     for (int i = 0; i < hours.length; i++)
     {
         if (hours[i] == 1)
             shiftNum += 1;
     }

     rString = rString + "Total Hours: " + shiftNum; 

     return rString;
 }

 public int getHoursInt()
 {
     int r = 0;
     for (int i : hours)
     {
         if (i == 1)
             r++;
     }

     return r;
 }

 public int[] getHoursArray()
 {
     return hours;
 }

 public double getWage()
 {
     return wage;
 }

 public double getWeeklySalary()
 {
     int shiftNum = 0;

     for (int i = 0; i < hours.length; i++)
     {
         if (hours[i] == 1)
             shiftNum += 1;
     }

     return shiftNum * wage * 5; // for a five-day work week
 }

 public Date getDate()
 {
     return hiringDate;
 }

 public void setName(String name)
 {
     this.name = name;
 }

 public void setYears(int years)
 {
     if (years > 0)
         this.years = years;
 }

 public void setWage(double wage)
 {
     if (wage > 12.50)       // whatever minimum wage is... maybe tie this to location?
         this.wage = wage;
 }

 public void setHours(int[] toAdd)  // an array of the timeslots the user wants to add (e.g. [6, 7, 8] means you work from 6-9)
 {
     for (int i = 0; i < toAdd.length; i++)
     {
         hours[toAdd[i]] = 1;
     }
 }

 public void setLocation(Location location)
 {
     this.location = location;
 }

 public void resetHours()
 {
     for (int i = 0; i < hours.length; i++)
     {
         hours[i] = 0;
     }
 }

}