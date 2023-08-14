package procalender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class CalenderMain {                                                  
    public static void main(String[] args) {

        calFunctions calendar = new calFunctions();

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to CalendarPro!");


        Thread RThread = new Thread(() -> {                      // A thread For Checking Remiders 
            while (true) {
                try {
                    Thread.sleep(60000); 
                    calendar.checkReminders(); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        RThread.start();
        

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");



        while (true) {
            
            System.out.println("1. Add Event");
            System.out.println("2. View Events");
            System.out.println("3. Check Reminders");
            System.out.println("4. Exit");

             System.out.println("Select an option:");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:       // takign input from user
                    System.out.println("\n\nEnter event details :\n");
                  

                    System.out.print("Event Name: ");
                    String name = sc.nextLine();


                LocalDateTime startTime = null;
                LocalDateTime endTime = null;

                try {
                    System.out.print("Start Date and Time (time in 24-hour format) (yyyy-MM-dd HH:mm): ");
                    String dateTimeStr = sc.nextLine();
                    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    startTime = LocalDateTime.parse(dateTimeStr, formatter);

                    System.out.print("End Date and Time  (time in 24-hour format) (yyyy-MM-dd HH:mm):");
                    String endDateTimeStr = sc.nextLine();
                    endTime = LocalDateTime.parse(endDateTimeStr, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date/time format. Please use the format yyyy-MM-dd HH:mm and Try Again");
                    break;
                }



                    System.out.print("Location: ");
                    String location = sc.nextLine();
                    System.out.print("Enter Event Category (work, personal, etc):");
                    String category = sc.nextLine();
                    System.out.print("Notes: ");
                    String notes = sc.nextLine();

                    System.out.print("Do You want to add remider (y/n):");
                    String remid = sc.next();

                    if (remid.equals("y") || remid.equals("Y") ) {
                        System.out.print("Reminder Date and Time  (time in 24-hour format) (yyyy-MM-dd HH:mm): ");
                        sc.nextLine(); 
                        String reminderDateTimeStr = sc.nextLine();
                    
                        try {
                            LocalDateTime reminderDateTime = LocalDateTime.parse(reminderDateTimeStr, formatter);
                            calendar.setReminder(name, reminderDateTime);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date/time format. Please use the format yyyy-MM-dd HH:mm.");
                        }
                    } else {
                        System.out.println("\n You have not added a Reminder.");
                    }
                    
                    

                    Event event = new Event(name, startTime, endTime, location,category ,notes);
                    calendar.addEvent(event);
             
                    break;



                case 2:   // print a list of all events
                    // System.out.println("\n\n All Events: \n ");
                    calendar.printEvents();
                    System.out.println("\n\nYou can also View Events by Day/week/Month/category Wise");
                    System.out.print("\n 1. Day \n 2. Week \n 3. Month \n 4. by category \n Select Option:");
                    int userChoice = sc.nextInt();

                    switch (userChoice) {
                        case 1:
                        calendar.dayView();
                            
                        break;
                        case 2:
                        calendar.weekView();
                         

                        break;

                        case 3:
                        calendar.monthView();

                        break;

                        case 4:
                        calendar.viewByCategory();
                    
                        default:
                            break;
                    }
            
                   
                    break;

                case 3:                        // just print a list of all reminders added by user
       
                    calendar.printReminders();
                    break;

                case 4:

                    //  calendar.printDataa();
                     sc.close();
                    System.exit(0);
                   
                   

                default:
                    System.out.println("Please Enter Correct Choice");
            }
        }


       


        
    }

}


