package Final_Project;

public class Table {
    private int tableID;
    private int capacity;

    // Constructor
    public Table(int tableID, int capacity) {
        this.tableID = tableID;
        this.capacity = capacity;

    }

    // Getters
    public int getTableID() {
        return tableID;
    }

    public int getCapacity() {
        return capacity;
    }
    
    public void setTableID(int tableID) {
    	this.tableID = tableID;
    }
    
    public void setCapacity(int capacity) {
    	this.capacity = capacity; 
    }


}
