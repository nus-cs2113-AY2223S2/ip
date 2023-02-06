import java.util.Scanner;
import util.Task;
import util.Todo;
import util.Event;
import util.Deadline;

public class Duke {
    public static int nextIndexInList = 1;

    public static Task[] createList() {
        Task[] commands = new Task[100];
        return commands;
    }

    public static void addTaskToList(String line, Task[] commands) {
        commands[nextIndexInList] = new Task(line);
        nextIndexInList++;
    }

    public static void displayCommandsList(Task[] commands) {
        int i = 1;
        System.out.println("\t_____________________________________________________");
        while (i < nextIndexInList) {
            System.out.println("\t" + i + "." + commands[i]);
            i++;
        }
        System.out.println("\t_____________________________________________________");
    }

    public static String ask() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public static void printDashLine() {
        System.out.println("\t_____________________________________________________");
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" Hello! I'm Duke ");
        System.out.println(" What can I do for you? \n");
        System.out.println("____________________________________________________________\n");
    }

    public static void printByeMessage() {
        System.out.println("____________________________________________________________\n");
        System.out.println("\t Bye. Hope to see you again soon!\n");
        System.out.println("____________________________________________________________\n");
    }

    public static void printSpecificTask(int index, Task[] commands, String message) {
        if (!message.equals("")) {
            System.out.println("\t " + message);
        }
        System.out.println("\t" + index + "." + commands[index]);
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        Task[] commands = createList();
        boolean not_finished = true;
        while (not_finished == true) {
            String line = ask();
            if (line.equals("bye")) {
                printByeMessage();
                not_finished = false;
            } else if (line.equals("list")) {
                displayCommandsList(commands);
            } else if (line.split(" ")[0].equals("mark")) {
                int index = Integer.parseInt(line.split(" ")[1]);
                commands[index].setDone(true);
                printDashLine();
                printSpecificTask(index, commands, "Nice! I've marked this task as done:");
                printDashLine();
            } else if (line.split(" ")[0].equals("unmark")) {
                int index = Integer.parseInt(line.split(" ")[1]);
                commands[index].setDone(false);
                printDashLine();
                printSpecificTask(index, commands, "OK, I've marked this task as not done yet:");
                printDashLine();
            } else if (line.split(" ")[0].equals("todo")) {
                if (line.split(" ").length == 1) {
                    printDashLine();
                    System.out.println("\t OOPS!!! The description of a todo cannot be empty.");
                    printDashLine();
                } else {
                    commands[nextIndexInList] = new Todo(line.substring(5));
                    nextIndexInList++;
                    printDashLine();
                    printSpecificTask(nextIndexInList - 1, commands, "Got it. I've added this task:");
                    printDashLine();
                }

            } else if (line.split(" ")[0].equals("event")) {
                int indexFrom = line.indexOf("/");
                int indexTo = line.indexOf("/", indexFrom + 1);
                commands[nextIndexInList] = new Event(line.substring(6, indexFrom - 1),
                        line.substring(indexFrom + 6, indexTo - 1), line.substring(indexTo + 4));
                nextIndexInList++;
                printDashLine();
                printSpecificTask(nextIndexInList - 1, commands, "Got it. I've added this task:");
                printDashLine();
            } else if (line.split(" ")[0].equals("deadline")) {
                int index_by = line.indexOf("/");
                commands[nextIndexInList] = new Deadline(line.substring(9, index_by - 1), line.substring(index_by + 4));
                nextIndexInList++;
                printDashLine();
                printSpecificTask(nextIndexInList - 1, commands, "Got it. I've added this task:");
                printDashLine();
            } else {
                printDashLine();
                System.out.println("\t OOPS!!! I'm sorry, but I don't know what that means :-(");
                printDashLine();
            }
        }

    }
}
