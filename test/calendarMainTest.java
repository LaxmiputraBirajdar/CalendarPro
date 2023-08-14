package test;
import org.junit.jupiter.api.Test;
import procalender.CalenderMain;
import procalender.Event;
import procalender.Reminder;
import procalender.calFunctions;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

public class calendarMainTest {

    //

    // Note : For Some Functions excpected and actual values are Same but i am not sure why test case is not passing....

    //



    calFunctions cal = new calFunctions();

    @Test
    public void testaddevent() {


        Event event = new Event("Meeting", LocalDateTime.of(2023, 8, 14, 10, 0), LocalDateTime.of(2023, 8, 14, 12, 0), "Room A", "Work", "");

       
        cal.addEvent(event);

        assertTrue(cal.eventsMap.containsKey("2023-08-14"));
        assertEquals(1, cal.eventsMap.get("2023-08-14").size());
    }


    @Test
    public void testprintevent() {
  
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    
        LocalDateTime startTime1 = LocalDateTime.of(2023, 8, 14, 10, 0);
        LocalDateTime endTime1 = LocalDateTime.of(2023, 8, 14, 12, 0);
        Event event1 = new Event("test", startTime1, endTime1, "College Lib", "Work", "Nothing");
    
        cal.addEvent(event1);
    
        cal.printEvents();
    
        String expectedOutput = "\nEvent added successfully!\n\n" +
                "List of Events :\n\n\n" +
                "Date : 2023-08-14\n" +
                "Event: test\n" +
                "Start Date and Time: 2023-08-14T10:00\n" +
                "End date and Time: 2023-08-14T12:00\n" +
                "Location: College Lib\n" +
                "category:Work\n" +
                "Notes: Nothing\n\n";
    
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintReminders_WithReminders() {
     


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

  
        LocalDateTime rem = LocalDateTime.of(2023, 8, 14, 13, 45);
        
     
        cal.setReminder("Work Reminder", rem);

        cal.printReminders();

        String expectedOutput = "\n\nList of Reminders :\n" +
                "Reminder for event 'Work Reminder' at 2023-08-14T13:45\n";


    

        assertEquals(expectedOutput, outputStream.toString());
    }


    @Test
    public void testSetReminder() {
     

        LocalDateTime reminderDateTime = LocalDateTime.of(2023, 8, 14, 9, 0);
        String event = "Meeting";

        cal.setReminder(event, reminderDateTime);
        assertEquals(1, cal.reminders.size());

    }
    
  
}
