package duke.parse;

import duke.command.FindCommand;
import duke.exception.EmptyKeywordException;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.exception.EmptyDeadlineDescriptionException;
import duke.exception.EmptyEventDescriptionException;
import duke.exception.EmptyToDoDescriptionException;
import duke.exception.UnknownCommandException;

public abstract class Parser {
    private static final String COMMAND_MARK_TASK = "mark";
    private static final String COMMAND_UNMARK_TASK = "unmark";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    private static Command c;

    /**
     * Initialises an instance of Parser.
     */
    public Parser() {

    }

    /**
     * Parses user input from CLI.
     * Creates command based on user input.
     *
     * @param input User input enter via CLI.
     * @return A command instance based on user input.
     * @throws UnknownCommandException if user enters command unknown to Duke.
     * @throws IndexOutOfBoundsException if user did not enter a task no or entered a task no outside the range of
     * the task list when using mark/unmark/delete command
     * @throws NumberFormatException if user enters a non integer task no when using mark/unmark/delete command.
     * @throws EmptyDeadlineDescriptionException if user enters an incomplete deadline command.
     * @throws EmptyToDoDescriptionException if user enters an incomplete to do command.
     * @throws EmptyEventDescriptionException if user enters an incomplete event command.
     */
    public static Command parse(String input) throws UnknownCommandException,
            IndexOutOfBoundsException, NumberFormatException, EmptyDeadlineDescriptionException,
            EmptyToDoDescriptionException, EmptyEventDescriptionException, EmptyKeywordException {
        String command = getCommand(input);

        switch (command) {
        case COMMAND_LIST:
            c = new ListCommand();
            break;
        case COMMAND_MARK_TASK:
            int markTaskNo = getTaskNo(input);
            c = new MarkCommand(markTaskNo);
            break;
        case COMMAND_UNMARK_TASK:
            int unmarkTaskNo = getTaskNo(input);
            c = new UnMarkCommand(unmarkTaskNo);
            break;
        case COMMAND_DELETE:
            int delTaskNo = getTaskNo(input);
            c = new DeleteCommand(delTaskNo);
            break;
        case COMMAND_ADD_DEADLINE:
            String[] deadlineDetails = getDeadlineDetails(input);
            String deadlineTaskName = deadlineDetails[0];
            String by = deadlineDetails[1];
            c = new AddDeadlineCommand(deadlineTaskName, by);
            break;
        case COMMAND_ADD_EVENT:
            String[] eventDetails = getEventDetails(input);
            String eventTaskName = eventDetails[0];
            String from = eventDetails[1];
            String to = eventDetails[2];
            c = new AddEventCommand(eventTaskName, from, to);
            break;
        case COMMAND_ADD_TODO:
            String toDoTaskName = getToDoTaskName(input);
            c = new AddToDoCommand(toDoTaskName);
            break;
        case COMMAND_FIND:
            String keyword = getKeyword(input);
            c = new FindCommand(keyword);
            break;
        case COMMAND_EXIT:
            c = new ExitCommand();
            break;
        default:
            throw new UnknownCommandException();
        }

        return c;
    }

    /**
     * Retrieves command from user input.
     *
     * @param input User input enter via CLI.
     * @return Command.
     */
    private static String getCommand(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0];
        return command;
    }

    /**
     * Retrieves task no from user input.
     *
     * @param input User input enter via CLI.
     * @return Task no.
     * @throws IndexOutOfBoundsException if user did not enter a task no or entered a task no outside the range of
     * the task list when using mark/unmark/delete command.
     * @throws NumberFormatException if user enters a non integer task no when using mark/unmark/delete command.
     */
    private static int getTaskNo(String input) throws IndexOutOfBoundsException,
            NumberFormatException {
        String[] words = input.split(" ", 2);
        int taskNo = Integer.parseInt(words[1]) - 1;

        return taskNo;
    }

    /**
     * Retrieves deadline task name and end date/time from user input.
     *
     * @param input User input enter via CLI.
     * @return A string array of deadline details containing task name and end date/time.
     * @throws EmptyDeadlineDescriptionException if user enters an incomplete deadline command.
     */
    private static String[] getDeadlineDetails(String input) throws EmptyDeadlineDescriptionException {
        String[] words = input.split(" ", 2);

        if (words.length != 2) {
            throw new EmptyDeadlineDescriptionException();
        }

        words = words[1].split(" /by ");

        if (words.length != 2) {
            throw new EmptyDeadlineDescriptionException();
        }

        String deadlineTaskName = words[0];
        String by = words[1];

        String[] deadlineDetails = new String[]{deadlineTaskName, by};

        return deadlineDetails;
    }

    /**
     * Retrieves event task name, start date/time and end date/time from user input.
     *
     * @param input User input enter via CLI.
     * @return A string array of event details containing task name, start date/time, end date/time.
     * @throws EmptyEventDescriptionException if user enters an incomplete event command.
     */
    private static String[] getEventDetails(String input) throws EmptyEventDescriptionException {
        String[] words = input.split(" ", 2);

        if (words.length != 2) {
            throw new EmptyEventDescriptionException();
        }

        words = words[1].split(" /from ");

        if (words.length != 2) {
            throw new EmptyEventDescriptionException();
        }

        String eventTaskName = words[0];
        words = words[1].split(" /to ");

        if (words.length != 2) {
            throw new EmptyEventDescriptionException();
        }

        String from = words[0];
        String to = words[1];

        String[] eventDetails = new String[]{eventTaskName, from, to};

        return eventDetails;
    }

    /**
     * Retrieves to do task name from user input.
     *
     * @param input User input enter via CLI.
     * @return To do task name.
     * @throws EmptyToDoDescriptionException if user enters an incomplete to do command.
     */
    private static String getToDoTaskName(String input) throws EmptyToDoDescriptionException {
        String[] words = input.split(" ", 2);

        if (words.length != 2) {
            throw new EmptyToDoDescriptionException();
        }

        String toDoTaskName = words[1];

        return toDoTaskName;
    }

    private static String getKeyword(String input) throws EmptyKeywordException {
        String[] words = input.split(" ", 2);

        if (words.length != 2) {
            throw new EmptyKeywordException();
        }

        String keyword = words[1];

        return keyword;
    }
}