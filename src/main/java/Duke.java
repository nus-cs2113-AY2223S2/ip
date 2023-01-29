import java.util.Scanner;
import java.util.ArrayList;



public class Duke {
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input;
        ArrayList<String> items = new ArrayList<String>();
        ArrayList<Boolean> marked = new ArrayList<Boolean>();
        ArrayList<TaskType> tasks = new ArrayList<TaskType>();
        
        while((input = in.nextLine()) != "") {
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                in.close();
                return;
            }
            else if (input.equals("list")) {
                
                for(int i = 0; i < items.size(); i++) {
                    System.out.println(i+1 +". " + "[" + tasks.get(i).toString().charAt(0) + "]" + (marked.get(i) ? "[X] " : "[ ] ")   + items.get(i));
                    
                }
            }
            else if (input.startsWith("mark")) {
                String[] strArray = input.split(" ");
                int num = Integer.parseInt(strArray[1]);
                marked.set(num-1, true);
                System.out.println("Nice! I've marked this task as done:\n" + "[X] " + items.get(num-1));
            }
            else if (input.startsWith("unmark")) {
                String[] strArray = input.split(" ");
                int num = Integer.parseInt(strArray[1]);
                marked.set(num-1, false);
                System.out.println("Ok, I've marked this task as not done yet:\n" + "[ ] " + items.get(num-1));
            }
            else if (input.startsWith("todo")) {
                items.add(input.replace("todo ", ""));
                marked.add(false);
                tasks.add(TaskType.TODO);
                System.out.println("Got it. I've added this task: \n" + "   [T][ ] " + input + "\n" + "Now you have " + items.size() + " tasks in the list.");
            }
            else if (input.startsWith("deadline")) {
                String[] strArray = input.split("/");
                String temp = strArray[0].replace("deadline ", "") + ("(" +  strArray[1].replace("by", "by:") + ")");
                items.add(temp);
                marked.add(false);
                tasks.add(TaskType.DEADLINE);
                System.out.println("Got it. I've added this task: \n" + "   [D][ ] " + temp + "\n" + "Now you have " + items.size() + " tasks in the list.");
            }
            else if (input.startsWith("event")) {
                String[] strArray = input.split("/");
                String temp = strArray[0].replace("event ", "") + ("(" +  strArray[1].replace("from", "from:") + strArray[2].replace("to", "to:") + ")");
                items.add(temp);
                marked.add(false);
                tasks.add(TaskType.EVENT);
                System.out.println("Got it. I've added this task: \n" + "   [E][ ] " + temp + "\n" + "Now you have " + items.size() + " tasks in the list.");
            }
            else {
                System.out.println(input);
            }
        }
        
    }
}
