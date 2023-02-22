package duke.userinterface;

import duke.command.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    /**
     * Generates strings to greet the user and welcome them to the program.
     */
    public void greetUser() {
        printLineSeparator();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    /**
     * Prints a long string of lines ("___") to separate the outputs.
     */
    public void printLineSeparator() {
        System.out.println("___________________________________________________________________________________");
    }

    /**
     * Prints a string to inform the user about an invalid command entered as the input.
     */
    public void printInvalidCommandError() {
        printLineSeparator();
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        printLineSeparator();
    }

    /**
     * Prints a string to inform the user to retype a valid task number.
     */
    public void printInvalidInputError() {
        printLineSeparator();
        System.out.println("OOPS!!! Please key in a valid task number!");
        printLineSeparator();
    }

    /**
     * Prints a string to inform the user that file used to retrieve and store data does not
     * exist, and that the program will attempt to create a file for this exact purpose.
     */
    public void printMissingFileError() {
        printLineSeparator();
        System.out.println("File cannot be found! Creating new file for data storage...");
        printLineSeparator();
    }

    /**
     * Prints a string to inform the user that file creation for storage and data retrieval has
     * failed.
     */
    public void printFileCreationError() {
        printLineSeparator();
        System.out.println("Failed to find or create a file.");
        printLineSeparator();
    }

    /**
     * Prints a string to inform the user that the saving of list details back into the .txt
     * file has failed, due to .txt file being missing.
     */
    public void printFileSavingError() {
        printLineSeparator();
        System.out.println("Unable to save tasks into database. Did you create a .txt file?");
        printLineSeparator();
    }

    /**
     * Prints a string to inform the user of an invalid "list" command input due to the existence
     * of a second string.
     */
    public void printListCommandError() {
        printLineSeparator();
        System.out.println("OOPS!!! A 'list' command cannot contain a second word!");
        printLineSeparator();
    }

    /**
     * Prints a string to inform the user of an invalid "to-do" command input due to wrong format
     * of entry.
     */
    public void printTodoAdditionError() {
        printLineSeparator();
        System.out.println("OOPS!!! A description of a 'todo' cannot be empty. Please follow this format below:");
        System.out.println("    todo <INSERT TASK NAME>");
        printLineSeparator();
    }

    /**
     * Prints a string to inform the user of an invalid "deadline" command input due to wrong format
     * of entry.
     */
    public void printDeadlineAdditionError() {
        printLineSeparator();
        System.out.println("OOPS!!! Did you declare your 'deadline' properly? Please follow this format below:");
        System.out.println("    deadline <INSERT TASK NAME> /by <INSERT END DATE>");
        printLineSeparator();
    }

    /**
     * Prints a string to inform the user of an invalid "event" command input due to wrong format
     * of entry.
     */
    public void printEventAdditionError() {
        printLineSeparator();
        System.out.println("OOPS!!! Did you declare your 'event' properly? Please follow this format below:");
        System.out.println("    event <INSERT TASK NAME> /from <INSERT START DATE> /to <INSERT END DATE>");
        printLineSeparator();
    }

    /**
     * Prints a series of strings to inform the user that the task has been added to the list of tasks, while
     * incrementing the "listSize" variable used to track the total tasks in the list by one.
     *
     * @param list     The ArrayList containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @return Returns the new total number of tasks found in the list.
     */
    public int printNewlyAddedTask(ArrayList<Task> list, int listSize) {
        printLineSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + list.get(listSize));
        listSize += 1;
        System.out.println("Now you have " + listSize + " tasks in the list.");
        printLineSeparator();
        return listSize;
    }

    /**
     * Removes one task from the list of tasks based on the index number of the task inputted by the user, and
     * informs the user of the removal of the selected task from the list.
     *
     * @param list The ArrayList containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @param command The string array containing all individual strings separated by a space (" ") character in
     *                the user inputted string.
     * @return Returns the size of the "list" ArrayList after the removal of one task from the list.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    public int printNewlyRemovedTask(ArrayList<Task> list, int listSize, String[] command) throws DukeException {
        if (command.length == 2 && isNumeric(command[1])) {
            int taskNumber = Integer.parseInt(command[1]);
            if (taskNumber > 0 && taskNumber <= listSize) {
                //If valid task number is given.
                printLineSeparator();
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + list.get(taskNumber - 1).toString());
                listSize -= 1;
                System.out.println("Now you have " + listSize + " tasks in the list.");
                printLineSeparator();
                list.remove(taskNumber - 1);
            } else {
                //If task number given exceeds total tasks.
                throw new DukeException();
            }
        } else {
            throw new DukeException();
        }
        return listSize;
    }

    /**
     * Prints the "list" array, which contains a series of user-created tasks. The tasks will be printed
     * in entry order, starting from the task that was entered into the list first.
     *
     * @param list     The ArrayList containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @param command  The string array containing all individual strings separated by a space (" ") character in
     *                 the user inputted string.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    public void printList(ArrayList<Task> list, int listSize, String[] command) throws DukeException {
        if (command.length == 1) {
            printLineSeparator();
            for (int increment = 0; increment < listSize; increment += 1) {
                System.out.println((increment + 1) + ". " + list.get(increment).toString());
            }
            printLineSeparator();
        } else {
            throw new DukeException();
        }
    }

    /**
     * Prints a string to inform the user that the program is terminating.
     *
     * @param command The string array containing all individual strings separated by a space (" ") character in
     *                the user inputted string.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    public void printGoodbyeMessage(String[] command) throws DukeException {
        if (command.length == 1) {
            printLineSeparator();
            System.out.println("Bye. Hope to see you again soon!");
            printLineSeparator();
        } else {
            //If 'bye' is not the only thing typed.
            throw new DukeException();
        }
    }

    /**
     * Returns false if the second string in the input cannot be converted into a numeric type, true if it
     * can be converted.
     *
     * @param strNum The second string found in the user input.
     * @return Boolean for whether string can be converted into a numeric type (int, double etc.).
     */
    //@@author ngkaiwen123-reused
    //Reused from https://www.baeldung.com/java-check-string-number
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    //@@author

}
