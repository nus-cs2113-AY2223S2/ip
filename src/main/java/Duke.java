import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static final String spacer = "\t ";

    /**
     * Helper method to print a horizontal line.
     */
    private static void printDivider() {
        System.out.println("\t____________________________________________________________");
    }


    /**
     * Prints all tasks in the list.
     */
    private static void listTasks() {
        System.out.println(spacer + "Here are the tasks in your list:");
        int cnt = 0;
        for (Task task : taskList) {
            System.out.printf(spacer + "%d.%s\n", ++cnt, task);
        }
    }

    /**
     * Checks validity of listID and then marks the corresponding task in the task list.
     * @param listIdStr id of the task in the task list in a string.
     */
    private static void markTask(String listIdStr) {
        try {
            markTask(Integer.parseInt(listIdStr));
        } catch (NumberFormatException e) {
            System.err.println(spacer + "Invalid input!");
            System.err.println(spacer + "Valid input format: mark [list id of the task]");
        }
    }

    /**
     * Checks validity of listID and then unmark the corresponding task in the task list.
     * @param listIdStr id of the task in the task list in a string.
     */

    private static void unmarkTask(String listIdStr) {
        try {
            unmarkTask(Integer.parseInt(listIdStr));
        } catch (NumberFormatException e) {
            System.err.println(spacer + "Invalid input!");
            System.err.println(spacer + "Valid input format: unmark [list id of the task]");
        }
    }

    /**
     * Marks a task in the task list
     * @param listId id of the task in the task list in an int.
     */
    private static void markTask(int listId) {
        int index = listId - 1;
        if (index < 0 || index >= taskList.size()) {
            System.err.println(spacer + "Index out of range!");
        } else {
            taskList.get(index).mark();
        }
    }

    /**
     * Unmarks a task in the task list.
     * @param listId id of the task in the task list in an int.
     */
    private static void unmarkTask(int listId) {
        int index = listId - 1;
        if (index < 0 || index >= taskList.size()) {
            System.err.println(spacer + "Index out of range!");
        } else {
            taskList.get(index).unmark();
        }
    }

    /**
     * Add a task from user's command.
     * @param cmd user's input command.
     */
    private static void addTask(String cmd) {
        try {
            Task task = createTask(cmd);
            taskList.add(task);
            System.out.println(spacer + "Got it. I've added this task:");
            System.out.println("\t\t" + task);
            System.out.println(spacer + "Now you have " + taskList.size() + " tasks in the list.");
        } catch (IllegalArgumentException e) {
            System.err.println(spacer + e.getMessage());
        }
    }

    /**
     * Create a task from user's input command.
     * @param cmd user command.
     * @return a task instance (can be event, deadline, etc).
     * @throws IllegalArgumentException when user input an invalid command
     */
    private static Task createTask(String cmd) throws IllegalArgumentException {
        String[] cmdArgs = cmd.split(" ");
        if (cmdArgs.length <= 1) {
            throw new IllegalArgumentException("☹ OOPS!!! The description cannot be empty!");
        }
        switch (cmdArgs[0]) {
        case "todo":
            return new Todo(cmdArgs);
        case "deadline":
            return new Deadline(cmdArgs);
        case "event":
            return new Event(cmdArgs);
        default:
            throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // greeting messages
        printDivider();
        System.out.println(spacer + "Hello! I'm Tohru.");
        System.out.println(spacer + "What can I do for you?");
        printDivider();

        // user interactions
        while (true) {
            String cmd = scanner.nextLine();
            String[] cmdArgs = cmd.split(" ");
            printDivider();

            if (cmd.equals("bye")) {
                break;
            } else if (cmd.equals("list")) {
                listTasks();
            } else if (cmdArgs[0].equals("mark")) {
                markTask(cmdArgs[1]);
            } else if (cmdArgs[0].equals("unmark")) {
                unmarkTask(cmdArgs[1]);
            } else {
                addTask(cmd);
            }
            printDivider();
        }

        // exit message
        System.out.println(spacer + "Bye. Hope to see you again soon!");
        printDivider();
    }
}
