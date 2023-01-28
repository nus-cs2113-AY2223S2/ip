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

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String command;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        boolean isRunning = true;

        while (isRunning) {
            command = in.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isRunning = false;

            } else if (command.equals("list")) {
                int count = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task task: tasks) {
                    System.out.println(count + "." + task);
                    count += 1;
                }
            } else if (command.matches("mark \\d+")) {
                int taskNumber = Character.getNumericValue(command.charAt(5)); //convert to char than get numeric value
                Task task = tasks.get(taskNumber-1);
                task.markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);

            } else if (command.matches("unmark \\d+")) {
                int taskNumber = Character.getNumericValue(command.charAt(7)); //convert to char than get numeric value
                Task task = tasks.get(taskNumber-1);
                task.unmarkAsDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);

            } else if (command.matches("todo" + "(.*)")) {
                System.out.println("Got it. I've added this task:");
                String description = command.substring(5); //index 5 = beginning of task
                Task task = new Todo(description);
                tasks.add(task);

                System.out.println(task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");

            } else if (command.matches("deadline" + "(.*)" + "/by" + "(.*)")) {
                System.out.println("Got it. I've added this task:");
                int endOfTask = command.indexOf("/");
                String description = command.substring(9, endOfTask); //index 9 = beginning of task
                String date = command.substring(endOfTask + 3);
                Task task = new Deadline(description, date);
                tasks.add(task);

                System.out.println(task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list");

            } else if (command.matches("event" + "(.*)" + "/from" + "(.*)" + "/to" + "(.*)")) {
                System.out.println("Got it. I've added this task:");
                command = command.replace("event ", ""); //remove "event" from string
                String[] components = command.split(" /from | /to "); //split string using "/from" and "/to"

                Task task = new Event(components[0], components[1], components[2]);
                tasks.add(task);
                System.out.println(task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list");
            } else {
                System.out.println("I don't know what that means :-(");
            }
        }
    }
}
