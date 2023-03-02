package duke;

import duke.addable.Task;
import java.util.ArrayList;
import java.util.Arrays;

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
            "- delete [TASK NUMBER]"
    };
    private static final String INDENT = "      ";

    public static void printInvalidInputMessage() {
        printMessage(INVALID_INPUT_MESSAGE);
    }
    public static void printInvalidInputMessage(String extraClarification) {
        String[] message = Arrays.copyOf(INVALID_INPUT_MESSAGE, INVALID_INPUT_MESSAGE.length);
        message[0] = extraClarification;
        printMessage(message);
    }
    public static String getFormattedTask(Task task, int number) {
        return number + ". " + task.toString();
    }

    public static String[] getFormattedList(String heading, ArrayList<Task> tasks) {
        int numTasks = tasks.size();
        String[] formattedList = new String[numTasks + 1];
        formattedList[0] = heading;
        for (int i = 0; i < numTasks; i++) {
            formattedList[i + 1] = getFormattedTask(tasks.get(i), i + 1);
        }
        return formattedList;
    }

    public static void printIntro() {
        String[] intro = {"Hello! I'm Tom", "What can I do for you?\n Loading tasks from hard disk..."};
        printMessage(intro);

    }

    public static void printExit() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public static void printMessage(String message) {
        printSeparator();
        printIndent();
        System.out.print(message + "\n");
        printSeparator();
    }

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

    public static void printIndent() {
        System.out.print(INDENT);
    }

    public static void printSeparator() {
        System.out.print("   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public static void printAddTaskMessage(Task addedTask, int numTasks) {
        printMessage(getAddTaskMessage(addedTask, numTasks));
    }
    public static String[] getAddTaskMessage(Task addedTask, int numTasks) {
        String[] message = {
                "Got it. I've added this task:",
                INDENT + addedTask.toString(),
                "Now you have " + numTasks + " tasks in the list."
        };
        return message;
    }
}
