import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Date;
import java.util.Comparator;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Event {
    private String name;
    private Date eventDate;

    public Event(String name, Date eventDate) {
        this.name = name;
        this.eventDate = eventDate;
    }

    public String getName() {
        return name;
    }

    public Date getEventDate() {
        return eventDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return name + " at " + sdf.format(eventDate);
    }
}

class EventScheduler {
    private PriorityQueue<Event> eventQueue;

    public EventScheduler() {
        // Initialize the priority queue with a comparator based on event time
        eventQueue = new PriorityQueue<>(new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                return e1.getEventDate().compareTo(e2.getEventDate());
            }
        });
    }

    // Method to add an event to the scheduler
    public void addEvent(String name, Date eventDate) {
        Event event = new Event(name, eventDate);
        eventQueue.add(event);
        System.out.println("Event added: " + event);
    }

    // Method to remove the next event from the scheduler
    public void removeNextEvent() {
        Event nextEvent = eventQueue.poll();
        if (nextEvent != null) {
            System.out.println("Removed event: " + nextEvent);
        } else {
            System.out.println("No upcoming events to remove.");
        }
    }

    // Method to display all upcoming events
    public void displayEvents() {
        if (eventQueue.isEmpty()) {
            System.out.println("No upcoming events.");
        } else {
            System.out.println("Upcoming events:");
            for (Event event : eventQueue) {
                System.out.println(event);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        EventScheduler scheduler = new EventScheduler();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        while (true) {
            System.out.println("\nEvent Scheduler Menu:");
            System.out.println("1. Add Event");
            System.out.println("2. Remove Next Event");
            System.out.println("3. Display Events");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter event name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter event date (dd/MM/yyyy HH:mm): ");
                        String dateString = scanner.nextLine();
                        Date eventDate = sdf.parse(dateString);
                        scheduler.addEvent(name, eventDate);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please try again.");
                    }
                    break;
                case 2:
                    scheduler.removeNextEvent();
                    break;
                case 3:
                    scheduler.displayEvents();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
output :
Event Scheduler Menu:
1. Add Event
2. Remove Next Event
3. Display Events
4. Exit
Enter your choice: 1
Enter event name: Meeting with Bob
Enter event date (dd/MM/yyyy HH:mm): 25/08/2024 15:30
Event added: Meeting with Bob at 25/08/2024 15:30

Event Scheduler Menu:
1. Add Event
2. Remove Next Event
3. Display Events
4. Exit
Enter your choice: 3
Upcoming events:
Meeting with Bob at 25/08/2024 15:30
