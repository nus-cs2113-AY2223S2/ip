package EntityUtils;

import Command.Command;
import Command.AddCommand;
import Command.DeleteCommand;
import Command.ExitCommand;
import Command.FindCommand;
import Command.ListCommand;
import Command.MarkCommand;
import Command.UnmarkCommand;
import Command.UpcomingCommand;
import Exceptions.DukeException;
import Exceptions.UnknownInputException;

public class Parser {
    public static Command parse(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        String command = inputArray[0].toLowerCase();

        Command c = null;
        switch (command) {
        // Exits Duke
        case "bye":
            c = new ExitCommand();
            break;

        // Lists all added tasks
        case "list":
            c = new ListCommand();
            break;
        
        case "find":
            c = new FindCommand(input);
            break;

        // Lists upcoming tasks
        case "upcoming":
            c = new UpcomingCommand();
            break;

        // Creates a new task
        case "todo":
        case "deadline":
        case "event":
            c = new AddCommand(command, input);
            break;

        // Marks a task as done using its index
        case "mark":
            c = new MarkCommand(command, input);
            break;

        // Mark a task as not done using its index
        case "unmark":
            c = new UnmarkCommand(command, input);
            break;

        // Delete task using its index
        case "delete":
            c = new DeleteCommand(command, input);
            break;

        default:
            throw new UnknownInputException();
        }
        return c;
    }
}
