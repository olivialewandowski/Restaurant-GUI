package project;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// add interface "human" for employee and manager to implement?


public class Execution extends JFrame
{
    Location[] locationList;

    Employee[][] employeeList;

    // labels
    private JLabel      locationLabel, managerLabel, employeeLabel, hoursLabel, wageLabel, weeklyPayLabel;
    private JTextField  managerText, hoursText, wageText, weeklyPayText;
    private JTextArea   interactZone;

    // drop down menus
    private final JComboBox<String> locationCombo = new JComboBox<>();
    private final JComboBox<String> employeeCombo = new JComboBox<>();

    // menus
    private JButton clearButton, /* **removed** confirmButton,*/ menuButton, ordersButton, addButton, removeButton, changeMaButton, checkWagesButton, summaryButton, quitButton, updateWageButton;

    //panel
    private JPanel panel;


    private final int windowHeight = 200;
    private final int windowWidth = 500;

    // constructor
    public Execution(String filename)
    {
        locationList = SaveAndLoad.loadFile(filename);
        employeeList = new Employee[locationList.length][];
        for (int empList = 0; empList < employeeList.length; empList++)
        {
            employeeList[empList] = locationList[empList].getEmployees();
        }
        
        locationCombo.addItem("Select Location...");
        for (int lIndex = 0; lIndex < locationList.length; lIndex++)
        {
            locationCombo.addItem(locationList[lIndex].getName());
        }

        //title, height, and width
        setTitle("Restaurant Manager"); 
        setSize(windowWidth, windowHeight);

        // close behavior
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildPanel();
        add(panel);

        pack();

		setVisible(true);
    }

