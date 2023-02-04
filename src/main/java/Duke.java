import java.util.Scanner;
import java.util.Arrays;
import java.util.List;


public class Duke {
    public static final List<String> taskTypes = Arrays.asList("todo", "deadline", "event");
    public static final List<String> commands = Arrays.asList("todo", "deadline", "event", "mark", "unmark", "list", "bye");

    public static void printHorizontalLine() {
        System.out.print("    ____________________________________________________________\n");
    }

    public static void listing(Task[] listOfTasks, int currentNumberIndex) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < currentNumberIndex; ++i) {
            int counter = i + 1;
            System.out.print("     " + counter + "." + listOfTasks[i].taskLabel + listOfTasks[i].getStatusIcon() + " ");
            System.out.println(listOfTasks[i].description);
        }
    }

    public static void checkValidity(String[] lineComponents) throws InvalidCommand {
        boolean isNotValidCommand = !commands.contains(lineComponents[0]);
        if (isNotValidCommand) {
            throw new InvalidCommand();
        }
    }

    public static void checkIfEmpty(String[] lineComponents) throws InvalidCommand {
        boolean isNotValidCommand = (taskTypes.contains(lineComponents[0]) && lineComponents.length == 1);
        if (isNotValidCommand) {
            throw new InvalidCommand();
        }
    }


    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke \n"
                + "     What can I do for you? \n"
                + "    ____________________________________________________________\n";
        System.out.println(greet);

        String line;
        final int TOTAL_TASKS = 100; // Total number of tasks
        Task[] tasksList = new Task[TOTAL_TASKS]; // Array of Tasks
        int currentNumber = 0; // Current number of tasks

        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine(); // Take in input line
            String[] lineComponents = line.split(" ", 2); // Split the input line
            String type = lineComponents[0];
            try {
                checkValidity(lineComponents);
                try {
                    printHorizontalLine();
                    checkIfEmpty(lineComponents);
                    switch (type) {
                    case "todo":
                        currentNumber = Todo.add(line, tasksList, currentNumber);
                        break;
                    case "deadline":
                        currentNumber = Deadline.add(line, tasksList, currentNumber);
                        break;
                    case "event":
                        currentNumber = Event.add(line, tasksList, currentNumber);
                        break;
                    case "list":
                        listing(tasksList, currentNumber);
                        break;
                    case "bye":
                        System.out.println("     Bye. Hope to see you again soon!");
                        break;
                    default:
                        if (line.matches("mark \\d") || line.matches("unmark \\d")) {
                            Task.markOrUnmark(line, tasksList, currentNumber);
                            break;
                        }
                    }
                } catch (InvalidCommand e) {
                        System.out.println("     ☹ OOPS!!! The description of a " + lineComponents[0] + " cannot be empty.");
                } finally {
                    printHorizontalLine();
                }
                if (line.matches("bye")) {
                    break;
                }
            } catch (InvalidCommand e) {
                printHorizontalLine();
                System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printHorizontalLine();
            }
        }
    }
}



