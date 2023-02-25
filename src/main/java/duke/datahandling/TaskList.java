package duke.datahandling;

import duke.command.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList extends Storage {

    /**
     * Creates a new "to-do" task in the "list" arrayList by determining what is typed after the spacing
     * between the "to-do" command and the task to the right of the spacing.
     *
     * @param line    The single line of string inputted by the user.
     * @param list    The ArrayList containing all information about existing tasks and their completion status.
     * @param command The string array containing all individual strings separated by a space (" ") character in
     *                the user inputted string.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    public static void createNewTodo(String line, ArrayList<Task> list, String[] command) throws DukeException {
        if (command.length == 1) {
            throw new DukeException();
        }
        list.add(new ToDo(line.substring(line.indexOf(' ') + 1)));
    }

    /**
     * Creates a new "deadline" task in the "list" array by determining the position of the "/by
     * string in the entire string, in order to retrieve the strings representing the name/description
     * of the "deadline" task and the due date of the task.
     *
     * @param line The single line of string inputted by the user.
     * @param list The ArrayList containing all information about existing tasks and their completion status.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    public static void createNewDeadline(String line, ArrayList<Task> list) throws DukeException {
        int byIndex = line.indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException();
        }
        String deadline = line.substring(line.indexOf("deadline") + 9, byIndex - 1);
        list.add(new Deadline(deadline, line.substring(byIndex + 4)));
    }

    /**
     * Creates a new "event" task in the "list" array by determining the position of the "/from" and "/to"
     * strings in the entire string, in order to retrieve the strings representing the name/description (event)
     * of the event, the starting date (startDate) and ending date (endDate).
     *
     * @param line The single line of string inputted by the user.
     * @param list The ArrayList containing all information about existing tasks and their completion status.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    public static void createNewEvent(String line, ArrayList<Task> list) throws DukeException {
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
        list.add(new Event(event, startDate, endDate));
    }

    /**
     * Changes the completion status of the user-created task to done, and prints a series of strings
     * to inform the user about the update to the task completion status.
     *
     * @param list     The ArrayList containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @param command  The string array containing all individual strings separated by a space (" ") character in
     *                 the user inputted string.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    public static void markAsDone(ArrayList<Task> list, int listSize, String[] command) throws DukeException {
        if (command.length == 2 && isNumeric(command[1])) {
            //If user wants to mark task as done and second input after "mark" is a valid number.
            int taskNumber = Integer.parseInt(command[1]);
            if (taskNumber > 0 && taskNumber <= listSize) {
                //If valid task number is given.
                list.get(taskNumber - 1).markDone();
                dukeUserInterface.printLineSeparator();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + list.get(taskNumber - 1).toString());
                dukeUserInterface.printLineSeparator();
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
     * @param list     The ArrayList containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @param command  The string array containing all individual strings separated by a space (" ") character in
     *                 the user inputted string.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    public static void markAsUndone(ArrayList<Task> list, int listSize, String[] command) throws DukeException {
        if (command.length == 2 && isNumeric(command[1])) {
            //If user wants to mark task as not done and second input after "unmark" is a valid number.
            int taskNumber = Integer.parseInt(command[1]);
            if (taskNumber > 0 && taskNumber <= listSize) {
                //If valid task number is given.
                list.get(taskNumber - 1).unmarkDone();
                dukeUserInterface.printLineSeparator();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + list.get(taskNumber - 1).toString());
                dukeUserInterface.printLineSeparator();
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
