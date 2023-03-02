import java.util.ArrayList;

public class Task {
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }
    public static ArrayList<String> items = new ArrayList<String>();
    public static ArrayList<Boolean> marked = new ArrayList<Boolean>();
    public static ArrayList<TaskType> tasks = new ArrayList<TaskType>();
    
    public Task() {

    }

    public void setDone(String input) {
        String[] strArray = input.split(" ");
        int num = Integer.parseInt(strArray[1]);
        marked.set(num-1, true);
        System.out.println("Nice! I've marked this task as done:\n" + "[" + tasks.get(num-1).toString().charAt(0) + "]" + "[X] " + items.get(num-1));
    }
    public void setNotDone(String input) {
        String[] strArray = input.split(" ");
        int num = Integer.parseInt(strArray[1]);
        marked.set(num-1, false);
        System.out.println("Ok, I've marked this task as not done yet:\n" + "[" + tasks.get(num-1).toString().charAt(0) + "]" + "[ ] " + items.get(num-1));
    }
    //getters
    public void getItems() {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i+1) + ". " + "[" + tasks.get(i).toString().charAt(0) + "]" +"[" + (marked.get(i) ? "X" : "") + "] " + items.get(i));
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
    }
    public void print() {
        String type = "[" + tasks.get(this.tasks.size()-1).toString().charAt(0) + "]"
;       String checkbox = this.marked.get(this.marked.size()-1) ? "[X] " : "[ ] ";
        String item = this.items.get(this.items.size()-1);
        System.out.println("Got it. I've added this task: \n" + type + checkbox + item + "\n" + "Now you have " + items.size() + " tasks in the list.");
    }
}
