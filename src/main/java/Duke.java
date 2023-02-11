import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        String line;
        List<Task> tasks = new ArrayList<Task>();
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            System.out.println("____________________________________________________________\n");

            //List command when list is not empty
            if (line.equals("list") && !tasks.isEmpty()) {
                System.out.println("Here are the tasks in your list: \n ");
                for (int i = 0; i < tasks.size(); i++) {
                    Task todoList = tasks.get(i);
                    System.out.println("\t" + (i + 1) + ". " + todoList.toString());
                }
                System.out.println("____________________________________________________________\n");
            }

            //unmark command to indicate completion of tasks
            else if (line.contains("unmark")) {
                int index = line.indexOf(" ");
                if (line.length() > 7 && (tasks.size() >= index)){
                    String str = line.substring(index + 1);
                    int pos = Integer.parseInt(str);
                    Task taskUnmarked = tasks.get(pos - 1);
                    taskUnmarked.setStatusIcon(false);
                    System.out.printf("" + "Okay, I've marked this task as not done yet:\n" + "\t" + " " + "[" +
                            taskUnmarked.getStatusIcon() + "]" + " " + taskUnmarked.description + "\n");
                    System.out.println("____________________________________________________________\n");
                }
                else{
                    System.out.println("🤪Sorry please drink some coffee and enter valid unmark command");
                    System.out.println("____________________________________________________________\n");
                }
            }

            //mark tasks to indicate completion
            else if (line.contains("mark")) {
                int index = line.indexOf(" ");
                if (line.length() > 5 && (tasks.size() >= index)){
                    String str = line.substring(index + 1);
                    int pos = Integer.parseInt(str);
                    Task taskMarked = tasks.get(pos - 1);
                    taskMarked.setStatusIcon(true);
                    System.out.printf(" " + "Nice! I've marked this task as done:\n" + "\t" + " " + "[" +
                            taskMarked.getStatusIcon() + "]" + " " + taskMarked.description + "\n");
                    System.out.println("____________________________________________________________\n");
                }
                else{
                    System.out.println("🤪Sorry please drink some coffee and enter valid mark command");
                    System.out.println("____________________________________________________________\n");
                }
            }

            //Expected format : todo borrow book
            else if (line.contains("todo")) {
                if (line.length() > 5){
                    int index = line.indexOf(" ");
                    String activity = line.substring(index, line.length());
                    Todo toDo = new Todo(activity);
                    tasks.add(toDo);
                    System.out.println("Added: " + "\n" + toDo.toString() + "\n");
                    System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                    System.out.println("____________________________________________________________\n");
                }
                else {
                    System.out.println("🤪Drink coffee and enter a valid todo command");
                    System.out.println("____________________________________________________________\n");
                }
            }

            //Expected format : deadline finish submission /by Monday 7pm
            else if (line.contains("deadline")) {
                if (line.length() > 9 && line.contains("/")){
                    int index = line.indexOf(" ");
                    int pos = line.indexOf("/");
                    String activity = line.substring(index, pos - 1);
                    String dueDate = line.substring(pos + 3, line.length());
                    Deadline deadline = new Deadline(activity, dueDate);
                    tasks.add(deadline);
                    System.out.println("Added: " + "\n" + deadline.toString() + "\n");
                    System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                    System.out.println("____________________________________________________________\n");
                }
                else{
                    System.out.println("🤪Drink coffee and enter a valid deadline command");
                    System.out.println("____________________________________________________________\n");
                }
            }

            //Expected format : event attend Aarushi's marriage /from Monday 7pm to Thursday 7pm
            else if (line.contains("event")) {
                if (line.length() > 6 && line.contains("/")){
                    int index = line.indexOf(" ");
                    int startDate = line.indexOf("/");
                    String activity = line.substring(index, startDate);
                    String timeFrame = line.substring(startDate + 5, line.length());
                    Event event = new Event(activity, timeFrame);
                    tasks.add(event);
                    System.out.println("Added: " + "\n" + event.toString() + "\n");
                    System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                    System.out.println("____________________________________________________________\n");
                }
                else{
                    System.out.println("🤪Drink coffee and enter a valid event command");
                    System.out.println("____________________________________________________________\n");
                }
            }

            //When the list is empty
            else if(tasks.isEmpty()){
                System.out.println("🤪The list is empty. Do something in order to have tasks in your list");
                System.out.println("____________________________________________________________\n");
            }

            //When non duke command are entered
            else {
                System.out.println("🤪Sorry enter a valid Duke command");
                System.out.println("____________________________________________________________\n");
            }
            line = in.nextLine();
        }
        System.out.println("____________________________________________________________\n");
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
