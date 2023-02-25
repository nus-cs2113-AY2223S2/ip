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
import app.exceptions.DukeException;
import app.exceptions.InvalidCommandException;

public class Parser {

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
        default:
            throw new InvalidCommandException();
        }
        return c;
    }
}
