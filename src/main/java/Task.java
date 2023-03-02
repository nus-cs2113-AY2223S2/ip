import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    public static enum TaskType {
        TODO, DEADLINE, EVENT
    }
    public static ArrayList<String> items = new ArrayList<String>();
    public static ArrayList<Boolean> marked = new ArrayList<Boolean>();
    public static ArrayList<TaskType> tasks = new ArrayList<TaskType>();
    public static ArrayList<LocalDateTime> dateTimeFrom = new ArrayList<LocalDateTime>();
    public static ArrayList<LocalDateTime> dateTimeTo = new ArrayList<LocalDateTime>();

    
    public Task() {

    }

    private String formatDateOut(int i) { //helper function to format date output string for printing
        String dateFrom = Task.dateTimeFrom.get(i) == null ? "" : Task.dateTimeFrom.get(i).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String dateTo = Task.dateTimeTo.get(i) == null ? "" : Task.dateTimeTo.get(i).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));

        String dateOut = "";
        if (dateFrom.equals("") && dateTo.equals("")) {
            dateOut = "";
        } else if (dateFrom.equals("") && !dateTo.equals("")) {
            dateOut = " (by: " + dateTo + ")";
        } else if (!dateFrom.equals("") && !dateTo.equals("")) {
            dateOut = " (from: " + dateFrom + " to: " + dateTo + ")";
        } 
       
        return dateOut;
    }
    // setters 
    public void setDone(String input) { //set task as done
        String[] strArray = input.split(" ");
        int num = Integer.parseInt(strArray[1]);
        marked.set(num-1, true);
        String dateOut = formatDateOut(num-1);
        System.out.println("Nice! I've marked this  as done:\n" + "[" + tasks.get(num-1).toString().charAt(0) + "]" + "[X] " + items.get(num-1) + dateOut);
    }
    public void setNotDone(String input) { //set task as not done
        String[] strArray = input.split(" ");
        int num = Integer.parseInt(strArray[1]);
        marked.set(num-1, false);
        String dateOut = formatDateOut(num-1);
        System.out.println("Ok, I've marked this task as not done yet:\n" + "[" + tasks.get(num-1).toString().charAt(0) + "]" + "[ ] " + items.get(num-1) + dateOut);
    }
    
    //getters
    public void getItems() { //get all items
        if (items.size() == 0) {
            System.out.println("No tasks yet!");
            return;
        }
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < items.size(); i++) {
            String dateOut = formatDateOut(i);
            System.out.println((i+1) + ". " + "[" + tasks.get(i).toString().charAt(0) + "]" +"[" + (marked.get(i) ? "X" : "") + "] " + items.get(i) + dateOut);
        }
    }

    public void getDue(String input) { //show items due before  & after a certain date time
        LocalDateTime dateTime = LocalDateTime.parse(input.substring(3).trim()); // remove "due " from input and convert to LocalDateTime
        System.out.println("Due Before "  + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm")) + ":");
        int count = 1;
        for (int i = 0; i < items.size(); i++) {
            if (dateTimeTo.get(i) == null) {
                continue;
            }
            String dateOut = formatDateOut(i);
            if (dateTime.equals(dateTimeTo.get(i)) || dateTime.isAfter(dateTimeTo.get(i))) {
                System.out.println(Integer.toString(count) + ". " + "[" + tasks.get(i).toString().charAt(0) + "]" +"[" + (marked.get(i) ? "X" : "") + "] " + items.get(i) + dateOut);
                count++;
            }
        }
        count = 1;
        System.out.println("\n" + "Due After " + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm")) + ":");
        for (int i = 0; i < items.size(); i++) {
            if (dateTimeTo.get(i) == null) {
                continue;
            }
            String dateOut = formatDateOut(i);
            if (dateTime.isBefore(dateTimeTo.get(i))) {
                System.out.println(Integer.toString(count) + ". " + "[" + tasks.get(i).toString().charAt(0) + "]" +"[" + (marked.get(i) ? "X" : "") + "] " + items.get(i) + dateOut);
                count++;
            } 
        }
    }

    public void find(String input) { // find items containing a certain string
        input = input.substring(4).trim(); //remove "find " from input
        int count = 1;
        for (int i = 0; i < items.size(); i++) {
            String dateOut = formatDateOut(i);
            if (items.get(i).contains(input)) {
                System.out.println(Integer.toString(count) + ". " + "[" + tasks.get(i).toString().charAt(0) + "]" +"[" + (marked.get(i) ? "X" : "") + "] " + items.get(i) + dateOut);
                count++;
            } 
        }

    }

    public void delete(String input){ // delete item and its entries in each arraylist
        String[] strArray = input.split(" ");
        int num = Integer.parseInt(strArray[1]);
        System.out.println("Noted. I've removed this task:\n" + "[" + tasks.get(num-1).toString().charAt(0) + "]" + "[" + (marked.get(num-1) ? "X" : "") + "] " + items.get(num-1));
        items.remove(num-1);
        System.out.println("Now you have " + items.size() + " tasks in the list.");
        marked.remove(num-1);
        tasks.remove(num-1);
        dateTimeFrom.remove(num-1);
        dateTimeTo.remove(num-1);
    }

    public void print() { // print out item added after add command

        int size = items.size() - 1;
        System.out.println(items.size());
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }
        String type = "[" + tasks.get(size).toString().charAt(0) + "]";       
        String checkbox = Task.marked.get(size) ? "[X] " : "[ ] ";
        String item = Task.items.get(size);
        String dateOut = formatDateOut(size); 
        System.out.println("Got it. I've added this task: \n" + type + checkbox + item + dateOut + "\n" + "Now you have " + items.size() + " tasks in the list.");
    }
}
