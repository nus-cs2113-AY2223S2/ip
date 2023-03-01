package EntityUtils;

import Command.Command;
import Command.AddDeadlineCommand;
import Command.AddTodoCommand;
import Command.AddEventCommand;
import Command.DeleteCommand;
import Command.ExitCommand;
import Command.FindCommand;
import Command.ListCommand;
import Command.MarkCommand;
import Command.UnmarkCommand;
import Command.UpcomingCommand;
import Exceptions.DukeException;
import Exceptions.UnknownInputException;

/**
 * Class that handles parsing of user inputs
 */
public class Parser {
    /**
     * Parses input and returns a command which matchs it
     * @param input user input
     * @return corresponding command
     * @throws DukeException
     */
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
        
        // Finds all tasks with specified substring in description
        case "find":
            c = new FindCommand(input);
            break;

        // Lists upcoming tasks
        case "upcoming":
            c = new UpcomingCommand();
            break;

        // Creates a new todo
        case "todo":
            c = new AddTodoCommand(command, input);
            break;

        // Creates a new deadline
        case "deadline":
            c = new AddDeadlineCommand(command, input);
            break;

        // Creates a new event
        case "event":
            c = new AddEventCommand(command, input);
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

        // Throws exception if Duke is unable to understand user input
        default:
            throw new UnknownInputException();
        }
        return c;
    }
}
