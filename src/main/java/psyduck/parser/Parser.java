package psyduck.parser;

import psyduck.exceptions.*;
import psyduck.ui.ErrorMessage;

import java.util.Scanner;

/**
 * Represents the parsing component of Psyduck
 */
public class Parser {
    private static final int NOT_FOUND = -1;

    /**
     * Parses the string input to return the command string.
     *
     * @param input The string input from the user
     * @return the string containing the command
     */
    public String prepareCommand(String input) {
        input = input.trim();
        int space = input.indexOf(" ");
        String command;
        if (space == NOT_FOUND) {
            command = input;
        } else {
            command = input.substring(0, space);
        }
        return command;
    }

    /**
     * Parses the string input to return the string array containing the strings
     * required for creating a deadline task.
     *
     * @param input The string input from the user
     * @return the string array containing the strings required to create a deadline.
     * @throws TaskEmptyException             thrown when the input string does not contain a description.
     * @throws InvalidDeadlineFormatException thrown when the input string does not contain a deadline.
     */
    public String[] prepareDeadline(String input) throws
            TaskEmptyException, InvalidDeadlineFormatException {
        input = input.trim();
        int space = input.indexOf(" ");
        if (space == NOT_FOUND) {
            throw new TaskEmptyException();
        }
        int byPos = input.indexOf("/by");
        if (byPos == NOT_FOUND) {
            throw new InvalidDeadlineFormatException();
        }
        String description = input.substring(space + 1, byPos);
        String by = input.substring(byPos + 4);
        String[] output = new String[2];
        output[0] = description;
        output[1] = by;
        return output;
    }

    /**
     * Parses the string input to return the string array containing the strings
     * required for creating an event task.
     *
     * @param input The string input from the user
     * @return the string array containing the strings required to create an event.
     * @throws TaskEmptyException          thrown when the input string does not contain a description.
     * @throws InvalidEventFormatException thrown when the input string does not contain a from string or to string
     */
    public String[] prepareEvent(String input) throws
            TaskEmptyException, InvalidEventFormatException {
        input = input.trim();
        int space = input.indexOf(" ");
        if (space == NOT_FOUND) {
            throw new TaskEmptyException();
        }
        int fromPos = input.indexOf("/from");
        int toPos = input.indexOf("/to");
        if (fromPos == NOT_FOUND || toPos == NOT_FOUND) {
            throw new InvalidEventFormatException();
        }
        String description = input.substring(space + 1, fromPos);
        String from = input.substring(fromPos + 6, toPos);
        String to = input.substring(toPos + 4);
        String[] output = new String[3];
        output[0] = description;
        output[1] = from;
        output[2] = to;
        return output;
    }

    /**
     * Parses the string input to return the string required to create a to-do task.
     *
     * @param input the string input from the user
     * @return the string containing the description required to create a to-do task.
     * @throws TaskEmptyException thrown when the input string does not contain a description.
     */
    public String prepareToDo(String input) throws TaskEmptyException {
        input = input.trim();
        int space = input.indexOf(" ");
        if (space == NOT_FOUND) {
            throw new TaskEmptyException();
        }
        String description = input.substring(space + 1);
        return description;
    }

    /**
     * Parses the string input to return the string containing the task number required
     * to remove a task.
     *
     * @param input the string input from the user.
     * @return the string containing the task number required to remove a task.
     */
    public String prepareRemove(String input) {
        input = input.trim();
        int space = input.indexOf(" ");
        int taskNum = 0;
        try {
            taskNum = Integer.parseInt(input.substring(space + 1));
        } catch (NumberFormatException e) {
            /*
              left empty as this will cause the task number to be returned as 0,
              which is invalid, and will throw IndexOutOfBoundsException due to
              the nature of how TaskList manages the removal. The same error
              message is returned as IndexOutOfBoundsException.
             */
        }
        String output = Integer.toString(taskNum);
        return output;
    }

    /**
     * Parses the string input to return the string containing the task number required
     * to mark a task.
     *
     * @param input the string input from the user
     * @return the string containing the task number required to mark a task.
     */
    public String prepareMark(String input) {
        input = input.trim();
        int space = input.indexOf(" ");
        int taskNum = 0;
        try {
            taskNum = Integer.parseInt(input.substring(space + 1));
        } catch (NumberFormatException e) {
            /*
              left empty as this will cause the task number to be returned as 0,
              which is invalid, and will throw IndexOutOfBoundsException due to
              the nature of how TaskList manages the removal. The same error
              message is returned as IndexOutOfBoundsException.
             */
        }
        String output = Integer.toString(taskNum);
        return output;
    }

    /**
     * Parses the string input to return the string containing the task number required
     * to unmark a task.
     *
     * @param input the string input from the user
     * @return the string containing the task number required to unmark a task.
     */
    public String prepareUnmark(String input) {
        input = input.trim();
        int space = input.indexOf(" ");
        int taskNum = 0;
        try {
            taskNum = Integer.parseInt(input.substring(space + 1));
        } catch (NumberFormatException e) {
            /*
              left empty as this will cause the task number to be returned as 0,
              which is invalid, and will throw IndexOutOfBoundsException due to
              the nature of how TaskList manages the removal. The same error
              message is returned as IndexOutOfBoundsException.
             */
        }
        String output = Integer.toString(taskNum);
        return output;
    }

    /**
     * Parses the string input to return the string containing the keyword
     * to find all tasks related to the keyword string.
     *
     * @param input the string input from the user.
     * @return the string containing the keyword .
     * @throws EmptyFindException thrown when the input string does not contain a keyword.
     */
    public String prepareFind(String input) throws EmptyFindException {
        input = input.trim();
        int space = input.indexOf(" ");
        if (space == NOT_FOUND) {
            throw new EmptyFindException();
        }
        String description = input.substring(space + 1);
        description = description.trim();
        return description;
    }
}
