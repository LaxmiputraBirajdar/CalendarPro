
package procalender;

import java.time.LocalDateTime;


public class Reminder {   
    
                                                    // The Remider class to hold the Remiders detail
    public String eventName;
    public LocalDateTime reminderDateTime;


    public Reminder(String eventName, LocalDateTime reminderDateTime) {   //constructor intialize
        this.eventName = eventName;
        this.reminderDateTime = reminderDateTime;
    }


    public String toString() {
        return "Reminder for event '" + eventName + "' at " + reminderDateTime;
    }
}


