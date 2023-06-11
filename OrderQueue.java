package project;


public class OrderQueue
{
    // FIELDS
    private Order[] orders; // where the values in the queue are stored
    private int numberOfOrders;       // value indicating the number of values queued

    // CONSTRUCTORS
    // non-argument constructor that creates an array with 8 spaces (no queued values)
    public OrderQueue()
    {
        orders = new Order[8];
        numberOfOrders = 0;
    }

    // ACCESSORS
    // returns the number of items in the queue
    public int getNumberOfOrders()
    {
        return numberOfOrders;
    }

    // BEHAVIOR
    // adds v into the queue (also increments the numberOfOrders of the queue)
    public void enqueue(Order order)
    {
        // if the number of orders in the queue is filling up the array, then the body of this if statement doubles the numberOfOrders
        // of the array
        if (numberOfOrders == orders.length)
        {
            // i make a new array
            Order[] doubled = new Order[orders.length * 2];

            // copy all the values with a for loop
            for (int i = 0; i < orders.length; i++)
            {
                doubled[i] = orders[i];
            }
            // and then store the bigger array, trashing the old one
            orders = doubled;
        }
        // adding the value to the back of the line
        orders[numberOfOrders] = order;
        numberOfOrders++; // and noting that my queue is bigger now
    }

    // removes and returns the next element from the queue 
    public Order dequeue()
    {
        // storing the value to be returned
        Order r = orders[0];
            // why dont i need "this" here... because I already set an object im calling from in the objName.dequeue() call?
        // shifting the array to the left, losing the value I stored above^
        shiftLeft(orders);
        numberOfOrders--;     // noting that the length of the line has decreased
        return r;   // actually returning the value
    }

    // returns true if the queue is empty
    public boolean empty()
    {
        if (numberOfOrders == 0)
            return true;
        return false;
    }

    // this method shifts all values in an array to the left (the leftmost value is lost, and the new last value is 0)
    private void shiftLeft(Order[] a)
    {
        // this for loop iterates over the loop, assigning each entry the value to its right (it does not modify the last entry)
        for (int i = 0; i < a.length - 1; i++)
        {
            a[i] = a[i + 1];
        }
        // modifying the last entry
        a[a.length - 1] = null;
    }

    // returns the first order in the queue without removing it
    public Order peek()
    {
        return orders[0];
    }

    // returns the given entry in the queue without removing it (overloads previous method)
    public Order peek(int index)
    {
        return orders[index];
    }

}