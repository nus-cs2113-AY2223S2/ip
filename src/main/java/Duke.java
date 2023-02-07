import duke.command.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

public class Duke {
    /**
     * Executes the "Duke" program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        //Initialisation
        String line = "start";
        Task[] list = new Task[100];
        int listSize = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        //Get commands while user input is not "bye".
        while (!line.equals("bye")) {
            line = input.nextLine();
            try {
                listSize = handleUserInputs(line, list, listSize);
            } catch (DukeException de) {
                handleException(line);
            }
        }
    }

    /**
     * Returns the number of tasks found in the list, after handling the commands given by the
     * user in the form of a line of string.
     *
     * @param line     The single line of string inputted by the user.
     * @param list     The array containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @return Returns the current size of the list populated by tasks created by the user.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static int handleUserInputs(String line, Task[] list, int listSize) throws DukeException {
        String[] command = line.split(" ");
        //Check the first word in the line of strings (list, mark, unmark, event, etc.)
        switch (command[0]) {
        case "list":
            printList(list, listSize, command);
            break;
        case "mark":
            markAsDone(list, listSize, command);
            break;
        case "unmark":
            markAsUndone(list, listSize, command);
            break;
        case "todo":
            if (command.length == 1) {
                throw new DukeException();
            }
            list[listSize] = new ToDo(line.substring(line.indexOf(' ') + 1));
            listSize = printNewlyAddedTask(list, listSize);
            break;
        case "deadline":
            createNewDeadline(line, list, listSize);
            listSize = printNewlyAddedTask(list, listSize);
            break;
        case "event":
            createNewEvent(line, list, listSize);
            listSize = printNewlyAddedTask(list, listSize);
            break;
        case "bye":
            goodbyeMessage(command);
            break;
        default:
            throw new DukeException();
        }
        return listSize;
    }

    /**
     * Prints the "list" array, which contains a series of user-created tasks. The tasks will be printed
     * in entry order, starting from the task that was entered into the list first.
     *
     * @param list     The array containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @param command  The string array containing all individual strings separated by a space (" ") character in
     *                 the user inputted string.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static void printList(Task[] list, int listSize, String[] command) throws DukeException {
        if (command.length == 1) {
            lineSeparator();
            for (int increment = 0; increment < listSize; increment += 1) {
                System.out.println((increment + 1) + ". " + list[increment].toString());
            }
            lineSeparator();
        } else {
            throw new DukeException();
        }
    }

    /**
     * Creates a new "deadline" task in the "list" array by determining the position of the "/by
     * string in the entire string, in order to retrieve the strings representing the name/description
     * of the "deadline" task and the due date of the task.
     *
     * @param line     The single line of string inputted by the user.
     * @param list     The array containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static void createNewDeadline(String line, Task[] list, int listSize) throws DukeException {
        int byIndex = line.indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException();
        }
        String deadline = line.substring(line.indexOf("deadline") + 9, byIndex - 1);
        list[listSize] = new Deadline(deadline, line.substring(byIndex + 4));
    }

    /**
     * Creates a new "event" task in the "list" array by determining the position of the "/from" and "/to"
     * strings in the entire string, in order to retrieve the strings representing the name/description (event)
     * of the event, the starting date (startDate) and ending date (endDate).
     *
     * @param line     The single line of string inputted by the user.
     * @param list     The array containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static void createNewEvent(String line, Task[] list, int listSize) throws DukeException {
        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new DukeException();
        }
        String event;
        String startDate;
        String endDate;
        if (fromIndex < toIndex) {
            //If user typed "/from" before "/to".
            event = line.substring(line.indexOf("event") + 5, fromIndex - 1);
            startDate = line.substring(fromIndex + 6, toIndex - 1);
            endDate = line.substring(toIndex + 4);
        } else {
            //If user typed "/to" before "/from".
            event = line.substring(line.indexOf("event") + 5, toIndex - 1);
            startDate = line.substring(toIndex + 4, fromIndex - 1);
            endDate = line.substring(fromIndex + 6);
        }
        list[listSize] = new Event(event, startDate, endDate);
    }

    /**
     * Prints a series of strings to inform the user that the task has been added to the list of tasks, while
     * incrementing the "listSize" variable used to track the total tasks in the list by one.
     *
     * @param list     The array containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @return Returns the new total number of tasks found in the list.
     */
    private static int printNewlyAddedTask(Task[] list, int listSize) {
        lineSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + list[listSize].toString());
        listSize += 1;
        System.out.println("Now you have " + listSize + " tasks in the list.");
        lineSeparator();
        return listSize;
    }

    /**
     * Changes the completion status of the user-created task to done, and prints a series of strings
     * to inform the user about the update to the task completion status.
     *
     * @param list     The array containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @param command  The string array containing all individual strings separated by a space (" ") character in
     *                 the user inputted string.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static void markAsDone(Task[] list, int listSize, String[] command) throws DukeException {
        if (command.length == 2 && isNumeric(command[1])) {
            //If user wants to mark task as done and second input after "mark" is a valid number.
            int taskNumber = Integer.parseInt(command[1]);
            if (taskNumber > 0 && taskNumber <= listSize) {
                //If valid task number is given.
                list[taskNumber - 1].markDone();
                lineSeparator();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + list[taskNumber - 1].toString());
                lineSeparator();
            } else {
                //If task number given exceeds total tasks.
                throw new DukeException();
            }
        } else {
            //If user types non-integer inputs after "mark".
            throw new DukeException();
        }
    }

    /**
     * Changes the completion status of the user-created task to NOT done, and prints a series of strings
     * to inform the user about the update to the task completion status.
     *
     * @param list     The array containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @param command  The string array containing all individual strings separated by a space (" ") character in
     *                 the user inputted string.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static void markAsUndone(Task[] list, int listSize, String[] command) throws DukeException {
        if (command.length == 2 && isNumeric(command[1])) {
            //If user wants to mark task as not done and second input after "unmark" is a valid number.
            int taskNumber = Integer.parseInt(command[1]);
            if (taskNumber > 0 && taskNumber <= listSize) {
                //If valid task number is given.
                list[taskNumber - 1].unmarkDone();
                lineSeparator();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + list[taskNumber - 1].toString());
                lineSeparator();
            } else {
                //If task number given exceeds total tasks in the list.
                throw new DukeException();
            }
        } else {
            //If user types non-integer inputs after "unmark".
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
    private static void goodbyeMessage(String[] command) throws DukeException {
        if (command.length == 1) {
            lineSeparator();
            System.out.println("Bye. Hope to see you again soon!");
            lineSeparator();
        } else {
            //If 'bye' is not the only thing typed.
            throw new DukeException();
        }
    }

    /**
     * Prints a series of error message strings depending on the wrong or partially wrong input by the user.
     *
     * @param line The single line of string inputted by the user.
     */
    private static void handleException(String line) {
        String[] command = line.split(" ");
        switch (command[0]) {
        case "list":
            lineSeparator();
            System.out.println("OOPS!!! A 'list' command cannot contain a second word!");
            lineSeparator();
            break;
        case "todo":
            lineSeparator();
            System.out.println("OOPS!!! A description of a 'todo' cannot be empty. Please follow this format below:");
            System.out.println("    todo <INSERT TASK NAME>");
            lineSeparator();
            break;
        case "event":
            lineSeparator();
            System.out.println("OOPS!!! Did you declare your 'event' properly? Please follow this format below:");
            System.out.println("    event <INSERT TASK NAME> /from <INSERT START DATE> /to <INSERT END DATE>");
            lineSeparator();
            break;
        case "deadline":
            lineSeparator();
            System.out.println("OOPS!!! Did you declare your 'deadline' properly? Please follow this format below:");
            System.out.println("    deadline <INSERT TASK NAME> /by <INSERT END DATE>");
            lineSeparator();
            break;
        case "mark":
        case "unmark":
            printInvalidInputError();
            break;
        default:
            printInvalidCommandError();
            break;
        }
    }

    /**
     * Prints a string to inform the user about an invalid command entered as the input.
     */
    private static void printInvalidCommandError() {
        lineSeparator();
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        lineSeparator();
    }

    /**
     * Prints a string to inform the user to retype a valid task number.
     */
    private static void printInvalidInputError() {
        lineSeparator();
        System.out.println("OOPS!!! Please key in a valid task number!");
        lineSeparator();
    }

    /**
     * Prints a long string of lines ("___") to separate the outputs.
     */
    public static void lineSeparator() {
        System.out.println("___________________________________________________________________________________");
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
    public static boolean isNumeric(String strNum) {
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
