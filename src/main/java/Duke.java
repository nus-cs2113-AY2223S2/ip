import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Task> taskList = new ArrayList<>();

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
        System.out.println("\t Here are the tasks in your list:");
        int cnt = 0;
        for (Task task : taskList) {
            System.out.printf("\t %d.%s\n", ++cnt, task);
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
            System.err.println("\t Invalid input!");
            System.err.println("\t Valid input format: mark [list id of the task]");
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
            System.err.println("\t Invalid input!");
            System.err.println("\t Valid input format: unmark [list id of the task]");
        }
    }

    /**
     * Marks a task in the task list
     * @param listId id of the task in the task list in an int.
     */
    private static void markTask(int listId) {
        int index = listId - 1;
        if (index < 0 || index >= taskList.size()) {
            System.err.println("\t Index out of range!");
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
            System.err.println("\t Index out of range!");
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
            System.out.println("\t Got it. I've added this task:");
            System.out.println("\t\t" + task);
            System.out.println("\t Now you have " + taskList.size() + " tasks in the list.");
        } catch (IllegalArgumentException e) {
            System.err.println("\t Invalid task type!");
            System.err.println("\t Valid task types: todo, deadline, event.");
        }
    }

    /**
     * Create a task from user's input command.
     * @param cmd user command.
     * @return a task instance (can be event, deadline, etc).
     * @throws IllegalArgumentException when user input an invalid command
     */
    private static Task createTask(String cmd) {
        String[] cmdArgs = cmd.split(" ");
        switch (cmdArgs[0]) {
        case "todo":
            return createTodo(cmdArgs);
        case "deadline":
            return createDeadline(cmdArgs);
        case "event":
            return createEvent(cmdArgs);
        default:
            throw new IllegalArgumentException("Invalid task type!");
        }
    }

    /**
     * Creates a Todo instance from user input arguments.
     * @param cmdArgs an array of command words, including contents.
     * @return a Todo instance.
     */
    private static Todo createTodo(String[] cmdArgs) {
        assert cmdArgs[0].equals("todo");
        StringBuilder content = new StringBuilder();
        for (int i = 1; i < cmdArgs.length; ++i) {
            content.append(cmdArgs[i]).append(" ");
        }
        return new Todo(content.toString().trim());
    }

    /**
     * Creates a Deadline instance from user input arguments.
     * @param cmdArgs an array of command words, including contents, by date.
     * @return a Deadline instance.
     */
    private static Deadline createDeadline(String[] cmdArgs) {
        assert cmdArgs[0].equals("deadline");
        StringBuilder content = new StringBuilder();
        StringBuilder by = new StringBuilder();
        boolean buildContent = true;
        for (int i = 1; i < cmdArgs.length; ++i) {
            if (cmdArgs[i].equals("/by")) {
                buildContent = false;
                continue;
            }
            if (buildContent) {
                content.append(cmdArgs[i]).append(" ");
            } else {
                by.append(cmdArgs[i]).append(" ");
            }
        }
        return new Deadline(content.toString().trim(), by.toString().trim());
    }

    /**
     * Creates an Event instance from user input arguments.
     * @param cmdArgs an array of command words, including content, from time, to time.
     * @return an Event instance.
     */
    private static Event createEvent(String[] cmdArgs) {
        assert cmdArgs[0].equals("event");
        StringBuilder content = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        int buildingSteps = 0; // 0: content, 1: from, 2: to
        for (int i = 1; i < cmdArgs.length; ++i) {
            if (cmdArgs[i].equals("/from")) {
                buildingSteps = 1;
                continue;
            } else if (cmdArgs[i].equals("/to")) {
                buildingSteps = 2;
                continue;
            }

            switch (buildingSteps) {
            case (0):
                content.append(cmdArgs[i]).append(" ");
                break;
            case (1):
                from.append(cmdArgs[i]).append(" ");
                break;
            case (2):
                to.append(cmdArgs[i]).append(" ");
                break;
            }
        }
        return new Event(content.toString().trim(), from.toString().trim(), to.toString().trim());
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
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
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
        System.out.println("\t Bye. Hope to see you again soon!");
        printDivider();
    }
}
