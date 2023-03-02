import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Task {
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
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
    public void setDone(String input) {
        String[] strArray = input.split(" ");
        int num = Integer.parseInt(strArray[1]);
        marked.set(num-1, true);
        System.out.println("Nice! I've marked this  as done:\n" + "[" + tasks.get(num-1).toString().charAt(0) + "]" + "[X] " + items.get(num-1));
    }
    public void setNotDone(String input) {
        String[] strArray = input.split(" ");
        int num = Integer.parseInt(strArray[1]);
        marked.set(num-1, false);
        System.out.println("Ok, I've marked this task as not done yet:\n" + "[" + tasks.get(num-1).toString().charAt(0) + "]" + "[ ] " + items.get(num-1));
    }
    //getters
    private String formatDateOut(int i) {
        String dateFrom = this.dateTimeFrom.get(i) == null ? "" : this.dateTimeFrom.get(i).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String dateTo = this.dateTimeTo.get(i) == null ? "" : this.dateTimeTo.get(i).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String dateOut = "";
        if (dateFrom.equals("") && dateTo.equals("")) {
            dateOut = "";
        } else if (dateFrom.equals("") && !dateTo.equals("")) {
            dateOut = "(by: " + dateTo + ")";
        } else if (!dateFrom.equals("") && !dateTo.equals("")) {
            dateOut = "(from: " + dateFrom + " to: " + dateTo + ")";
        } 
       
        return dateOut;
    }
    public void getItems() {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < items.size(); i++) {
            String dateOut = formatDateOut(i);
            System.out.println((i+1) + ". " + "[" + tasks.get(i).toString().charAt(0) + "]" +"[" + (marked.get(i) ? "X" : "") + "] " + items.get(i) + dateOut);
        }
    }

    public void getDue(String input) {
        LocalDateTime dateTime = LocalDateTime.parse(input.replace("due ", "").trim());
        System.out.println("Before: " + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyy")));
        int count = 1;
        for (int i = 0; i < items.size(); i++) {
            String dateOut = formatDateOut(i);
            if (dateTime.equals(dateTimeTo.get(i)) || dateTime.isAfter(dateTimeTo.get(i))) {
                System.out.println(Integer.toString(count) + ". " + "[" + tasks.get(i).toString().charAt(0) + "]" +"[" + (marked.get(i) ? "X" : "") + "] " + items.get(i) + dateOut);
                count++;
            } 
        }
        System.out.println("\n" + "After: " + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyy")));
        for (int i = 0; i < items.size(); i++) {
            String dateOut = formatDateOut(i);
            if (dateTime.isBefore(dateTimeTo.get(i))) {
                System.out.println(Integer.toString(count) + ". " + "[" + tasks.get(i).toString().charAt(0) + "]" +"[" + (marked.get(i) ? "X" : "") + "] " + items.get(i) + dateOut);
                count++;
            } 
        }
    }

    public void find(String input) {
        input = input.replace("find ", "");
        int count = 1;
        for (int i = 0; i < items.size(); i++) {
            String dateOut = formatDateOut(i);
            if (items.get(i).contains(input)) {
                System.out.println(Integer.toString(count) + ". " + "[" + tasks.get(i).toString().charAt(0) + "]" +"[" + (marked.get(i) ? "X" : "") + "] " + items.get(i) + dateOut);
                count++;
            } 
        }

    }

    public void delete(String input){
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
    public void print() {
        int size = items.size() - 1;
        System.out.println(items.size());
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }
        String type = "[" + tasks.get(size).toString().charAt(0) + "]";       
        String checkbox = this.marked.get(size) ? "[X] " : "[ ] ";
        String item = this.items.get(size);
        String dateOut = formatDateOut(size); 
        System.out.println("Got it. I've added this task: \n" + type + checkbox + item + dateOut + "\n" + "Now you have " + items.size() + " tasks in the list.");
    }
}
