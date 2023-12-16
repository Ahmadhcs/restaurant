package Final_Project;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextOrderID = 1; // Static variable to track the next order ID
    private static final ArrayList<Order> allOrders = new ArrayList<>(); // Static list of all orders

    private final int orderID;
    private List<MenuItem> items; // Each order has its own list of items
    private String name;

    // Constructor
    public Order(String name) {
        this.name = name;
        this.orderID = nextOrderID++; // Assign current ID and increment for the next order
        this.items = new ArrayList<>();
        allOrders.add(this); // Add this order to the list of all orders
    }

    // Getters
    public int getOrderID() {
        return orderID;
    }

    public String getName() {
        return name;
    }


    public List<MenuItem> getItems() {
        return new ArrayList<>(items); // Return a copy of items to preserve encapsulation
    }

 

    public void setName(String name) {
        this.name = name;
    }

    // Methods to modify the items list
    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    // Static method to get all orders
    public static List<Order> getAllOrders() {
        return new ArrayList<>(allOrders); // Return a copy to preserve encapsulation
    }

    // Optional: Method to display order details
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderID)
          .append(", Name: ").append(name)
          .append(", Items: ");

        double total = 0;
        for (MenuItem item : items) {
            sb.append(item.getName()).append(" ($").append(item.getPrice()).append("), ");
            total += item.getPrice();
        }

        // Remove the last comma and space
        if (sb.length() > 0) {
            sb = new StringBuilder(sb.substring(0, sb.length() - 2));
        }

        sb.append("\nTotal Sale: $").append(total);
        return sb.toString();
    }
}
