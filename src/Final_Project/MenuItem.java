package Final_Project;

import java.util.ArrayList;
import java.util.Random;

public class MenuItem {
    private int itemID;
    private String name;
    private String description;
    private double price;
    private static ArrayList<MenuItem> menuItems = new ArrayList<>(); // Static list to store menu items

    // Constructor
    
    public MenuItem(String name, String description, double price) {
   	 	Random random = new Random();
   	 	
    	this.itemID = 1000 + random.nextInt(9000);;
        this.name = name;
        this.description = description;
        this.price = price;
        menuItems.add(this);
    }
    
  

    // Getters
    public int getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public static ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

 
}
