package components;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.ByeCommand;
import command.Command;
import command.DeleteCommand;
import command.FindCommand;
import command.ListCommand;
import command.ToggleMarkCommand;
import exception.DukeException;
import exception.ErrorMessage;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    private static void ParseTodoCommand(String fullCommand, String[] commandFields) throws DukeException {
        try {
            commandFields[0] = fullCommand.substring(5);
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ErrorMessage.MISSING_TODO_PARAMETER.toString());
        }
    }

    private static void ParseEventCommand(String fullCommand, String[] commandFields) throws DukeException {
        try {
            int positionOfFrom = fullCommand.indexOf("/from");
            if (positionOfFrom == -1) {
                throw new DukeException(ErrorMessage.MISSING_TWO_EVENT_PARAMETER.toString());
            }

            String startEnd = fullCommand.substring(positionOfFrom + 1);
            int positionOfTo = startEnd.indexOf("/to");
            if (positionOfTo == -1) {
                throw new DukeException(ErrorMessage.MISSING_ONE_EVENT_PARAMETER.toString());
            }

            commandFields[1] = startEnd.substring(5, positionOfTo - 1);
            commandFields[2] = startEnd.substring(positionOfTo + 4);

            commandFields[0] = fullCommand.substring(6, positionOfFrom - 1);
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ErrorMessage.MISSING_EVENT_PARAMETER.toString());
        }
    }

    private static void ParseDeadlineCommand(String fullCommand, String[] commandFields) throws DukeException {
        int positionOfBy = fullCommand.indexOf("/by");
        if (positionOfBy == -1) {
            throw new DukeException(ErrorMessage.MISSING_DEADLINE_BY_PARAMETER.toString());
        }

        try {
            commandFields[1] = fullCommand.substring(positionOfBy + 4);
            try {
                commandFields[0] = fullCommand.substring(9, positionOfBy - 1);
            } catch (IndexOutOfBoundsException error) {
                throw new DukeException(ErrorMessage.MISSING_DEADLINE_PARAMETER.toString());
            }
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ErrorMessage.EMPTY_DEADLINE_BY_PARAMETER.toString());
        }
    }

    private static void ParseDeleteCommand(String[] words, String[] commandFields) throws DukeException {
        try {
            commandFields[0] = words[1];
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ErrorMessage.EMPTY_DELETE_PARAMETER.toString());
        }
    }

    private static void ParseToggleCommand(String[] words, String[] commandFields) throws DukeException {
        try {
            commandFields[0] = words[0];
            commandFields[1] = words[1];
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ErrorMessage.EMPTY_MARK_OR_UNMARK_PARAMETER.toString());
        }
    }

    private static void ParseFindCommand(String fullCommand, String[] commandFields) throws DukeException {
        try {
            commandFields[0] = fullCommand.substring(5);
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ErrorMessage.MISSING_FIND_PARAMETER.toString());
        }
    }

    /**
     * Returns new command object based on command by user.
     *
     * @param fullCommand Command entered by user.
     * @return Command object based on description.
     * @throws DukeException If command is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] words = fullCommand.split(" ");
        String[] commandFields = new String[5];

        if (words[0].equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (words[0].equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (words[0].equalsIgnoreCase("todo")) {
            ParseTodoCommand(fullCommand, commandFields);
            return new AddTodoCommand(commandFields);
        } else if (words[0].equalsIgnoreCase("event")) {
            ParseEventCommand(fullCommand, commandFields);
            return new AddEventCommand(commandFields);
        } else if (words[0].equalsIgnoreCase("deadline")) {
            ParseDeadlineCommand(fullCommand, commandFields);
            return new AddDeadlineCommand(commandFields);
        } else if (words[0].equalsIgnoreCase("delete")) {
            ParseDeleteCommand(words, commandFields);
            return new DeleteCommand(commandFields);
        } else if (words[0].equalsIgnoreCase("mark")) {
            ParseToggleCommand(words, commandFields);
            return new ToggleMarkCommand(commandFields);
        } else if (words[0].equalsIgnoreCase("unmark")) {
            ParseToggleCommand(words, commandFields);
            return new ToggleMarkCommand(commandFields);
        } else if (words[0].equalsIgnoreCase("find")) {
            ParseFindCommand(fullCommand, commandFields);
            return new FindCommand(commandFields);
        } else {
            throw new DukeException(ErrorMessage.INVALID_COMMAND.toString());
        }
    }
}
