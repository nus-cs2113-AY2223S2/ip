package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.Duke.FILE_PATH;
import static duke.Duke.allTasks;

public class Ui {
    private static final Scanner in = new Scanner(System.in);
    public static final String DIVIDER = "____________________________________________________________\n";
    public static final String INVALID_DEADLINE =
            DIVIDER + "Please enter deadline as \"deadline [task] /by [date]\".\n" + DIVIDER;
    public static final String INVALID_EVENT =
            DIVIDER + "Please enter event as \"event [task] /from [date] /to [date]\".\n" + DIVIDER;
    public static final String INVALID_COMMAND =
            DIVIDER + "Sorry, but I don't know what that means :(\n" + DIVIDER;
    public static final String EMPTY_TASK_DESCRIPTION =
            DIVIDER + "Oops! The description of a task cannot be empty.\n" + DIVIDER;

    public static String readCommand() {
        return in.nextLine();
    }
    public static void printWelcomeMessage() {
        System.out.println(DIVIDER +
                "Hello from\n" +
                " ____        _\n" +
                "|  _ \\ _   _| | _____\n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n" +
                "Enter \"help\" to see a list of commands.\n" +
                DIVIDER);
    }

    // functions
    public static void printHelpMessage() {
        System.out.println(DIVIDER +
                " Enter \"list\" to see all tasks\n" +
                " Enter \"todo [task]\" to add a task\n" +
                " Enter \"deadline [task] /by [date]\" to add a deadline\n" +
                " Enter \"event [task] /from [date] /to [date]\" to add an event\n" +
                " Enter \"mark [idx]\" to mark task as done\n" +
                " Enter \"unmark [idx]\" to mark task as not done\n" +
                " Enter \"delete [idx]\" to remove task from list\n" +
                " Enter \"bye\" to exit the program\n" +
                DIVIDER);
    }
    public static void printAddMessage(Task newTask) {
        System.out.println(
                DIVIDER + "Got it. I've added this " + newTask.getType() + ":\n" +
                "  " + newTask + "\n" + DIVIDER);
    }

    public static void printList(ArrayList<Task> allTasks) {
        if (allTasks.size()==0) {
            System.out.println(DIVIDER +
                    "There are no tasks in your list!\n" + DIVIDER);
            return;
        }
        System.out.println(DIVIDER +
                "Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println(i+1 + "." + allTasks.get(i));
        }
        System.out.println(DIVIDER);
    }

    public static void printMarkDone(Task doneTask) {
        System.out.println(DIVIDER +
                "Nice!, I've marked this task as done:\n" +
                "  " + doneTask + "\n" + DIVIDER);
    }

    public static void printMarkNotDone(Task notDoneTask) {
        System.out.println(DIVIDER +
                "OK, I've marked this task as not done yet:\n" +
                "  " + notDoneTask + "\n" + DIVIDER);
    }

    public static void printDeleted(Task deletedTask) {
        System.out.println(DIVIDER +
                "Noted, I've removed this task:\n" +
                "  " + deletedTask + "\n" +
                "Now you have " + (allTasks.size()-1) + " tasks in the list\n" +
                DIVIDER);
    }

    public static void printExitMessage() {
        System.out.println(DIVIDER +
                "Bye. Hope to see you again soon!\n" +
                DIVIDER);
    }

    // error messages
    public static void printErrorForIdx() {
        if (allTasks.size() != 0) {
            System.out.println(DIVIDER +
                    "Please enter [idx] in the form of an integer from 1 to " + allTasks.size() + ".\n" +
                    DIVIDER);
        } else {
            System.out.println(DIVIDER + "There are no tasks in your list!\n" + DIVIDER);
        }
    }

    public static void printErrorForIO() {
        System.out.println(DIVIDER +
                "Something went wrong with the hard disk :(\n" +
                DIVIDER);
    }

    public static void printErrorFileNotFound() {
        System.out.println(DIVIDER +
                "Save file not found, initialising empty list...\n" +
                DIVIDER);
    }

    public static void printInvalidDeadline() {
        System.out.println(INVALID_DEADLINE);
    }

    public static void printInvalidEvent() {
        System.out.println(INVALID_EVENT);
    }

    public static void printInvalidCommand() {
        System.out.println(INVALID_COMMAND);
    }

    public static void printInvalidSaveFile(int counter) {
        System.out.println(DIVIDER + "There is an error in save.txt at line " + (counter+1) + "\n" +
                "Task " + (counter+1) + " has been excluded. You can edit the save file at:\n" +
                FILE_PATH + "\n" + DIVIDER);
    }

    public static void printEmptyTask() {
        System.out.println(EMPTY_TASK_DESCRIPTION);
    }
}
