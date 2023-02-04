
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

        while(!line.equals("bye")) {
            System.out.println("____________________________________________________________\n");
            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list: \n ");
                for (int i = 0; i < tasks.size(); i++) {
                    Task todoList = tasks.get(i);
                    //System.out.println("\t" + (i+1) + "." + "[" + todoList.getStatusIcon() + "]" +
                    //        " " + todoList.description);
                    System.out.println("\t" + (i + 1) + ". " + todoList.toString());
                }
                System.out.println("____________________________________________________________\n");
            }

            else if (line.contains("unmark")) {
                int index = line.indexOf(" ");
                String str= line.substring(index + 1);
                int pos = Integer.parseInt(str);
                Task taskUnmarked = tasks.get(pos - 1);
                taskUnmarked.setStatusIcon(false);
                System.out.printf("" + "Okay, I've marked this task as not done yet:\n" + "\t" + " " + "[" +
                        taskUnmarked.getStatusIcon() + "]" + " "+ taskUnmarked.description + "\n");
                System.out.println("____________________________________________________________\n");
            }

            else if (line.contains("mark")) {
                int index = line.indexOf(" ");
                String str= line.substring(index + 1);
                int pos = Integer.parseInt(str);
                Task taskMarked = tasks.get(pos - 1);
                taskMarked.setStatusIcon(true);
                System.out.printf(" " + "Nice! I've marked this task as done:\n" + "\t" + " " + "[" +
                        taskMarked.getStatusIcon() + "]" + " "+ taskMarked.description + "\n");
                System.out.println("____________________________________________________________\n");
            }

            else if (line.contains("todo")){
                int index = line.indexOf(" ");
                String activity = line.substring(index, line.length());
                Todo toDo = new Todo(activity);
                tasks.add(toDo);
                System.out.println("Added: " + "\n" + toDo.toString() + "\n");
                System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                System.out.println("____________________________________________________________\n");
            }

            else if (line.contains("deadline")){
                int index = line.indexOf(" ");
                int pos = line.indexOf("/");
                String activity = line.substring(index, pos - 1);
                String dueDate = line.substring(pos + 3, line.length());
                Deadline deadline = new Deadline(activity,dueDate);
                tasks.add(deadline);
                System.out.println("Added: " + "\n" + deadline.toString() + "\n");
                System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                System.out.println("____________________________________________________________\n");
            }

            else if (line.contains("event")){
                int index = line.indexOf(" ");
                int startDate = line.indexOf("/");
                String activity = line.substring(index, startDate);
                String timeFrame = line.substring(startDate + 3, line.length());
                Event event = new Event(activity,timeFrame);
                tasks.add(event);
                System.out.println("Added: " + "\n" + event.toString() + "\n");
                System.out.println("You have " + tasks.size() + " number of tasks in you list.");
                System.out.println("____________________________________________________________\n");
            }

            else {
                System.out.println("\t" + "added: " + line);
                System.out.println("____________________________________________________________\n");
                Task newTask = new Task(line);
                tasks.add(newTask);

            }
            line = in.nextLine();
        }
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
