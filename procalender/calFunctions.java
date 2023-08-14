package procalender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class calFunctions {

    public Map<String, List<Event>> eventsMap;        // "2023-08-14" -> [event] it store data like this, event : cotain the event detali 
    public List<Reminder> reminders;                  // it store the Event name and remider time
    
    private Scanner sc;

    public calFunctions() {
        eventsMap = new HashMap<>();
        reminders = new ArrayList<>();
        sc = new Scanner(System.in);
    }


    public void addEvent(Event event) {                         // it add the events 
        LocalDateTime startTime = event.getStartTime();
        LocalDateTime endTime = event.getEndTime();
        String date = startTime.toLocalDate().toString();
    
        List<Event> events = eventsMap.getOrDefault(date, new ArrayList<>());
    
        for (Event alreadyEvent : events) {
            LocalDateTime alreadyStartTime = alreadyEvent.getStartTime();
            LocalDateTime alreadyEndTime = alreadyEvent.getEndTime();
    
        if ((startTime.isBefore(alreadyEndTime) && endTime.isAfter(alreadyStartTime)) ||
            (alreadyStartTime.isBefore(endTime) && alreadyEndTime.isAfter(startTime)) ||
            (startTime.isEqual(alreadyStartTime) || endTime.isEqual(alreadyEndTime))) {
            System.out.println("Event at the same time already exists.");
            System.out.println();
            System.out.println("You can select a time after: " + alreadyEndTime);
            return;
        }

        }
    
        events.add(event);
        eventsMap.put(date, events);

        System.out.println("\nEvent added successfully!");
    }
    
    

    public void setReminder(String event, LocalDateTime reminderDateTime) {              // Set Reminder 

        Reminder reminder = new Reminder(event, reminderDateTime);
        reminders.add(reminder);

    }


    public void checkReminders() {
        LocalDateTime time = LocalDateTime.now();

        List<Reminder> listForR = new ArrayList<>();
    
        for (Reminder r : reminders) {
            if (time.isAfter(r.reminderDateTime)) {
                System.out.println("ALERT: " + r);
                listForR.add(r);
            }
        }
        reminders.removeAll(listForR);
    }
    


    public void printReminders() {                                                     // Print Reminder
        System.out.println("\n\nList of Reminders :");
        for (Reminder r : reminders) {
            System.out.println(r);
        }
    }


     public void printEvents(){                                                         // Print Events 
        System.out.println("\nList of Events :\n\n");


        for (Map.Entry<String, List<Event>> entry : eventsMap.entrySet()) {
            String date = entry.getKey();
            List<Event> events = entry.getValue();
    
            System.out.println("Date : " + date);
            for (Event event : events) {
                System.out.println(event);
            }
            System.out.println();
        }

     }


                              
                                                                                    // Day, week and Month wise Events Display 
    public void dayView() {
  
        System.out.print("Enter date (yyyy-MM-dd) : ");
        String date = sc.nextLine();
    
        List<Event> events = eventsMap.getOrDefault(date, new ArrayList<>());

        System.out.println("\n Events for " + date + ":");
        for (Event e : events) {
            System.out.println(e);
        }
        System.out.println();
    }
    
    public void weekView() {
  
        System.out.print("Enter start date of the week (yyyy-MM-dd): ");
        String startDate = sc.nextLine();
        System.out.println("\n");
    
        LocalDate startLocalDate = LocalDate.parse(startDate);
        LocalDate endLocalDate = startLocalDate.plusDays(6); 
    
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
        for (LocalDate date = startLocalDate; !date.isAfter(endLocalDate); date = date.plusDays(1)) {
            String currentDate = date.format(dateFormatter);
            List<Event> events = eventsMap.getOrDefault(currentDate, new ArrayList<>());
            // System.out.println("Events for " + currentDate + ":");
            for (Event event : events) {
                System.out.println(event);
            }
        }
        System.out.println();
    }
    

    
    
    public void monthView() {
     
        System.out.print("Enter month (yyyy-MM): ");
        String month = sc.nextLine();
        System.out.println("\n");
        YearMonth yearMonth = YearMonth.parse(month);
    
        LocalDate startLocalDate = yearMonth.atDay(1);
        LocalDate endLocalDate = yearMonth.atEndOfMonth();
    
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
        for (LocalDate date = startLocalDate; !date.isAfter(endLocalDate); date = date.plusDays(1)) {
            String currentDate = date.format(dateFormatter);
            List<Event> events = eventsMap.getOrDefault(currentDate, new ArrayList<>());
   
            for (Event event : events) {
                System.out.println(event);
            }
        }

        System.out.println();
    }
    

    public void viewByCategory() {                                       // view evets by selecting category 
        System.out.print("Enter category:");
        String category = sc.next();
        System.out.println("\nEvents for Category: " + category);
        System.out.println();
        
        for (Map.Entry<String, List<Event>> entry : eventsMap.entrySet()) {
            List<Event> events = entry.getValue();
            for (Event event : events) {
                if (event.getCategory().equalsIgnoreCase(category)) {
                    System.out.println(event);
                }
            }
        }
        System.out.println();
    }



}