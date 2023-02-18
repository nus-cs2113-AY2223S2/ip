package EntityUtils;

import Command.Command;
import Command.AddCommand;
import Command.DeleteCommand;
import Command.ExitCommand;
import Command.FindCommand;
import Command.ListCommand;
import Command.MarkCommand;
import Command.UnmarkCommand;
import Exceptions.DukeException;
import Exceptions.UnknownInputException;

public class Parser {
    public static Command parse(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        String command = inputArray[0].toLowerCase();

        Command c = null;
        switch (command) {
        case "bye":
            c = new ExitCommand();
            break;

        case "list":
            c = new ListCommand();
            break;
        
        case "find":
            c = new FindCommand(input);
            break;

        case "todo":
        case "deadline":
        case "event":
            c = new AddCommand(command, input);
            break;

        case "mark":
            c = new MarkCommand(command, input);
            break;

        case "unmark":
            c = new UnmarkCommand(command, input);
            break;

        case "delete":
            c = new DeleteCommand(command, input);
            break;

        default:
            throw new UnknownInputException();
        }
        return c;
    }
}
