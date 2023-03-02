package duke;

import duke.addable.Task;
import duke.exception.UnknownCommandException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to print output to user
 */
public class Ui {
    public Ui() {
        return;
    }
    private static final String[] INVALID_INPUT_MESSAGE = {
            "Invalid input format",
            "Please see below for a list of valid commands",
            "- list",
            "- mark [TASK NUMBER]",
            "- unmark [TASK NUMBER]",
            "- todo [TASK DESCRIPTION]",
            "- event [EVENT DESCRIPTION] /from [START DATE] /to [END DATE]",
            "- deadline [DEADLINE DESCRIPTION] /by [DUE DATE]",
            "- delete [TASK NUMBER]",
            "- find [STRING]"
    };
    private static final String INDENT = "      ";

    /**
     * Prints message saying that input is invalid and giving a list of valid inputs
     * @return nothing
     */
    public static void printInvalidInputMessage() {
        printMessage(INVALID_INPUT_MESSAGE);
    }

    /**
     * Prints message saying that input is invalid, why input is invalid, and giving a list of valid inputs
     * @param extraClarification Explanation as to why input is invalid
     * @return nothing
     */
    public static void printInvalidInputMessage(String extraClarification) {
        String[] message = Arrays.copyOf(INVALID_INPUT_MESSAGE, INVALID_INPUT_MESSAGE.length);
        message[0] = extraClarification;
        printMessage(message);
    }

    private static String getFormattedTask(Task task, int number) {
        return number + ". " + task.toString();
    }

    /**
     * Constructs formatted list of tasks with a heading
     *
     * @param heading Heading for list
     * @param tasks List of tasks to format
     * @return formatted list of tasks with heading
     */
    public static String[] getFormattedList(String heading, ArrayList<Task> tasks) {
        int numTasks = tasks.size();
        String[] formattedList = new String[numTasks + 1];
        formattedList[0] = heading;
        for (int i = 0; i < numTasks; i++) {
            formattedList[i + 1] = getFormattedTask(tasks.get(i), i + 1);
        }
        return formattedList;
    }

    /**
     * Prints the introductory statement for the program
     * @return nothing
     */
    public static void printIntro() {
        String[] intro = {"Hello! I'm Duke", "What can I do for you?\n Loading tasks from hard disk..."};
        printMessage(intro);

    }

    /**
     * Prints the final statement for the program
     * @return nothing
     */
    public static void printExit() {
        printMessage("Bye. Hope to see you again soon!");
    }
    /**
     * Prints formatted message (indented and surrounded by lines above and below)
     * @param message String to print
     * @return nothing
     */
    public static void printMessage(String message) {
        printSeparator();
        printIndent();
        System.out.print(message + "\n");
        printSeparator();
    }
    /**
     * Prints formatted message (indented and surrounded by lines above and below) from ArrayList of strings
     * @param message ArrayList of strings to print
     * @return nothing
     */
    public static void printMessage(String[] message) {
        printSeparator();
        for (String line : message) {
            if (!line.equals("")) {
                printIndent();
                System.out.print(line + "\n");
            }
        }
        printSeparator();
    }

    private static void printIndent() {
        System.out.print(INDENT);
    }

    private static void printSeparator() {
        System.out.print("   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    /**
     * Prints formatted message for when task is added
     * @param addedTask Task that has been added
     * @param numTasks Current number of tasks in task list
     * @return nothing
     */
    public static void printAddTaskMessage(Task addedTask, int numTasks) {
        printMessage(getAddTaskMessage(addedTask, numTasks));
    }
    private static String[] getAddTaskMessage(Task addedTask, int numTasks) {
        String[] message = {
                "Got it. I've added this task:",
                INDENT + addedTask.toString(),
                "Now you have " + numTasks + " tasks in the list."
        };
        return message;
    }
}
