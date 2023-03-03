package duke.parse;

import duke.ui.Ui;
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
    private static Command c;

    public Parser() {

    }

    public static Command parse(String input, Ui ui) throws UnknownCommandException,
            IndexOutOfBoundsException, NumberFormatException, EmptyDeadlineDescriptionException,
            EmptyToDoDescriptionException, EmptyEventDescriptionException {
        String command = getCommand(input);

        switch (command) {
        case COMMAND_LIST:
            c = new ListCommand();
            break;
        case COMMAND_MARK_TASK:
            int markTaskNo = getTaskNo(input, ui);
            c = new MarkCommand(markTaskNo);
            break;
        case COMMAND_UNMARK_TASK:
            int unmarkTaskNo = getTaskNo(input, ui);
            c = new UnMarkCommand(unmarkTaskNo);
            break;
        case COMMAND_DELETE:
            int delTaskNo = getTaskNo(input, ui);
            c = new DeleteCommand(delTaskNo);
            break;
        case COMMAND_ADD_DEADLINE:
            String[] deadlineDetails = getDeadlineDetails(input, ui);
            String deadlineTaskName = deadlineDetails[0];
            String by = deadlineDetails[1];
            c = new AddDeadlineCommand(deadlineTaskName, by);
            break;
        case COMMAND_ADD_EVENT:
            String[] eventDetails = getEventDetails(input, ui);
            String eventTaskName = eventDetails[0];
            String from = eventDetails[1];
            String to = eventDetails[2];
            c = new AddEventCommand(eventTaskName, from , to);
            break;
        case COMMAND_ADD_TODO:
            String toDoTaskName = getToDoTaskName(input, ui);
            c = new AddToDoCommand(toDoTaskName);
            break;
        case COMMAND_EXIT:
            c = new ExitCommand();
            break;
        default:
            throw new UnknownCommandException();
        }

        return c;
    }

    private static String getCommand(String input) {
        String[] words = input.split(" ", 2);
        String command = words[0];
        return command;
    }

    private static int getTaskNo (String input, Ui ui) throws IndexOutOfBoundsException,
            NumberFormatException {
        String[] words = input.split(" ", 2);
        int taskNo = Integer.parseInt(words[1]) - 1;

        return taskNo;
    }

    private static String[] getDeadlineDetails(String input, Ui ui) throws EmptyDeadlineDescriptionException {
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

    private static String[] getEventDetails(String input, Ui ui) throws EmptyEventDescriptionException {
        String[] words = input.split(" ", 2);

        if (words.length != 2) {
            throw  new EmptyEventDescriptionException();
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

    private static String getToDoTaskName(String input, Ui ui) throws EmptyToDoDescriptionException {
        String[] words = input.split(" ", 2);

        if (words.length != 2) {
            throw new EmptyToDoDescriptionException();
        }

        String toDoTaskName = words[1];

        return toDoTaskName;
    }
}
