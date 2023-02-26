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
     * Method first splits the user input into command word and rest of input.
     * Then returns a command based on the command word and respective case entered.
     *
     * @param input The entire user input.
     * @return The type of command that the user input.
     * @throws DukeException If input is invalid or error in processing new command.
     */
    public static Command parse(String input) throws DukeException {
        String[] userInputArray = input.split(" ");
        String commandWord = userInputArray[0];
        String commandDescriptor = input.substring(commandWord.length()).trim();
        Command command;

        switch (commandWord) {
        case "bye":
            command = new ExitCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "delete":
            command = new DeleteCommand(commandDescriptor);
            break;
        case "todo":
            command = new AddTodo(commandWord, commandDescriptor);
            break;
        case "deadline":
            command = new AddDeadline(commandWord, commandDescriptor);
            break;
        case "event":
            command = new AddEvent(commandWord, commandDescriptor);
            break;
        case "mark":
            command = new MarkCommand(commandDescriptor);
            break;
        case "unmark":
            command = new UnmarkCommand(commandDescriptor);
            break;
        case "find":
            command = new FindCommand(commandDescriptor);
            break;
        default:
            throw new InvalidCommandException();
        }
        return command;
    }
}


