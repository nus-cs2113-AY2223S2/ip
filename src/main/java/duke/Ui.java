package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final Scanner in = new Scanner(System.in);

    public static String readCommand() {
        return in.nextLine();
    }

    public static void showLine() {
        System.out.println("____________________________________________________________");
    }
    public static void printWelcomeMessage() {
        showLine();
        System.out.println(
                "Hello from\n" +
                " ____        _\n" +
                "|  _ \\ _   _| | _____\n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n" +
                "Enter \"help\" to see a list of commands.");
        showLine();
    }

    // functions
    public static void printHelpMessage() {
        System.out.println(
                " Enter \"list\" to see all tasks\n" +
                " Enter \"todo [task]\" to add a task\n" +
                " Enter \"deadline [task] /by [date]\" to add a deadline\n" +
                " Enter \"event [task] /from [date] /to [date]\" to add an event\n" +
                " Enter \"mark [idx]\" to mark task as done\n" +
                " Enter \"unmark [idx]\" to mark task as not done\n" +
                " Enter \"delete [idx]\" to remove task from list\n" +
                " Enter \"bye\" to exit the program\n" +
                "\n For Duke to understand [date], you can enter it in the form of\n     " +
                Task.storePattern +
                "\n eg. \"2023-10-30T23:59\" for Oct 20 2023, 11:59PM");
    }
    public static void printAddMessage(Task newTask) {
        System.out.println(
                "Got it. I've added this " + newTask.getType() + ":\n" +
                "  " + newTask);
    }

    public static void printList(ArrayList<Task> allTasks) {
        if (allTasks.size()==0) {
            System.out.println("There are no tasks in your list!\n");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println(i+1 + "." + allTasks.get(i));
        }
    }

    public static void printMarkDone(Task doneTask) {
        System.out.println(
                "Nice!, I've marked this task as done:\n" +
                "  " + doneTask);
    }

    public static void printMarkNotDone(Task notDoneTask) {
        System.out.println(
                "OK, I've marked this task as not done yet:\n" +
                "  " + notDoneTask);
    }

    public static void printDeleted(Task deletedTask, int size) {
        System.out.println(
                "Noted, I've removed this task:\n" +
                "  " + deletedTask + "\n" +
                "Now you have " + (size - 1) + " tasks in the list");
    }

    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    // error messages
    public static void printErrorForIdx(int size) {
        if (size != 0) {
            System.out.println("Please enter [idx] in the form of an integer from 1 to " + size);
        } else {
            System.out.println("There are no tasks in your list!");
        }
    }

    public static void printErrorForIO() {
        System.out.println("Something went wrong with the hard disk :(");
    }

    public static void printErrorFileNotFound() {
        System.out.println("Save file not found, initialising empty list...");
    }

    public static void printInvalidDeadline() {
        System.out.println("Please enter deadline as \"deadline [task] /by [date]\".");
    }

    public static void printInvalidEvent() {
        System.out.println("Please enter event as \"event [task] /from [date] /to [date]\".");
    }

    public static void printInvalidCommand() {
        System.out.println("Sorry, but I don't know what that means :(");
    }

    public static void printInvalidSaveFile(int counter, String filePath) {
        showLine();
        System.out.println("There is an error in save.txt at line " + (counter+1) + "\n" +
                "Task " + (counter+1) + " has been excluded. You can edit the save file at:\n" +
                filePath);
        showLine();
    }

    public static void printEmptyTask() {
        System.out.println("Oops! The description of a task cannot be empty.");
    }
}
