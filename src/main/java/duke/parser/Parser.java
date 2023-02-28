package duke.parser;

import duke.commands.*;

public class Parser {
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

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            default:
                return new IncorrectCommand(":( OOPS!!! Please choose from the command: todo, deadline, event, list, mark, unmark, bye.");
        }
    }

    private Command prepareAddTodoCommand(String args) {
        return new AddTodoCommand(args);
    }

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

    private Command prepareDeleteCommand(String args) {
        try {
            final int targetIndex = Integer.parseInt(args) - 1;
            return new DeleteCommand(targetIndex);
        } catch (IndexOutOfBoundsException ie) {
            return new IncorrectCommand("OOPS! Your task index exceeds the maximum.");
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("Please input number.");
        }
    }

    private Command prepareListCommand() {
        return new ListCommand();
    }

    private Command prepareMarkCommand(String args) {
        try {
            final int targetIndex = Integer.parseInt(args) - 1;
            return new MarkCommand(targetIndex);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("Please input number.");
        }
    }

    private Command prepareUnmarkCommand(String args) {
        try {
            final int targetIndex = Integer.parseInt(args) - 1;
            return new UnmarkCommand(targetIndex);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("Please input number.");
        }
    }




}
