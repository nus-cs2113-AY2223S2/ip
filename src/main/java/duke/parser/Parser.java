package duke.parser;

import duke.commands.*;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses user input into command for execution.
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        String[] words = userInput.trim().split(" ", 2);//split the input into command and arguments

        final String commandWord = words[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {

            case AddTodoCommand.COMMAND_WORD:
                return prepareAddTodoCommand(arguments);

            case AddDeadlineCommand.COMMAND_WORD:
                return prepareAddDeadlineCommand(arguments);

            case AddEventCommand.COMMAND_WORD:
                return prepareAddEventCommand(arguments);

            case DeleteCommand.COMMAND_WORD:
                return prepareDeleteCommand(arguments);

            case ListCommand.COMMAND_WORD:
                return prepareListCommand();

            case MarkCommand.COMMAND_WORD:
                return prepareMarkCommand(arguments);

            case UnmarkCommand.COMMAND_WORD:
                return prepareUnmarkCommand(arguments);

            case FindCommand.COMMAND_WORD:
                return prepareFindCommand(arguments);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            default:
                return new IncorrectCommand(":( OOPS!!! Please choose from the command: todo, deadline, event, list, mark, unmark, bye.");
        }
    }

    /**
     * Parses arguments in the context of the add todo command.
     * @param args full command string
     * @return the prepared command
     */
    private Command prepareAddTodoCommand(String args) {
        return new AddTodoCommand(args);
    }

    /**
     * Parses arguments in the context of the add deadline command.
     * @param args full command string
     * @return the prepared command
     */
    private Command prepareAddDeadlineCommand(String args) {
        String[] parts = args.split("/by");
        // Validate arg string format
        if (parts.length != 2) {
            return new IncorrectCommand(AddDeadlineCommand.MESSAGE_USAGE);
        }
        try {
            return new AddDeadlineCommand(
                    parts[0].trim(),
                    parts[1].trim()
            );
        } catch (StringIndexOutOfBoundsException e) {
            return new IncorrectCommand(AddDeadlineCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses arguments in the context of the add event command.
     * @param args full command string
     * @return the prepared command
     */
    private Command prepareAddEventCommand(String args) {
        String[] parts = args.split("/from|/to");
        // Validate arg string format
        if (parts.length != 3) {
            return new IncorrectCommand(AddEventCommand.MESSAGE_USAGE);
        }
        try {
            return new AddEventCommand(
                    parts[0].trim(),
                    parts[1].trim(),
                    parts[2].trim()
            );
        } catch (StringIndexOutOfBoundsException e) {
            return new IncorrectCommand(AddEventCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses arguments in the context of the delete of a task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDeleteCommand(String args) {
        try {
            final int targetIndex = Integer.parseInt(args) - 1;
            return new DeleteCommand(targetIndex);
        }  catch (NumberFormatException nfe) {
            return new IncorrectCommand("Please input number.");
        }
    }

    /**
     * Parses the list command.
     * @return the prepared command
     */
    private Command prepareListCommand() {
        return new ListCommand();
    }

    /**
     * Parses arguments in the context of marking a task command.
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareMarkCommand(String args) {
        try {
            final int targetIndex = Integer.parseInt(args) - 1;
            return new MarkCommand(targetIndex);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("Please input number.");
        }
    }

    /**
     * Parses arguments in the context of unmarking a task command.
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareUnmarkCommand(String args) {
        try {
            final int targetIndex = Integer.parseInt(args) - 1;
            return new UnmarkCommand(targetIndex);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("Please input number.");
        }
    }

    /**
     * Parses arguments in the context of the find of a task command.
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareFindCommand(String args) {
        try {
            return new FindCommand(args);
        } catch (StringIndexOutOfBoundsException e) {
            return new IncorrectCommand(FindCommand.MESSAGE_USAGE);
        }
    }




}
