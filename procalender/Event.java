package procalender;

import java.time.LocalDateTime;

public class Event {                                                 // The Event class to hold the Events Detail 
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private String category;
    private String notes;

    public Event(String name, LocalDateTime startTime, LocalDateTime endTime, String location,String category ,String notes) {
       this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.category = category;
        this.notes = notes;
    }

    public LocalDateTime getStartTime() {                  // i used these getters to access private attributes outise this class 


        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

     public String getName() {

        return name;
    }

    public String getLocation() {

        return location;
    }

    public String getCategory(){
        

        return category;
    }

    public String getNotes() {


        return notes;
    }

    public String toString() {
        return "Event: " + name + "\nStart Date and Time: " + startTime + "\nEnd date and Time: " + endTime + "\nLocation: " + location+ "\ncategory:"+category +"\nNotes: " + notes;
    }
}
