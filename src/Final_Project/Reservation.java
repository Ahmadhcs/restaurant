package Final_Project;

import java.util.ArrayList;
import java.util.Random;

//import java.time.LocalDateTime;

public class Reservation {
    private int reservationID;
    private int customerID;
    private int tableID;
    private String name; 
//    private LocalDateTime time;
    private int numberOfGuests;
    private static ArrayList<Reservation> reservations = new ArrayList<>();


    // Constructor
//    public Reservation(int reservationID, int customerID, int tableID, LocalDateTime time, int numberOfGuests) {
    public Reservation(String name, int numberOfGuests) {

    	this.name = name;
    	 Random random = new Random();
         this.reservationID = 1000 + random.nextInt(9000);;
//        this.customerID = customerID;
         this.tableID = random.nextInt(10);
//        this.time = time;
        this.numberOfGuests = numberOfGuests;
        reservations.add(this);  
        }

    // Getters
    public static ArrayList<Reservation> getInstances() {
        return reservations;
    }
    
    
    public int getReservationID() {
        return reservationID;
    }

    public int getTableID() {
        return tableID;
    }

//    public LocalDateTime getTime() {
//        return time;
//    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    // Setters
//    public void setTime(LocalDateTime time) {
//        this.time = time;
//    }

    public void setNumberOfGuests(int numGuests) {
        this.numberOfGuests = numGuests;
    }
    
    
    @Override
    public String toString() {
        return "Reservation #:" + reservationID +
                " | Table # :" + tableID +
                " | Name: " + name +
                " |  Capacity:" + numberOfGuests;
    }

}