    // adding all the buttons and their formatting, no functionality, really
    private void buildPanel()
	{
        // layout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        //create the panel
		panel = new JPanel(layout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

		// location label
		locationLabel = new JLabel("            Location: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(locationLabel, gbc);

        // adding action listener to locationCombo and adding it
        locationCombo.addActionListener(new LocationSelectionListener());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(locationCombo, gbc);

        // manager label
		managerLabel = new JLabel("Manager: ");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(managerLabel, gbc);

        // employee label
		employeeLabel = new JLabel("Employee: ");
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(employeeLabel, gbc);

        // employee drop-down
        EmployeeSelectionListener employeeListenerObject = new EmployeeSelectionListener();
        employeeCombo.addActionListener(employeeListenerObject);
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(employeeCombo, gbc);
		
        // manager text box
		managerText = new JTextField(15);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(managerText, gbc);	

		//create a button with the caption -Clear-
		clearButton = new JButton("Clear");
		//add an action listener to the button
		ClearButtonListener exitListener = new ClearButtonListener();
		clearButton.addActionListener(exitListener);
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(clearButton, gbc);

        // quit button
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new quitButtonListener());
        gbc.gridx = 6;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(quitButton, gbc);
        
        //create menu button (has action)
        menuButton = new JButton("Menu");
        menuButton.addActionListener(new MenuButtonListener());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(menuButton, gbc);

        //inventory button (has action)
        ordersButton = new JButton("Orders");
        ordersButton.addActionListener(new OrdersButtonListener());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(ordersButton, gbc);

        // add hours label
        hoursLabel = new JLabel("Hours: ");
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(hoursLabel, gbc);

        // add hours text
        hoursText = new JTextField(15);
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(hoursText, gbc);
        
        // add wage label
        wageLabel = new JLabel("Wage: ");
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(wageLabel, gbc);

        // add wage text
        wageText = new JTextField(15);
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(wageText, gbc);

        // add pay label
        weeklyPayLabel = new JLabel("Weekly Pay: ");
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(weeklyPayLabel, gbc);

        // add hours text
        weeklyPayText = new JTextField(15);
        gbc.gridx = 5;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(weeklyPayText, gbc);

        // add add employee button
        addButton = new JButton("Add Empl.");
        addButton.addActionListener(new addEmployeeButtonListener());
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(addButton, gbc);
 
        // add remove employee button
        removeButton = new JButton("Remove Empl.");
        removeButton.addActionListener(new removeEmployeeButtonListener());
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(removeButton, gbc);

        // add change ma button
        changeMaButton = new JButton("Change Manager");
        changeMaButton.addActionListener(new changeManagerButtonListener());
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(changeMaButton, gbc);

        // check wages button (has action)
        checkWagesButton = new JButton("Check Wages");
        checkWagesButton.addActionListener(new CheckWagesButtonListener());
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(checkWagesButton, gbc);

        // summary button (parital functionality)
        summaryButton = new JButton("Summary");
        summaryButton.addActionListener(new SummaryButtonListener());
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        panel.add(summaryButton, gbc);
        gbc.anchor = GridBagConstraints.CENTER;

        /* removed
        // confirm button
        confirmButton = new JButton("Confirm");
        gbc.gridx = 5;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(confirmButton, gbc);
        */

        // update wage button
        updateWageButton = new JButton("Update");
        updateWageButton.addActionListener(new updateButtonListener());
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(updateWageButton, gbc);

        // adding big area
        String initialText = "";//"\n\n\n\n\n\n\n\n\n\n";
        interactZone = new JTextArea(initialText);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.gridheight = 7;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_START;
        panel.add(interactZone, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
	}

    // clear button functionality
    private class ClearButtonListener implements ActionListener				
	{
		// the actionPerformed method executes				
		//when the user clicks on the Clear button 
									
		public void actionPerformed(ActionEvent e)			
		{
            // removing action listener from employee and location combo
                // really have no idea why I have to remove both, but it works when I do so I will!!
            ActionListener addBack = new EmployeeSelectionListener();
            for (ActionListener listener : employeeCombo.getActionListeners())
            {
                employeeCombo.removeActionListener(listener);
                addBack = listener;
            }
                // doing it for location combo
            ActionListener addBack2 = new LocationSelectionListener();
            for (ActionListener listener : locationCombo.getActionListeners())
            {
                locationCombo.removeActionListener(listener);
                addBack2 = listener;
            }
		
			locationCombo.setSelectedIndex(0);
			managerText.setText(" ");
			employeeCombo.setSelectedIndex(-1);	
            interactZone.setText("");		
            hoursText.setText("");
            wageText.setText("");
            weeklyPayText.setText("");

            // adding back action listeners
            employeeCombo.addActionListener(addBack);
            locationCombo.addActionListener(addBack2);
		}									
	}

    // location drop-down functionality
    private class LocationSelectionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // setting text fields to empty
            hoursText.setText("");
            wageText.setText("");
            weeklyPayText.setText("");
            interactZone.setText("");
            managerText.setText("");

            // removing action listener from employee
            ActionListener addBack = new EmployeeSelectionListener();
            for (ActionListener listener : employeeCombo.getActionListeners())
            {
                employeeCombo.removeActionListener(listener);
                addBack = listener;
            }

            employeeCombo.removeAllItems();

            int selectedIndex = locationCombo.getSelectedIndex();

            // setting manager text field
            managerText.setText(locationList[selectedIndex - 1].getManager().getName());

            if (selectedIndex > 0)
            {
                employeeCombo.removeAllItems();
                for (int i = 0; i < employeeList[selectedIndex - 1].length; i++)
                {

                    employeeCombo.addItem(employeeList[selectedIndex - 1][i].getName());
                    employeeCombo.setSelectedIndex(-1);

                }
            }

            // adding back action listener
            employeeCombo.addActionListener(addBack);

        }
    }

    // employee drop-down functionality
    private class EmployeeSelectionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //interactZone.setText("Action listned");
            int locationIndex = locationCombo.getSelectedIndex();
            int employeeIndex = employeeCombo.getSelectedIndex();
            Employee selected = employeeList[locationIndex - 1][employeeIndex];

