package dude.commands;

import dude.exception.DudeException;
import dude.exception.EmptyInputException;
import dude.exception.InvalidDeadlineException;
import dude.exception.InvalidEventException;
import dude.exception.InvalidTodoException;
import dude.task.Deadline;
import dude.task.Event;
import dude.task.ListManager;
import dude.task.Todo;
import dude.task.Task;


public class Parser {

    /**
     * Parses the input and calls the relevant methods.
     *
     * @param input The input from the user.
     * @param isSilent If the message should be printed.
     * @throws EmptyInputException If the input is empty.
     */
    public static void parseInput(String input, Boolean isSilent) throws EmptyInputException {
        String[] commands = input.split(" ", 2);
        String nextCommand = formatNextInput(commands);
        try {
            switch (commands[0]) {

            case "todo":
                ListManager.addNewTask(nextCommand, "todo", isSilent);
                break;
            case "deadline":
                ListManager.addNewTask(nextCommand, "deadline", isSilent);
                break;
            case "event":
                ListManager.addNewTask(nextCommand, "event", isSilent);
                break;
            case "list":
                ListManager.printList();
                break;
            case "mark":
                ListManager.markDone(nextCommand);
                break;
            case "unmark":
                ListManager.markUndone(nextCommand);
                break;
            case "delete":
                ListManager.deleteTask(nextCommand);
                break;
            case "find":
                ListManager.findTask(nextCommand);
                break;
            default:
                throw new EmptyInputException();
            }
        } catch (DudeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create a new todo task.
     * Handles the case where the user does not input a description.
     * 
     * @param input Description of todo task
     * @return new Todo task
     * @throws InvalidTodoException
     */
    public static Task createTodo(String input) throws InvalidTodoException {
        if (input.equals("")) {
            throw new InvalidTodoException();
        }
        return new Todo(input);
    }

    /**
     * Create a new deadline task.
     * Handles the case where the user does not input a description or deadline.
     * 
     * @param input Description of deadline task
     * @return new Deadline task
     * @throws InvalidDeadlineException
     */
    public static Task createDeadline(String input) throws InvalidDeadlineException {
        if (input.equals("") || !input.contains("/by")) {
            throw new InvalidDeadlineException();
        }
        String[] description = input.split("/by", 2);
        String deadlineDesc = description[0].trim();
        String deadlineDate = description[1].trim();
        if (deadlineDesc.equals("") || deadlineDate.equals("")) {
            throw new InvalidDeadlineException();
        }
        return new Deadline(deadlineDesc, deadlineDate);
    }

    /**
     * Create a new event task.
     * Handles the case where the user does not input a description, start or end time.
     * 
     * 
     * @param input Description of event task
     * @return new Event task
     * @throws InvalidEventException
     */
    public static Task createEvent(String input) throws InvalidEventException {
        if (input.equals("") || !input.contains("/from") || !input.contains("/to")) {
            throw new InvalidEventException();
        }
        String[] description = input.split("/from", 2);
        String eventDesc = description[0].trim();
        String[] range = description[1].split("/to");
        String eventStart = range[0].trim();
        String eventEnd = range[1].trim();

        if (eventDesc.equals("") || eventStart.equals("") || eventEnd.equals("")) {
            throw new InvalidEventException();
        }
        return new Event(eventDesc, eventStart, eventEnd);
    }

    /**
     * Formats the next input.
     *
     * @param String[] input The next input to be formatted.
     * @return The next input.
     */
    public static String formatNextInput(String[] input) {
        String output;
        if (input.length > 1) {
            output = input[1].trim();
        } else {
            output = "";
        }
        return output;
    }
}


