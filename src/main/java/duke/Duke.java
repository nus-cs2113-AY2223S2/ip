package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasklist.task.Task;
import duke.tasklist.task.Todo;
import duke.tasklist.task.Deadline;
import duke.tasklist.task.Event;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static final String spacer = "\t ";
    private static final String DATA_PATH = "data";
    private static final String DATA_FILE = "task-list.csv";

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

    public static void deleteTask(String listId) {
        try {
            String taskStr = deleteTask(Integer.parseInt(listId));
            System.out.println(spacer + "Noted. I've removed this task:\n");
            System.out.println(spacer + taskStr);
            System.out.printf(spacer + "Now you have %d tasks in the list.\n", taskList.size());
        } catch (NumberFormatException e) {
            System.err.println("Please input a valid index!");
        }
    }

    public static String deleteTask(int listId) {
        int index = listId - 1;
        if (index < 0 || index >= taskList.size()) {
            System.err.println(spacer + "Index out of range!");
        } else {
            String taskStr = taskList.get(index).toString();
            taskList.remove(index);
            return taskStr;
        }
        return null;
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

    private static void initData(String path, String fileName) {
        File directory = new File(path);
        directory.mkdir();
        File file = new File(path + "/" + fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void readData(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String otherThanQuote = " [^\"] ";
            String quotedString = String.format(" \" %s* \" ", otherThanQuote);
            String regex = String.format("(?x) " + // enable comments, ignore white spaces
                            ",                         " + // match a comma
                            "(?=                       " + // start positive look ahead
                            "  (?:                     " + //   start non-capturing group 1
                            "    %s*                   " + //     match 'otherThanQuote' zero or more times
                            "    %s                    " + //     match 'quotedString'
                            "  )*                      " + //   end group 1 and repeat it zero or more times
                            "  %s*                     " + //   match 'otherThanQuote'
                            "  $                       " + // match the end of the string
                            ")                         ", // stop positive look ahead
                    otherThanQuote, quotedString, otherThanQuote);
            String[] args = line.split(regex, -1);
            try {
                boolean isMarked = Integer.parseInt(args[1]) == 1;
                for (int i = 2; i < args.length; ++i) {
                    args[i] = args[i].substring(1, args[i].length()-1);
                }
                String content = args[2];
                switch (args[0]) {
                case "todo":
                    taskList.add(new Todo(content, isMarked));
                    break;
                case "deadline":
                    taskList.add(new Deadline(content, args[3], isMarked));
                    break;
                case "event":
                    taskList.add(new Event(content, args[3], args[4], isMarked));
                    break;
                default:
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Cannot add task: " + line);
            }
        }
    }

    private static void writeData(String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (Task task : taskList) {
                fw.write(task.toCSV() + System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        run(args);
    }

    public static void run(String[] args) {

        try {
            readData(DATA_PATH + "/" + DATA_FILE);
            System.out.println(spacer + "Read data from file " + DATA_PATH + "/" + DATA_FILE);
        } catch (IOException ioe) {
            System.out.println(spacer + "Create new data file in " + DATA_PATH + "/" + DATA_FILE);
            initData(DATA_PATH, DATA_FILE);
        }
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
            } else if (cmdArgs[0].equals("delete")) {
                deleteTask(cmdArgs[1]);
            } else {
                addTask(cmd);
            }
            writeData(DATA_PATH + "/" + DATA_FILE);
            printDivider();
        }

        // exit message
        System.out.println(spacer + "Bye. Hope to see you again soon!");
        printDivider();
    }
}
