package duke.util;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * A <code>Parser</code> object takes care of the processing of user
 * inputs and registering tasks from any pre-existing Duke files.
 */
public class Parser {

    private static Ui ui;

    /**
     * Checks for input from user via command line.
     *
     * @return String containing user input.
     * @throws NoSuchElementException when program is closed.
     */
    public static String getInput() throws NoSuchElementException {
        Scanner in = new Scanner(System.in);
        String line = "";
        try {
            line = in.nextLine();
        } catch (NoSuchElementException e) {
            ui.printExiting();
        }
        return line;
    }

    /**
     * Processes input from user given by ui function.
     * Calls different functions based on the given commands.
     *
     * @param line String containing user input
     * @throws DukeException If no keywords are found.
     */
    public static Command processInput(String line, TaskList taskList) {
        if (line.startsWith("todo")) {
            return(new CreateTodoCommand(line));
        } else if (line.startsWith("deadline")) {
            return(new CreateDeadlineCommand(line));
        } else if (line.startsWith("event")) {
            return(new CreateEventCommand(line));
        } else if (line.startsWith("list")) {
            return(new ListTaskCommand());
        } else if (line.startsWith("mark") || line.startsWith("unmark")) {
            return(new MarkTaskCommand(line, taskList));
        } else if (line.startsWith("delete")) {
            return(new DeleteTaskCommand(line));
        }else if (line.startsWith("find")) {
            return(new SearchCommand(line));
        } else if (line.equals("bye")) {
            return(new ExitCommand());
        } else {
            return (new InvalidCommand());
        }
    }

    /**
     * Registers task found in pre-existing Duke data file on system.
     *
     * @param lists List to store all tasks.
     * @param inputLines Divided strings from user input
     * @return List containing all tasks.
     */
    public static ArrayList<Task> registerDukeFileTasks (ArrayList<Task> lists, String[] inputLines) {
        String type = inputLines[0].strip();
        boolean isDone = (inputLines[1].equals("1")) ? true : false;
        if (type.equals("T")) {
            String description = inputLines[2];
            lists.add(new Todo(description));
        } else if (type.equals("D")) {
            String description = inputLines[2];
            String deadline = inputLines[3].strip();
            lists.add(new Deadline(description, deadline));
        } else if (type.equals("E")) {
            String description = inputLines[2];
            String start = inputLines[3];
            String end = inputLines[4];
            lists.add(new Event(description, start, end));
        }

        if (isDone) {
            lists.get(lists.size() - 1).markAsDoneSilent();
        }
        return lists;
    }

    /**
     * Parses string if given in dd-MM-yyyy HH:mm format and translates to LLL dd yyyy HH:mm format.
     * Else if string not in given format, return empty string.
     *
     * @param date String containing date to be parsed.
     * @return Re-formatted date in string format or empty string.
     * @throws DateTimeParseException if user does not provide date in required format.
     */
    public static String formatDateTime(String date) throws DateTimeParseException {
        try {
            DateTimeFormatter dateTimeFormatterInput = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
            DateTimeFormatter dateTimeFormatterOutput = DateTimeFormatter.ofPattern("LLL dd uuuu HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(date, dateTimeFormatterInput);
            String formattedDateTime = dateTime.format(dateTimeFormatterOutput);
            return formattedDateTime;
        } catch (DateTimeParseException e) {
            System.out.println("The date provided must be of the following format: dd-MM-yyyy HH:MM");
            return "";
        }
    }
}
