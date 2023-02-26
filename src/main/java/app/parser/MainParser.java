package app.parser;

import app.commands.Command;
import app.commands.DeleteCommand;
import app.commands.ExitCommand;
import app.commands.ListCommand;
import app.commands.MarkCommand;
import app.commands.UnmarkCommand;
import app.commands.AddDeadline;
import app.commands.AddTodo;
import app.commands.AddEvent;
import app.commands.FindCommand;
import app.exceptions.DukeException;
import app.exceptions.InvalidCommandException;

/**
 * Represents Duke's main parser used to distinguish the
 * main command of the user input.
 */
public class MainParser {

    /**
     * Method used to enter various cases and assign
     * command type based on user input.
     *
     * @param input The entire user input.
     * @return The type of command that the user input.
     * @throws DukeException If input is invalid or error in processing new command.
     */
    public static Command parse(String input) throws DukeException {
        String[] userInputArray = input.split(" ");
        String commandWord = userInputArray[0];
        String commandDescriptor = input.substring(commandWord.length()).trim();
        Command c;

        switch (commandWord) {
        case "bye":
            c = new ExitCommand();
            break;
        case "list":
            c = new ListCommand();
            break;
        case "delete":
            c = new DeleteCommand(commandDescriptor);
            break;
        case "todo":
            c = new AddTodo(commandWord, commandDescriptor);
            break;
        case "deadline":
            c = new AddDeadline(commandWord, commandDescriptor);
            break;
        case "event":
            c = new AddEvent(commandWord, commandDescriptor);
            break;
        case "mark":
            c = new MarkCommand(commandDescriptor);
            break;
        case "unmark":
            c = new UnmarkCommand(commandDescriptor);
            break;
        case "find":
            c = new FindCommand(commandDescriptor);
            break;
        default:
            throw new InvalidCommandException();
        }
        return c;
    }
}


