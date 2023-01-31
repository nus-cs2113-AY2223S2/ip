import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        printWelcome();

        String command;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        boolean isRunning = true;

        while (isRunning) {
            command = in.nextLine();

            if (command.equals("bye")) {
                printBye();
                isRunning = false;

            } else if (command.equals("list")) {
                printAllTasks(tasks);

            } else if (command.matches("mark \\d+")) {
                markTaskDone(tasks, command);

            } else if (command.matches("unmark \\d+")) {
                markTaskNotDone(tasks, command);

            } else if (command.matches("todo" + "(.*)")) {
               addTodo(tasks, command);

            } else if (command.matches("deadline" + "(.*)" + "/by" + "(.*)")) {
                addDeadline(tasks, command);

            } else if (command.matches("event" + "(.*)" + "/from" + "(.*)" + "/to" + "(.*)")) {
               addEvent(tasks, command);

            } else {
                printInvalidMessage();
            }
        }
    }
    public static void markTaskDone(ArrayList<Task> tasks, String command) {
        command = command.replace("mark ", "");
        int taskNumber = Integer.parseInt(command);
        Task task = tasks.get(taskNumber-1);
        task.markAsDone();
        printMarkDone();
        printTask(task);
    }

    public static void markTaskNotDone(ArrayList<Task> tasks, String command) {
        command = command.replace("unmark ", "");
        int taskNumber = Integer.parseInt(command);
        Task task = tasks.get(taskNumber-1);
        task.unmarkAsDone();

        printMarkNotDone();
        printTask(task);
    }
    public static void addTodo(ArrayList<Task> tasks, String command) {
        printAddTask();

        String description = command.replace("todo ", "");
        Task task = new Todo(description);
        tasks.add(task);

        printTask(task);
        printNoOfTasks(tasks.size());
    }

    public static void addDeadline(ArrayList<Task> tasks, String command) {
        printAddTask();

        command = command.replace("deadline ", "");
        String[] components = command.split("/by");
        Task task = new Deadline(components[0], components[1]);
        tasks.add(task);

        printTask(task);
        printNoOfTasks(tasks.size());
    }

    public static void addEvent(ArrayList<Task> tasks, String command) {
        printAddTask();

        command = command.replace("event ", ""); //remove "event" from string
        String[] components = command.split(" /from | /to "); //split string using "/from" and "/to"
        Task task = new Event(components[0], components[1], components[2]);
        tasks.add(task);

        printTask(task);
        printNoOfTasks(tasks.size());
    }
    public static void printAllTasks(ArrayList<Task> tasks) {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks) {
            System.out.println(count + "." + task);
            count += 1;
        }
    }
    public static void printTask(Task task) {
        System.out.println(task);
    }
    public static void printAddTask() {
        System.out.println("Got it. I've added this task:");
    }
    public static void printMarkDone() {
        System.out.println("Nice! I've marked this task as done:");
    }
    public static void printMarkNotDone() {
        System.out.println("OK, I've marked this task as not done yet:");
    }
    public static void printNoOfTasks(int size) {
        System.out.println("Now you have " + size + " tasks in the list");
    }
    public static void printInvalidMessage() {
        System.out.println("I don't know what that means :-(");
    }
    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
}