            hoursText.setText(Integer.toString(selected.getHoursInt()));
            wageText.setText(Double.toString(selected.getWage()));
            weeklyPayText.setText(Double.toString(selected.getWeeklySalary()));
        }
    }

    // menu button functionality
    private class MenuButtonListener implements ActionListener				
	{
		// the actionPerformed method executes				
		//when the user clicks on the Menu button 
									
		public void actionPerformed(ActionEvent e)			
		{
            int selectedIndex = locationCombo.getSelectedIndex();
            if ( ! (selectedIndex == 0))
            {
                Location location = locationList[selectedIndex - 1];
                Product[] inventory = location.getInventory();
                String set = "Menu: (item : price)\n";
                set += "-----------\n";
                for (Product item : inventory)
                {
                    set = set + item.getName() + " : " + item.getPrice() + "\n";
                }

                interactZone.setText(set);
            }
		}									
	}

    // inventory button functionality (vestigial)
    private class OrdersButtonListener implements ActionListener				
    {
        // the actionPerformed method executes				
        //when the user clicks on the Orders button 
                                    
        public void actionPerformed(ActionEvent e)			
        {
            int selectedIndex = locationCombo.getSelectedIndex();
            if ( ! (selectedIndex == 0))
            {
                Location location = locationList[selectedIndex - 1];
                OrderQueue orders = location.getOrders();
                String set = "Orders: \n";
                double totalIncome = 0;
                for (int i = 0; i < orders.getNumberOfOrders(); i++)
                {
                    Order order = orders.peek(i);
                    set = set + "ID#" + order.getId() + " :: " + "Items: ";
                    
                    for (int j = 0; j < order.getItems().size() - 1; j++)
                    {
                        set = set + order.getItems().get(j).getName() + ", ";
                    }
                    set = set + order.getItems().get(order.getItems().size() - 1).getName();
                    
                    set = set + " :: Price: " + order.getTotalIncome() + "\n";
                    totalIncome += order.getTotalIncome();
                }

                set += "-----------\n";
                set = set + "Total Number of Orders: " + orders.getNumberOfOrders() + "\n";
                set = set + "Total Income: " + totalIncome;

                interactZone.setText(set);
            }
        }									
    }

    // check wages button functionality
    private class CheckWagesButtonListener implements ActionListener				
	{
		// the actionPerformed method executes				
		//when the user clicks on the CheckWages button 
									
		public void actionPerformed(ActionEvent e)			
		{
            int selectedIndex = locationCombo.getSelectedIndex();
            Location location = locationList[selectedIndex - 1];
            if (selectedIndex != -1)
            {
                String set = "Wages: \n";
                double total = 0;
                for (Employee employee : employeeList[selectedIndex - 1])
                {
                    set = set + employee.getName() + " - " + employee.getWeeklySalary() + "\n";
                    total += employee.getWeeklySalary();
                }
                set = set + "(Manager) " + location.getManager().getName() + " - " + location.getManager().getSalary() + "\n";
                total += location.getManager().getSalary();
                set += "-----------\n";
                set = set + "Total - " + Double.toString(total);
                interactZone.setText(set);
            }
		}									
	}
    
    // gives a summary of the location (includes Order price summary)
    private class SummaryButtonListener implements ActionListener
    {
        // the actionPerformed method executes				
        //when the user clicks on the summary button 
                                    
        public void actionPerformed(ActionEvent e)			
        {
            String set = "";
            int selectedIndex = locationCombo.getSelectedIndex() - 1;

            set = set + locationList[selectedIndex].getName() + " Location, Located At: " + locationList[selectedIndex].getAddress() + "\n";
            set = set + "Manager: " + locationList[selectedIndex].getManager().getName() + "\n";
            set += "Employees: ";
            for (int i = 0; i < locationList[selectedIndex].getEmployees().length - 1; i++)
            {
                set = set + locationList[selectedIndex].getEmployees()[i].getName() + ", ";
            }
            set += locationList[selectedIndex].getEmployees()[locationList[selectedIndex].getEmployees().length - 1].getName();
            set += "\nMenu: ";
            for (int i = 0; i < locationList[selectedIndex].getMenu().length - 1; i++)
            {
                set += locationList[selectedIndex].getMenu()[i];
                set += ", ";
            }  
            set += locationList[selectedIndex].getMenu()[locationList[selectedIndex].getMenu().length - 1];
            set += "\n";

            set += "Total price of all orders: ";
            double totalPrice = 0;
            for (int i = 0; i < locationList[selectedIndex].getOrders().getNumberOfOrders(); i++)
            {
                for (Product j : locationList[selectedIndex].getOrders().peek(i).getItems() )
                {
                    totalPrice += j.getPrice();
                }
            }

            set += totalPrice;

            interactZone.setText(set);
        }	
    }

    // adds an employee to the selected location
    private class addEmployeeButtonListener implements ActionListener
    {
        // the actionPerformed method executes				
        //when the user clicks on the add employee button 
                                    
        public void actionPerformed(ActionEvent e)			
        {
            interactZone.setText("");

            // removing action listener from employee
            ActionListener addBack = new EmployeeSelectionListener();
            for (ActionListener listener : employeeCombo.getActionListeners())
            {
                employeeCombo.removeActionListener(listener);
                addBack = listener;
            }

            int selectedIndex = locationCombo.getSelectedIndex() - 1;
            Location location = locationList[selectedIndex];

            // get name
            String name = JOptionPane.showInputDialog(null, "Location: " + location.getName() + "\nEnter new employee name: ");
            if (name == null)
            {
                interactZone.setText("Action cancelled");
                // adding back action listener
                employeeCombo.addActionListener(addBack);
                return;
            }


            // get wage
            double wage;
            while (true)
            {  
                try
                {
                    String input = JOptionPane.showInputDialog(null, "Location: " + location.getName() + "\nEnter new employee wage (per hour): ");
                    if (input == null)
                    {
                        interactZone.setText("Action cancelled");
                        // adding back action listener
                        employeeCombo.addActionListener(addBack);
                        return;
                    }
                    wage = Double.parseDouble(input);
                    break;
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a double value");
                    continue;
                }
            }

            // get years
            int years;
            while (true)
            {  
                try
                {
                    String input = JOptionPane.showInputDialog(null, "Location: " + location.getName() + "\nEnter new employee years: ");
                    if (input == null)
                    {
                        interactZone.setText("Action cancelled");
                        // adding back action listener
                        employeeCombo.addActionListener(addBack);
                        return;
                    }
                    years = Integer.parseInt(input);
                    break;
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Please enter an integer value");
                    continue;
                }
            }

            // get hours
            String hoursStr;
            String valid;
            String[] shifts;
            int[] hours = new int[24];
            boolean allDigits = true;
            boolean validRange = true;
            do
            {
                hoursStr = JOptionPane.showInputDialog(null, "Location: " + location.getName() + "\nEnter new employee working hours in 24-hour time:\n" + "(e.g. 6-9, 14-16)");
                valid = hoursStr.replace("-", "");
                valid = valid.replace(" ", "");
                valid = valid.replace(",", "");

                // checking for a valid entry
                allDigits = true;
                validRange = true;
                for (char ch : valid.toCharArray())
                {
                    if (!(Character.isDigit(ch)))
                    {
                        allDigits = false;
                        JOptionPane.showMessageDialog(null, "Invalid character found");
                        break;
                    }
                }

                try
                {
                    shifts = hoursStr.split(", ");
                    hours = new int[24];
                    for (String hourRange : shifts)
                    {
                        String[] startEnd = hourRange.split("-");
                        for (int i = Integer.parseInt(startEnd[0]); i < Integer.parseInt(startEnd[1]); i++)
                        {
                            hours[i] = 1;
                            
                        }
                        
                    }
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Entered number not between 1-24");
                    validRange = false;
                }

            } while ( (allDigits == false) || (validRange == false) );

            Employee newEmp = new Employee(name, years, wage, hours);
            Employee[] newList = new Employee[employeeList[selectedIndex].length + 1];

            for (int i = 0; i < newList.length - 1; i++)
            {
                newList[i] = employeeList[selectedIndex][i];
            }
            newList[newList.length - 1] = newEmp;
            employeeList[selectedIndex] = newList;
            locationList[selectedIndex].addEmployee(newEmp);

            employeeCombo.addItem(employeeList[selectedIndex][employeeList[selectedIndex].length - 1].getName());
            /* 
            employeeCombo.removeAllItems();
            for (int i = 0; i < employeeList[selectedIndex].length; i++)
            {
                employeeCombo.addItem(employeeList[selectedIndex - 1][i].getName());
                employeeCombo.setSelectedIndex(-1);
            }
            */
        
            employeeCombo.addActionListener(addBack);
        }
        
    }

    // removes an employee from the selected location
    private class removeEmployeeButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)	
        {
            // removing action listener from employee
            ActionListener addBack = new EmployeeSelectionListener();
            for (ActionListener listener : employeeCombo.getActionListeners())
            {
                employeeCombo.removeActionListener(listener);
                addBack = listener;
            }

            int selectedIndex = locationCombo.getSelectedIndex() - 1;
            Location location = locationList[selectedIndex];

            // get name
            String name = JOptionPane.showInputDialog(null, "Location: " + location.getName() + "\nEnter name of employee to remove: ");
            if (name == null)
            {
                interactZone.setText("Action cancelled");
                // adding back action listener
                employeeCombo.addActionListener(addBack);
                return;
            }
            if (location.employeeExist(name) == false)
            {
                JOptionPane.showMessageDialog(null, "Employee does not exist, cannot remove");
                // adding back action listener
                employeeCombo.addActionListener(addBack);
                return;
            }

            // removing from location
            location.removeEmployee(name);

            // removing from employee list
            Employee[] newList = new Employee[employeeList[selectedIndex].length - 1];

           int newListCounter=0;
           for(int i=0; i<employeeList[selectedIndex].length; i++)
           {
        	   if(!(employeeList[selectedIndex][i].getName().equals(name)))
        	   {
        		   newList[newListCounter]=employeeList[selectedIndex][i];
        		   newListCounter++;
        	   }
           }

            employeeList[selectedIndex] = newList;

            // repopulating drop down menu
            employeeCombo.removeAllItems();
            for (int i = 0; i < employeeList[selectedIndex].length; i++)
            {
                employeeCombo.addItem(employeeList[selectedIndex][i].getName());
            }
            employeeCombo.setSelectedIndex(-1);

            // adding back action listener
            employeeCombo.addActionListener(addBack);
        }
    }

    // updates the wage of the selected employee
    private class updateButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)	
        {
            // removing action listener from employee
            ActionListener addBack = new EmployeeSelectionListener();
            for (ActionListener listener : employeeCombo.getActionListeners())
            {
                employeeCombo.removeActionListener(listener);
                addBack = listener;
            }

            int selectedIndex = locationCombo.getSelectedIndex() - 1;
            Location location = locationList[selectedIndex];

            boolean valid = true;
            double wage = 0;
            do
            {
                valid = true;
                try
                {
                    String wageStr = JOptionPane.showInputDialog(null, "Employee: " + employeeCombo.getSelectedItem() + "\nEnter new wage: ");
                    if (wageStr == null)
                    {
                        interactZone.setText("Action cancelled");
                        // adding back action listener
                        employeeCombo.addActionListener(addBack);
                        return;
                    }
                    wage = Double.parseDouble(wageStr);
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a double value");
                    valid = false;
                }
            } while ( valid == false);

            employeeList[selectedIndex][employeeCombo.getSelectedIndex()].setWage(wage);

            location.setEmployeeList(employeeList[selectedIndex]);

            wageText.setText(Double.toString(wage));
            weeklyPayText.setText(Double.toString(employeeList[selectedIndex][employeeCombo.getSelectedIndex()].getWeeklySalary()));

            // adding back action listener
            employeeCombo.addActionListener(addBack);

        }
    }

    // changes the manager of the selected location
    private class changeManagerButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)	
        {
            int selectedIndex = locationCombo.getSelectedIndex() - 1;
            Location location = locationList[selectedIndex];

            String name = JOptionPane.showInputDialog(null, "Location: " + location.getName() + "\nEnter new manager name: ");
            if (name == null)
            {
                interactZone.setText("Action cancelled");
                return;
            }


            // get salary
            double salary;
            while (true)
            {  
                try
                {
                    String input = JOptionPane.showInputDialog(null, "Location: " + location.getName() + "\nEnter new manager salary: ");
                    if (input == null)
                    {
                        interactZone.setText("Action cancelled");
                        return;
                    }
                    salary = Double.parseDouble(input);
                    break;
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a double value");
                    continue;
                }
            }

            // get years
            int years;
            while (true)
            {  
                try
                {
                    String input = JOptionPane.showInputDialog(null, "Location: " + location.getName() + "\nEnter new manager years: ");
                    if (input == null)
                    {
                        interactZone.setText("Action cancelled");
                        return;
                    }
                    years = Integer.parseInt(input);
                    break;
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Please enter an integer value");
                    continue;
                }
            }

            Manager newMan = new Manager(name, years, salary);
            location.setManager(newMan);

            managerText.setText(location.getManager().getName());
        }
    }

    // saves and closes the program
    private class quitButtonListener implements ActionListener
    {
        // the actionPerformed method executes				
        //when the user clicks on the add employee button 
                                    
        public void actionPerformed(ActionEvent e)	
        {
            SaveAndLoad.saveFile("src/project/readTest", locationList);
            dispose();
        }
    }

    public static void main(String[] args)
    {
        new Execution("src/project/readTest");
    }
}