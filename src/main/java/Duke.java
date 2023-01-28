
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
        List<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye")) {
            System.out.println("____________________________________________________________\n");
            if (line.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task todoList = tasks.get(i);
                    System.out.println("\t" + (i+1) + todoList.getStatusIcon() + todoList.description);
                }
                System.out.println("____________________________________________________________\n");
            }
            else if (line.contains("unmark")) {
                int index = line.indexOf("") + 1;
                Task taskUnmarked = tasks.get(index - 1);
                taskUnmarked.markUndone();
                System.out.printf("" + "Okay, I've marked this task as not done yet:\n" + "\t" + " " + "[" +
                        taskUnmarked.getStatusIcon() + "]" + " "+ taskUnmarked.description + "\n");
                System.out.println("____________________________________________________________\n");
            }
            else if (line.contains("mark")) {
                int index = line.indexOf("") + 1;
                Task taskMarked = tasks.get(index - 1);
                taskMarked.markDone();
                System.out.printf(" " + "Nice! I've marked this task as done:\n" + "\t" + " " + "[" +
                        taskMarked.getStatusIcon() + "]" + " "+ taskMarked.description + "\n");
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
