import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents the main Duke programme that users will interact with
 */

public class Duke {
    public static final List<String> taskTypes = Arrays.asList("todo", "deadline", "event");
    public static final List<String> listEditableCommands =
            Arrays.asList("todo", "deadline", "event", "mark", "unmark", "delete");
    public static final List<String> commands =
            Arrays.asList("todo", "deadline", "event", "mark", "unmark", "list", "find", "delete", "bye");
    static final int PARTITION_TO_TWO = 2;

    public static void printHorizontalLine() {
        System.out.print("    ____________________________________________________________\n");
    }

    /**
     * Prints entries that contains the keyword(s).
     *
     * @param listOfTasks        ArrayList of tasks.
     * @param currentNumberIndex Number of tasks present in the list.
     * @param keyWord            keyword to search for in each entry
     */
    public static void findEntry(ArrayList<Task> listOfTasks, int currentNumberIndex, String keyWord) {
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < currentNumberIndex; ++i) {
            int indexInList = i+1;
            if (listOfTasks.get(i).taskLabel.contains(keyWord) || listOfTasks.get(i).description.contains(keyWord)) {
                System.out.print("     " + indexInList + "." + listOfTasks.get(i).taskLabel
                        + listOfTasks.get(i).getStatusIcon() + " ");
                System.out.println(listOfTasks.get(i).description);
            }
        }
    }

    /**
     * Prints all the entries in the list.
     *
     * @param listOfTasks        ArrayList of tasks.
     * @param currentNumberIndex Number of tasks present in the list.
     */
    public static void listing(ArrayList<Task> listOfTasks, int currentNumberIndex) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < currentNumberIndex; ++i) {
            int counter = i + 1;
            System.out.print("     " + counter + "." + listOfTasks.get(i).taskLabel +
                    listOfTasks.get(i).getStatusIcon() + " ");
            System.out.println(listOfTasks.get(i).description);
        }
    }

    /**
     * Removes entry at an index
     * and returns the number of tasks in the list after removal.
     *
     * @param listOfTasks        ArrayList of tasks.
     * @param currentNumberIndex Number of tasks present in the list.
     * @param toDelete           index of entry to delete.
     * @return Number of tasks in the list after removal.
     */
    public static int deleting(ArrayList<Task> listOfTasks, int currentNumberIndex, int toDelete) {
        System.out.println("     Noted. I've removed this task:");
        currentNumberIndex -= 1;
        listOfTasks.remove(toDelete - 1);
        System.out.println("     Now you have " + currentNumberIndex + " tasks in the list.");
        return currentNumberIndex;
    }

    /**
     * Throws exception if command is invalid.
     *
     * @throws InvalidCommand if isNotValidCommand is true.
     */
    public static void checkIfValid(String[] lineComponents) throws InvalidCommand {
        boolean isNotValidCommand = !commands.contains(lineComponents[0]);
        if (isNotValidCommand) {
            throw new InvalidCommand();
        }
    }

    /**
     * Throws exception if command is empty after the instruction word.
     *
     * @throws InvalidCommand if isEmptyCommand is true.
     */
    public static void checkIfEmpty(String[] lineComponents) throws InvalidCommand {
        boolean isEmptyCommand = (taskTypes.contains(lineComponents[0]) && (lineComponents.length == 1 || lineComponents[1].equals("")));
        if (isEmptyCommand) {
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
        ArrayList<Task> taskArrayList = new ArrayList<>(); // Array of Tasks
        int currentTaskNumber = 0; // Current number of tasks

        try {
            LoadFile.initFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            currentTaskNumber = LoadFile.loadFileContents("data\\duke.txt", taskArrayList, currentTaskNumber);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine(); // Take in input line
            String[] lineComponents = line.split(" ", PARTITION_TO_TWO); // Split the input line
            String type = lineComponents[0];
            try {
                checkIfValid(lineComponents);
                try {
                    printHorizontalLine();
                    checkIfEmpty(lineComponents);
                    switch (type) {
                    case "todo":
                        currentTaskNumber = Todo.add(line, taskArrayList, currentTaskNumber);
                        break;
                    case "deadline":
                        currentTaskNumber = Deadline.add(line, taskArrayList, currentTaskNumber);
                        break;
                    case "event":
                        currentTaskNumber = Event.add(line, taskArrayList, currentTaskNumber);
                        break;
                    case "list":
                        listing(taskArrayList, currentTaskNumber);
                        break;
                    case "find":
                        findEntry(taskArrayList, currentTaskNumber, lineComponents[1]);
                        break;
                    case "delete":
                        currentTaskNumber = deleting(taskArrayList, currentTaskNumber, Integer.parseInt(lineComponents[1]));
                        break;
                    case "bye":
                        System.out.println("     Bye. Hope to see you again soon!");
                        break;
                    default:
                        if (line.matches("mark \\d") || line.matches("unmark \\d")) {
                            Task.markOrUnmark(line, taskArrayList, currentTaskNumber);
                            break;
                        }
                    }
                } catch (InvalidCommand e) {
                    System.out.println("     ☹ OOPS!!! The description of a " +
                            "" + lineComponents[0] + " cannot be empty.");
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
            try {
                if (listEditableCommands.contains(type)) {
                    WriteFile.writeToFile("data\\duke.txt", taskArrayList);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
