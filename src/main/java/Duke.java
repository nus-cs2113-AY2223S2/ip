import java.util.Arrays;
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

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        String line;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int position = 0;
        while (true) {
            line = in.nextLine();
            if ("bye".equalsIgnoreCase(line)) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if ("list".equalsIgnoreCase(line)) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                int index = 1;
                for (int i = 0; i < position; i++) {
                    System.out.println(" " + index + ". [" + tasks[i].getStatusIcon() +"] " + tasks[i].getDescription());
                    index++;
                }
                System.out.println("____________________________________________________________");
            } else if ((line.substring(0,4)).equals("mark")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                int index = Integer.parseInt(line.substring(5));
                tasks[index-1].markAsDone();
                System.out.println(" [" + tasks[index-1].getStatusIcon() +"] " + tasks[index-1].getDescription());
                System.out.println("____________________________________________________________");
            } else if ((line.substring(0,6)).equals("unmark")) {
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                int index = Integer.parseInt(line.substring(7));
                tasks[index-1].markAsNotDone();
                System.out.println(" [" + tasks[index-1].getStatusIcon() +"] " + tasks[index-1].getDescription());
                System.out.println("____________________________________________________________");
            } else {
                tasks[position] = new Task(line);
                position++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + line);
                System.out.println("____________________________________________________________");
            }
        }
    }
}